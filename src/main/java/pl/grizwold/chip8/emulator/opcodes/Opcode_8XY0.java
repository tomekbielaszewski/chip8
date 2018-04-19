package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8XY0 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x8000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> (8*2));
        short y = (short) ((code & 0x00F0) >>> (8));

        vm.V[x] = vm.V[y];
    }
}