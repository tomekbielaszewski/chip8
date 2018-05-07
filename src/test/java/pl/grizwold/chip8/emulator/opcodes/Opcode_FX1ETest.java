package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX1ETest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX1E();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA1E;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetVxToDelayTimer() throws Exception {
        short code = (short) 0xFA1E;
        byte vx = 0xf;
        byte iBefore = 0x9;
        vm.V[0xA] = vx;
        vm.I = iBefore;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xA] == vx);
        assertTrue(vm.I != iBefore);
        assertTrue(vm.I == (vx + iBefore));
    }
}