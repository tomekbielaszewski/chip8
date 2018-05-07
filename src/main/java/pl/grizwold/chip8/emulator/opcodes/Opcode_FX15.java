package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX15 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF015;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        vm.delayTimer = vm.V[x];
    }
}