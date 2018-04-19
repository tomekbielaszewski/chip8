package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_4XKK implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0x4000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> (8*2));
        short kk = (short) (code & 0x00FF);

        if(vm.V[x] != kk) {
            vm.PC += 2;
        }
    }
}