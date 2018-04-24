package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_EXA1 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xE0A1;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        if((vm.keyboard & (1 << vm.V[x])) == 0) {
            vm.PC += 2;
        }
    }
}