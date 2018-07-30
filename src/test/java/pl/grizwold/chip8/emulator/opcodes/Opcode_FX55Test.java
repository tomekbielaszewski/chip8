package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import java.util.Arrays;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Opcode_FX55Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX55();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA55;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldCopyOnlyFirstRegister() throws Exception {
        short code = (short) 0xF055;
        vm.V = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        short iBefore = vm.I;
        byte[] expectedMemorySubset = new byte[]{1};

        opcode.execute(code, vm);

        assertThat(Arrays.copyOfRange(vm.memory, iBefore, vm.I), is(expectedMemorySubset));
    }

    @Test
    public void shouldCopyAllRegisters() throws Exception {
        short code = (short) 0xFF55;
        vm.V = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        short iBefore = vm.I;
        byte[] expectedMemorySubset = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};

        opcode.execute(code, vm);

        assertThat(Arrays.copyOfRange(vm.memory, iBefore, vm.I), is(expectedMemorySubset));
    }

    @Test
    public void shouldCopyXRegisters() throws Exception {
        short code = (short) 0xF755;
        vm.V = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        short iBefore = vm.I;
        byte[] expectedMemorySubset = new byte[]{1, 2, 3, 4, 5, 6, 7, 8};

        opcode.execute(code, vm);

        assertThat(Arrays.copyOfRange(vm.memory, iBefore, vm.I), is(expectedMemorySubset));
    }

    @Test
    public void shouldChangeMemoryPointer() throws Exception {
        short code = (short) 0xF755;
        short expectedI = (short) (vm.I + 0x7 + 1);

        opcode.execute(code, vm);

        assertThat(vm.I, is(expectedI));
    }
}