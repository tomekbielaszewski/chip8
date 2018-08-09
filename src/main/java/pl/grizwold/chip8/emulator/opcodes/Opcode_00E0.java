package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_00E0 implements Opcode {

    @Override
    public boolean accept(short code) {
        return code == 0x00e0;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        for (int x = 0; x < vm.screen.length; x++) {
            for (int y = 0; y < vm.screen[x].length; y++) {
                vm.screen[x][y] = false;
            }
        }
        vm.repaint = true;
    }

    @Override
    public String getDescription() {
        return "Clear the display";
    }

    @Override
    public String getAsm(short code) {
        return "CLS";
    }
}
