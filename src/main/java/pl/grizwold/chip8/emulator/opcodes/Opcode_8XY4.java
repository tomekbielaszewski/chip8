package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8XY4 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x8004;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short y = (short) ((code & 0x00F0) >>> 4);

        int sum = vm.V[y] + vm.V[x];
        vm.V[x] = (byte) sum;

        if(sum > vm.V[x]) {
            vm.V[15] = 1;
        } else {
            vm.V[15] = 0;
        }
    }
}
