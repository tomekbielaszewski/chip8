package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX33 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF033;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        byte vx = vm.V[x];

        byte ones;
        byte tens;
        byte hundreds;

        if (vx == 0) {
            ones = 0;
            tens = 0;
            hundreds = 0;
        } else {
            ones = (byte) (vx % 10);
            tens = (byte) ((vx % 100) / 10);
            hundreds = (byte) (vx / 100);
        }

        vm.memory[vm.I] = hundreds;
        vm.memory[vm.I + 1] = tens;
        vm.memory[vm.I + 2] = ones;
    }
}