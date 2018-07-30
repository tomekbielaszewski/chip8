package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class Opcode_FX65Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX65();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA65;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldCopyOnlyFirstRegister() throws Exception {
        short code = (short) 0xF065;
        vm.I = 500;
        vm.memory = createMemoryWith(new byte[]{100}, 500);

        opcode.execute(code, vm);

        assertThat(vm.V, is(new byte[]{100, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0}));
    }

    @Test
    public void shouldCopyAllRegisters() throws Exception {
        short code = (short) 0xFF65;
        vm.I = 500;
        vm.memory = createMemoryWith(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}, 500);

        opcode.execute(code, vm);

        assertThat(vm.V, is(new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16}));
    }

    @Test
    public void shouldCopyXRegisters() throws Exception {
        short code = (short) 0xF765;
        vm.V = new byte[]{1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16};
        vm.I = 500;
        vm.memory = createMemoryWith(new byte[]{101, 102, 103, 104, 105, 106, 107, 108}, 500);

        opcode.execute(code, vm);

        assertThat(vm.V, is(new byte[]{101, 102, 103, 104, 105, 106, 107, 108, 9, 10, 11, 12, 13, 14, 15, 16}));
    }

    @Test
    public void shouldChangeMemoryPointer() throws Exception {
        short code = (short) 0xFA65;
        short expectedI = (short) (vm.I + 0xA + 1);

        opcode.execute(code, vm);

        assertThat(vm.I, is(expectedI));
    }

    private byte[] createMemoryWith(byte[] content, int startingOn) {
        byte[] bytes = new byte[4096];
        System.arraycopy(content, 0, bytes, startingOn, content.length);
        return bytes;
    }
}