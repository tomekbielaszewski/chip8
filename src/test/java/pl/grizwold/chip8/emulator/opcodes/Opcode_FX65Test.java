package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX65Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX65();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA65;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetVxToDelayTimer() throws Exception {
        short code = (short) 0xFA65;

        opcode.execute(code, vm);
    }
}