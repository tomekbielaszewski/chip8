package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_6XKK implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0x6000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        byte kk = (byte) (code & 0x00FF);

        vm.V[x] = kk;
    }

    @Override
    public String getDescription() {
        return "Set Vx = kk";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD V%X, %X", ((code & 0x0F00) >>> 8), (code & 0x00FF));
    }
}
