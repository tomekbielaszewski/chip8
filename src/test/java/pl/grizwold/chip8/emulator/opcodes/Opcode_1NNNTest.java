package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_1NNNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_1NNN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x1abb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetProgramCounterToOpcode() throws Exception {
        short code = (short) 0x1aaa;
        short pcBefore = 0x7fff;
        vm.PC = pcBefore;

        opcode.execute(code, vm);

        assertTrue(vm.PC == 0xaaa);
        assertTrue(vm.PC != pcBefore);
    }
}