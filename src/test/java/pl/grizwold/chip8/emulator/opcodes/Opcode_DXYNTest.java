package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Opcode_DXYNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_DXYN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xdabb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldDrawSinglePixelInLeftUpperCorner() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b10000000;

        opcode.execute(code, vm);

        assertThat(vm.screen[0][0], is(true));
    }

    @Test
    public void shouldDrawSinglePixelOn8thPixelToTheRightFromLeftUpperCorner() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b00000001;

        opcode.execute(code, vm);

        assertThat(vm.screen[7][0], is(true));
    }

    @Test
    public void shouldDraw8PixelsOnLeftUpperCorner() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;

        opcode.execute(code, vm);

        for (int i = 0; i < 8; i++) {
            assertTrue(String.format("%d %d was not true", i, 0), vm.screen[i][0]);
        }
    }

    @Test
    public void shouldDraw16PixelsOnLeftUpperCorner() throws Exception {
        short code = (short) 0xd002;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;
        vm.memory[0x202] = (byte) 0b11111111;

        opcode.execute(code, vm);

        for (int i = 0; i < 16; i++) {
            assertTrue(String.format("%d %d was not true", i, 0), vm.screen[i][0]);
        }
    }

    @Test
    public void shouldWrapTheSpriteAroundTheScreen() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 60;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;

        opcode.execute(code, vm);

        for (int i = 60; i < 68; i++) {
            int x = i >= 64 ? i - 64 : i;
            assertTrue(String.format("%d %d was not true", i, 0), vm.screen[x][0]);
        }
    }

    @Test
    public void shouldDetectCollisionWithSinglePixel() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b10000000;
        vm.screen[0][0] = true;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xF] == 1);
    }

    @Test
    public void shouldDetectCollisionWithSinglePixelWhenLightningUpMany() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;
        vm.screen[4][0] = true;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xF] == 1);
    }

    @Test
    public void shouldDetectCollisionWhenThereAreManyOfThem() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;
        vm.screen[4][0] = true;
        vm.screen[6][0] = true;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xF] == 1);
    }

    @Test
    public void shouldNotDetectCollisionWhenLightedPixelIsOnTheEdge() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;
        vm.screen[8][0] = true;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xF] == 0);
    }

    @Test
    public void shouldDetectSingleCollisionOnTheMiddleOfTheScreen() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 30;
        vm.V[0x5] = 14;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b11111111;
        vm.screen[36][14] = true;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xF] == 1);
    }

    @Test
    public void shouldLightUpSinglePixelAndThenTurnItOffWithCollisionDetection() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b00000001;

        opcode.execute(code, vm);
        assertThat(vm.screen[7][0], is(true));

        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b00000001;

        opcode.execute(code, vm);

        assertThat(vm.screen[7][0], is(false));
        assertTrue(vm.V[0xF] == 1);
    }

    @Test
    public void shouldSwitchLastBitKeepingAllOthersIntact() throws Exception {
        short code = (short) 0xd451;
        vm.V[0x4] = 0;
        vm.V[0x5] = 0;
        vm.I = 0x201;
        vm.memory[0x201] = (byte) 0b00000001;
        for (int x = 0; x < 8; x++) {
            vm.screen[x][0] = true;
        }

        opcode.execute(code, vm);


        for (int x = 0; x < 7; x++) {
            assertThat(vm.screen[x][0], is(true));
        }
        assertThat(vm.screen[7][0], is(false));
    }
}