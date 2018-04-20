package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class Opcode_00E0Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_00E0();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x00E0;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldClearDisplayByDefault() throws Exception {
        short code = (short) 0x00E0;

        opcode.execute(code, vm);

        for (int i = 0; i < vm.screen.length; i++) {
            boolean pixel = vm.screen[i];
            assertFalse(pixel);
        }
    }

    @Test
    public void shouldClearDisplayWhenAllWereOn() throws Exception {
        short code = (short) 0x00E0;
        for (int i = 0; i < vm.screen.length; i++) {
            vm.screen[i] = true;
        }

        opcode.execute(code, vm);

        for (int i = 0; i < vm.screen.length; i++) {
            boolean pixel = vm.screen[i];
            assertFalse(pixel);
        }
    }
}