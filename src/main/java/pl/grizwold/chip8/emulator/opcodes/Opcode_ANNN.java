package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_ANNN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0xA000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        vm.I = (short) (0x0FFF & code);
    }

    @Override
    public String getDescription() {
        return "Set I = nnn";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD I, %X", (code & 0x0FFF));
    }
}
