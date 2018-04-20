package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_DXYNTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_DXYN();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xdabb;

        assertTrue(opcode.accept(code));
    }


}