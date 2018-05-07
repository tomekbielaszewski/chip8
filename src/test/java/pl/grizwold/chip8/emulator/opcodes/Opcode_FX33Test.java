package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX33Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX33();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA33;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetConsecutiveMemoryLocationsToDigitsOf123() throws Exception {
        short code = (short) 0xFA33;
        vm.V[0xA] = 123;
        vm.I = 1234;

        opcode.execute(code, vm);

        assertTrue(vm.memory[1234] == 1);
        assertTrue(vm.memory[1235] == 2);
        assertTrue(vm.memory[1236] == 3);
    }

    @Test
    public void shouldSetConsecutiveMemoryLocationsToZero() throws Exception {
        short code = (short) 0xFA33;
        vm.V[0xA] = 0;
        vm.I = 1234;

        opcode.execute(code, vm);

        assertTrue(vm.memory[1234] == 0);
        assertTrue(vm.memory[1235] == 0);
        assertTrue(vm.memory[1236] == 0);
    }

    @Test
    public void shouldSetConsecutiveMemoryLocationsTo030() throws Exception {
        short code = (short) 0xFA33;
        vm.V[0xA] = 30;
        vm.I = 1234;

        opcode.execute(code, vm);

        assertTrue(vm.memory[1234] == 0);
        assertTrue(vm.memory[1235] == 3);
        assertTrue(vm.memory[1236] == 0);
    }

    @Test
    public void shouldSetConsecutiveMemoryLocationsTo003() throws Exception {
        short code = (short) 0xFA33;
        vm.V[0xA] = 3;
        vm.I = 1234;

        opcode.execute(code, vm);

        assertTrue(vm.memory[1234] == 0);
        assertTrue(vm.memory[1235] == 0);
        assertTrue(vm.memory[1236] == 3);
    }

    @Test
    public void shouldSetConsecutiveMemoryLocationsTo101() throws Exception {
        short code = (short) 0xFA33;
        vm.V[0xA] = 101;
        vm.I = 1234;

        opcode.execute(code, vm);

        assertTrue(vm.memory[1234] == 1);
        assertTrue(vm.memory[1235] == 0);
        assertTrue(vm.memory[1236] == 1);
    }
}