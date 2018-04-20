package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_2NNNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_2NNN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x2abb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetProgramCounterToTopOfTheStack() throws Exception {
        short code = (short) 0x2aaa;
        short pcBefore = 0x7fff;
        vm.PC = pcBefore;
        short stackPopBefore = vm.stack.pop();

        opcode.execute(code, vm);

        short stackPopAfter = vm.stack.pop();
        assertTrue(stackPopBefore == 0);
        assertTrue(stackPopAfter == pcBefore);
        assertTrue(vm.PC == 0xaaa);
    }
}