package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.assertTrue;

public class Opcode_8XY7Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8XY7();

    @Test
    public void shouldAcceptCodeWizMask8007() throws Exception {
        short code = (short) 0x8117;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSubtractRegistersWithoutOverflow() throws Exception {
        short code = (short) 0x8127;
        byte xReg = 25;
        byte yReg = 100;
        vm.V[1] = xReg;
        vm.V[2] = yReg;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 75);
        assertTrue(vm.V[2] == yReg);
        assertTrue(vm.V[15] == 0);
    }

    @Test
    public void shouldSubtractRegistersWithOverflow() throws Exception {
        short code = (short) 0x8127;
        byte xReg = 100;
        byte yReg = 25;
        vm.V[1] = xReg;
        vm.V[2] = yReg;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == -75);
        assertTrue(vm.V[2] == yReg);
        assertTrue(vm.V[15] == 1);
    }
}