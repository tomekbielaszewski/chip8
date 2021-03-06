package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX18 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF018;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        vm.soundTimer = vm.V[x];
    }

    @Override
    public String getDescription() {
        return "Set sound timer = Vx";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD ST, V%X", ((code & 0x0F00) >>> 8));
    }
}