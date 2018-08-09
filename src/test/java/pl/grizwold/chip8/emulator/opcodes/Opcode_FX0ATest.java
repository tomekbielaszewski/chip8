package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.assertTrue;

public class Opcode_FX0ATest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX0A();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA0A;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldDecrementProgramCounterWhenKeyIsNotPressed() throws Exception {
        short code = (short) 0xFA0A;
        byte vxBefore = 0xf;
        vm.keyboard = 0;
        vm.V[0xA] = vxBefore;
        vm.PC = 100;

        opcode.execute(code, vm);

        assertTrue(vm.V[0xA] == vxBefore);
        assertTrue(vm.PC == 98);
    }

    @Test
    public void shouldSetRegisterToKeyCodeWhenKey_x_IsPressed() throws Exception {
        for (int i = 0; i < 16; i++) {
            short code = (short) 0xFA0A;
            byte vxBefore = 0xf;
            vm.keyboard = (short) ((0b1 << i) & 0xFFFF);
            vm.V[0xA] = vxBefore;
            vm.PC = 100;

            opcode.execute(code, vm);

            assertTrue(vm.V[0xA] == i);
            assertTrue(vm.PC == 100);
        }
    }
}