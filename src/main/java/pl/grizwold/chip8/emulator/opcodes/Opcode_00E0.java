package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_00E0 implements Opcode {

    @Override
    public boolean accept(short code) {
        return code == 0x00e0;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        for (int i = 0; i < vm.screen.length; i++) {
            vm.screen[i] = 0;
        }
    }
}
