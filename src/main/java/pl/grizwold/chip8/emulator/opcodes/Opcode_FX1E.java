package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX1E implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF01E;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        vm.I = (short) (vm.I + vm.V[x]);
    }

    @Override
    public String getDescription() {
        return "Set I = I + Vx";
    }

    @Override
    public String getAsm(short code) {
        return String.format("ADD I, V%X", ((code & 0x0F00) >>> 8));
    }
}