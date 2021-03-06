package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8XY3Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8XY3();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x8aa3;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithAllSetBits() throws Exception {
        short code = (short) 0x8123;
        byte x = (byte) 0b11111111;
        byte y = 0b00000000;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == (byte) 0b11111111);
        assertTrue(vm.V[2] == y);
    }

    @Test
    public void shouldResultWithAllUnsetBits() throws Exception {
        short code = (short) 0x8123;
        byte x = 0b00000000;
        byte y = 0b00000000;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b00000000);
        assertTrue(vm.V[2] == y);
    }
}