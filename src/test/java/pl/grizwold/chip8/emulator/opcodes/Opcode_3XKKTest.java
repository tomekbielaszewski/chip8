package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_3XKKTest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_3XKK();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x3abb;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldIncrementProgramCounterWhenRegisterEqualToValueInOpcode() throws Exception {
        short code = (short) 0x3122;
        byte x = 0x22;
        short pc = 100;
        vm.V[1] = x;
        vm.PC = pc;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == x);
        assertTrue(vm.PC == pc + 2);
    }

    @Test
    public void shouldNotIncrementProgramCounterWhenRegisterNotEqualToValueInOpcode() throws Exception {
        short code = (short) 0x3122;
        byte x = 0x23;
        short pc = 100;
        vm.V[1] = x;
        vm.PC = pc;

        opcode.execute(code, vm);

        assertTrue(vm.V[1] == x);
        assertTrue(vm.PC == pc);
    }
}