package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX18Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX18();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA18;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldSetVxToDelayTimer() throws Exception {
        short code = (short) 0xFA18;
        byte vx = 0xf;
        byte soundTimerBefore = 0x9;
        vm.V[0xA] = vx;
        vm.soundTimer = soundTimerBefore;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xA] == vx);
        assertTrue(vm.soundTimer != soundTimerBefore);
        assertTrue(vm.soundTimer == vx);
    }
}