package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX15Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX15();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA15;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetVxToDelayTimer() throws Exception {
        short code = (short) 0xFA15;
        byte vx = 0xf;
        byte delayTimerBefore = 0x9;
        vm.V[0xA] = vx;
        vm.delayTimer = delayTimerBefore;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xA] == vx);
        assertTrue(vm.delayTimer != delayTimerBefore);
        assertTrue(vm.delayTimer == vx);
    }
}