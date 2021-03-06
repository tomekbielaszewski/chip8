package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_9XY0Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_9XY0();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x9aa0;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldIncrementProgramCounterWhenRegistersAreNotEqual() throws Exception {
        short code = (short) 0x9120;
        byte x = 1;
        byte y = 2;
        short pc = 100;
        vm.V[1] = x;
        vm.V[2] = y;
        vm.PC = pc;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == x);
        assertTrue(vm.V[2] == y);
        assertTrue(vm.PC == pc + 2);
    }

    @Test
    public void shouldNotIncrementProgramCounterWhenRegistersAreEqual() throws Exception {
        short code = (short) 0x9120;
        byte x = 2;
        byte y = 2;
        short pc = 100;
        vm.V[1] = x;
        vm.V[2] = y;
        vm.PC = pc;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == x);
        assertTrue(vm.V[2] == y);
        assertTrue(vm.PC == pc);
    }
}