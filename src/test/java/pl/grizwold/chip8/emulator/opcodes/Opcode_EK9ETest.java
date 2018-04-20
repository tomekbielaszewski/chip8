package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_EK9ETest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_EK9E();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xE19E;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldIncrementPCWhenKeyCodeInRegisterCorrespondsToKeyboard() throws Exception {
        short code = (short) 0xE19E;
        short pcBefore = 100;
        vm.V[1] = 5;
        vm.PC = pcBefore;
        vm.keyboard = 0b00100000;

        opcode.execute(code, vm);

        assertTrue(vm.PC == pcBefore + 2);
    }

    @Test
    public void shouldIncrementPCWhenAllKeysOnKeyboardArePressed() throws Exception {
        short code = (short) 0xE19E;
        short pcBefore = 100;
        vm.V[1] = 5;
        vm.PC = pcBefore;
        vm.keyboard = 0b11111111;

        opcode.execute(code, vm);

        assertTrue(vm.PC == pcBefore + 2);
    }

    @Test
    public void shouldPCStayTheSameWhenKeyCodeInRegisterIsCloseToKeyboardFlagButNotExact() throws Exception {
        short code = (short) 0xE19E;
        short pcBefore = 100;
        vm.V[1] = 5;
        vm.PC = pcBefore;
        vm.keyboard = 0b01010000;

        opcode.execute(code, vm);

        assertTrue(vm.PC == pcBefore);
    }

    @Test
    public void shouldPCStayTheSameWhenNoKeysPressed() throws Exception {
        short code = (short) 0xE19E;
        short pcBefore = 100;
        vm.V[1] = 5;
        vm.PC = pcBefore;
        vm.keyboard = 0b00000000;

        opcode.execute(code, vm);

        assertTrue(vm.PC == pcBefore);
    }
}