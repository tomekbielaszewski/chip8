package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_ANNNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_ANNN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xaabb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetProgramCounterToOpcode() throws Exception {
        short code = (short) 0xaaaa;
        short IBefore = 0x7fff;
        vm.I = IBefore;

        opcode.execute(code, vm);

        assertTrue(vm.I == 0xaaa);
        assertTrue(vm.I != IBefore);
    }
}