package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.assertTrue;

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

        //PC needs to be decremented by 2 becouse just after opcode evaluation it is incremented by 2 by defaul
        assertTrue(vm.PC == (0xaaa - 2));
        assertTrue(vm.PC != pcBefore);
    }
}