package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8XY7 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x8007;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> (8));
        short y = (short) ((code & 0x00F0) >>> (4));

        if(vm.V[x] > vm.V[y]) {
            vm.V[15] = 1;
        } else {
            vm.V[15] = 0;
        }

        vm.V[x] = (byte) (vm.V[y] - vm.V[x]);
    }
}
