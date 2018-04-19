package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8XY4Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8XY4();

    @Test
    public void shouldAcceptCodeWizMask8007() throws Exception {
        short code = (short) 0x8114;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldAddWithoutOverflow() throws Exception {
        short code = (short) 0x8124;
        byte x = 100;
        byte y = 27;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 127);
        assertTrue(vm.V[15] == 0);
    }

    @Test
    public void shouldAddWithOverflow() throws Exception {
        short code = (short) 0x8124;
        byte x = 100;
        byte y = 28;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == -128);
        assertTrue(vm.V[15] == 1);
    }
}