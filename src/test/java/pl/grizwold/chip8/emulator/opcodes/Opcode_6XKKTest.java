package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_6XKKTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_6XKK();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x6aab;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithReplacedValue() throws Exception {
        short code = (short) 0x61aa;
        byte x = 1;
        byte replacement = (byte) 0xaa;
        vm.V[1] = x;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == replacement);
    }
}