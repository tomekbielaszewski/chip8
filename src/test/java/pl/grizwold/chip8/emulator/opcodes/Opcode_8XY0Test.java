package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8XY0Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8XY0();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x8aa0;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithCopiedValue() throws Exception {
        short code = (short) 0x8120;
        byte x = 0b00000000;
        byte y = 0b00110010;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == y);
        assertTrue(vm.V[2] == y);
    }
}