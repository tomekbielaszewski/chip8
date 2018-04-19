package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8XY2Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8XY2();

    @Test
    public void shouldAcceptCodeWizMask8007() throws Exception {
        short code = (short) 0x8aa2;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithAllSetBits() throws Exception {
        short code = (short) 0x8122;
        byte x = (byte) 0b11111111;
        byte y = (byte) 0b11111111;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == (byte) 0b11111111);
        assertTrue(vm.V[2] == y);
    }

    @Test
    public void shouldResultWithAllUnsetBits() throws Exception {
        short code = (short) 0x8122;
        byte x = (byte) 0b11111111;
        byte y = 0b00000000;
        vm.V[1] = x;
        vm.V[2] = y;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b00000000);
        assertTrue(vm.V[2] == y);
    }
}