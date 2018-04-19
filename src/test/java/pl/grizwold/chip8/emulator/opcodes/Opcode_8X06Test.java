package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8X06Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8X06();

    @Test
    public void shouldAcceptProperCode() throws Exception {
        int code = 0x8a06;
        assertTrue(opcode.accept((short) code));
    }

    @Test
    public void shouldSetVF_whenOnlyLeastSignificantIsSet() throws Exception {
        short code = (short) 0x8106;
        byte value = 0b00000001;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == value/2);
        assertTrue(vm.V[15] == 0b00000001);
    }

    @Test
    public void shouldSetVF_whenAllAreSet() throws Exception {
        short code = (short) 0x8106;
        byte value = (byte) 0b11111111;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b01111111);
        assertTrue(vm.V[15] == 0b00000001);
    }

    @Test
    public void shouldNotSetVF_whenAllAreSetExceptLeastSignificant() throws Exception {
        short code = (short) 0x8106;
        byte value = (byte) 0b11111110;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b01111111);
        assertTrue(vm.V[15] == 0b00000000);
    }

    @Test
    public void shouldNotSetVF_whenOnlyMostSignificantIsSet() throws Exception {
        short code = (short) 0x8106;
        byte value = (byte) 0b10000000;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b01000000);
        assertTrue(vm.V[15] == 0b00000000);
    }
}