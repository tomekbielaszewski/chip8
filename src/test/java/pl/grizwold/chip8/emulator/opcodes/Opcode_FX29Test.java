package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.Sprites;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.hamcrest.Matchers.is;
import static org.junit.Assert.*;

public class Opcode_FX29Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX29();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA29;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldPointToCharacterSprite_0() throws Exception {
        shouldPointToCharacterSprite((byte) 0x0, Sprites.SPRITE_0);
    }

    @Test
    public void shouldPointToCharacterSprite_1() throws Exception {
        shouldPointToCharacterSprite((byte) 0x1, Sprites.SPRITE_1);
    }

    @Test
    public void shouldPointToCharacterSprite_2() throws Exception {
        shouldPointToCharacterSprite((byte) 0x2, Sprites.SPRITE_2);
    }

    @Test
    public void shouldPointToCharacterSprite_3() throws Exception {
        shouldPointToCharacterSprite((byte) 0x3, Sprites.SPRITE_3);
    }

    @Test
    public void shouldPointToCharacterSprite_4() throws Exception {
        shouldPointToCharacterSprite((byte) 0x4, Sprites.SPRITE_4);
    }

    @Test
    public void shouldPointToCharacterSprite_5() throws Exception {
        shouldPointToCharacterSprite((byte) 0x5, Sprites.SPRITE_5);
    }

    @Test
    public void shouldPointToCharacterSprite_6() throws Exception {
        shouldPointToCharacterSprite((byte) 0x6, Sprites.SPRITE_6);
    }

    @Test
    public void shouldPointToCharacterSprite_7() throws Exception {
        shouldPointToCharacterSprite((byte) 0x7, Sprites.SPRITE_7);
    }

    @Test
    public void shouldPointToCharacterSprite_8() throws Exception {
        shouldPointToCharacterSprite((byte) 0x8, Sprites.SPRITE_8);
    }

    @Test
    public void shouldPointToCharacterSprite_9() throws Exception {
        shouldPointToCharacterSprite((byte) 0x9, Sprites.SPRITE_9);
    }

    @Test
    public void shouldPointToCharacterSprite_A() throws Exception {
        shouldPointToCharacterSprite((byte) 0xA, Sprites.SPRITE_A);
    }

    @Test
    public void shouldPointToCharacterSprite_B() throws Exception {
        shouldPointToCharacterSprite((byte) 0xB, Sprites.SPRITE_B);
    }

    @Test
    public void shouldPointToCharacterSprite_C() throws Exception {
        shouldPointToCharacterSprite((byte) 0xC, Sprites.SPRITE_C);
    }

    @Test
    public void shouldPointToCharacterSprite_D() throws Exception {
        shouldPointToCharacterSprite((byte) 0xD, Sprites.SPRITE_D);
    }

    @Test
    public void shouldPointToCharacterSprite_E() throws Exception {
        shouldPointToCharacterSprite((byte) 0xE, Sprites.SPRITE_E);
    }

    @Test
    public void shouldPointToCharacterSprite_F() throws Exception {
        shouldPointToCharacterSprite((byte) 0xF, Sprites.SPRITE_F);
    }

    public void shouldPointToCharacterSprite(byte registerValue, byte[] expectedSprite) throws Exception {
        short code = (short) 0xFA29;
        vm.V[0xA] = registerValue;

        opcode.execute(code, vm);

        byte[] character = new byte[5];
        System.arraycopy(vm.memory, vm.I, character, 0, 5);

        assertThat(character, is(expectedSprite));
    }
}