package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_8X0ETest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_8X0E();

    @Test
    public void shouldAcceptProperCode() throws Exception {
        int code = 0x8a0E;
        assertTrue(opcode.accept((short) code));
    }

    @Test
    public void shouldSetVF_whenOnlyMostSignificantIsSet() throws Exception {
        short code = (short) 0x810E;
        byte value = (byte) 0b10000000;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b00000000);
        assertTrue(vm.V[15] == 0b00000001);
    }

    @Test
    public void shouldSetVF_whenAllAreSet() throws Exception {
        short code = (short) 0x810E;
        byte value = (byte) 0b11111111;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == (byte) 0b11111110);
        assertTrue(vm.V[15] == 0b00000001);
    }

    @Test
    public void shouldNotSetVF_whenAllAreSetExceptLeastSignificant() throws Exception {
        short code = (short) 0x810E;
        byte value = (byte) 0b01111111;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == (byte) 0b11111110);
        assertTrue(vm.V[15] == 0b00000000);
    }

    @Test
    public void shouldNotSetVF_whenOnlyMostSignificantIsSet() throws Exception {
        short code = (short) 0x810E;
        byte value = 0b00000001;
        vm.V[1] = value;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == 0b00000010);
        assertTrue(vm.V[15] == 0b00000000);
    }
}