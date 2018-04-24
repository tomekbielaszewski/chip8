package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX07Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX07();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA07;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetVxToDelayTimer() throws Exception {
        short code = (short) 0xFA07;
        byte vxBefore = 0xf;
        byte delayTimer = 0x9;
        vm.V[0xA] = vxBefore;
        vm.delayTimer = delayTimer;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xA] == delayTimer);
        assertTrue(vm.V[0xA] != vxBefore);
    }
}