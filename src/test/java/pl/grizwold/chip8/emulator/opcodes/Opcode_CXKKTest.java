package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class Opcode_CXKKTest {
    private final Opcode opcode = new Opcode_CXKK();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xcabb;

        assertTrue(opcode.accept(code));
    }
}