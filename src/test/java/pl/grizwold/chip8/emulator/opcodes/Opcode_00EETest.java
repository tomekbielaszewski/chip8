package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_00EETest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_00EE();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0x00ee;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldResultWithProgramCounterSetToTopOfStack() throws Exception {
        short code = (short) 0x00ee;
        short pcBefore = 0x7fff;
        short topOfStackBefore = 0x6743;
        vm.PC = pcBefore;
        vm.stack.push(topOfStackBefore);

        opcode.execute(code, vm);

        assertTrue(vm.PC == topOfStackBefore);
    }

    @Test
    public void shouldResultWithProgramCounterZeroedWhenStackEmpty() throws Exception {
        short code = (short) 0x00ee;
        vm.PC = (short) 0x7fff;

        opcode.execute(code, vm);

        assertTrue(vm.PC == 0);
    }

    @Test
    public void shouldResultWithProgramCounterSetToLatestStackPush() throws Exception {
        short code = (short) 0x00ee;
        short pcBefore = 0x7fff;
        short firstPush = 0x6743;
        short secondPush = (short) 0xbeef;
        vm.PC = pcBefore;
        vm.stack.push(firstPush);
        vm.stack.push(secondPush);

        opcode.execute(code, vm);

        assertTrue(vm.PC == secondPush);
    }
}