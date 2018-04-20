package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_BNNNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_BNNN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xbabb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetProgramCounterToSumOfOpcodeAndV0() throws Exception {
        short code = (short) 0xbaaa;
        short pcBefore = 0x7fff;
        byte V0 = 0x3;
        vm.PC = pcBefore;
        vm.V[0] = V0;

        opcode.execute(code, vm);

        assertTrue(vm.PC == 0xaaa + V0);
        assertTrue(vm.PC != pcBefore);
    }

    @Test
    public void shouldSetProgramCounterToSumOfOpcodeAndV0_whenV0IsZero() throws Exception {
        short code = (short) 0xbaaa;
        short pcBefore = 0x7fff;
        short V0 = 0;
        vm.PC = pcBefore;

        opcode.execute(code, vm);

        assertTrue(vm.PC == 0xaaa);
        assertTrue(vm.PC != pcBefore);
    }
}