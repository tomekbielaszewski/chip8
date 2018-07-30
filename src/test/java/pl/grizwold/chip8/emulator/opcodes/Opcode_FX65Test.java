package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import static org.junit.Assert.*;

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
        short code = (short) 0xFA65;

        opcode.execute(code, vm);

        throw new NotImplementedException();
    }

    @Test
    public void shouldCopyAllRegisters() throws Exception {
        short code = (short) 0xFA65;

        opcode.execute(code, vm);

        throw new NotImplementedException();
    }

    @Test
    public void shouldCopyXRegisters() throws Exception {
        short code = (short) 0xFA65;

        opcode.execute(code, vm);

        throw new NotImplementedException();
    }

    @Test
    public void shouldChangeMemoryPointer() throws Exception {
        short code = (short) 0xFA65;

        opcode.execute(code, vm);

        throw new NotImplementedException();
    }
}