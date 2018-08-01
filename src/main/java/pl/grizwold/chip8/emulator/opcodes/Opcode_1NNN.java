package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_1NNN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0x1000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        vm.PC = (short) (0x0FFF & code);
        vm.PC -= 2;
    }

    @Override
    public String getDescription() {
        return "Jump to location nnn";
    }

    @Override
    public String getAsm(short code) {
        return "JP " + Integer.toHexString((0x0FFF & code) & 0xFFF);
    }
}
