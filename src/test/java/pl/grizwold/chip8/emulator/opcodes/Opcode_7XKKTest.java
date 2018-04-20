package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_7XKKTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_7XKK();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x7aab;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithSumOfGivenValueAndRegisterValue() throws Exception {
        short code = (short) 0x71aa;
        byte x = 1;
        byte addition = (byte) 0xaa;
        vm.V[1] = x;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == x + addition);
    }
}