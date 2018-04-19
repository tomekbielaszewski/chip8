package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8X06 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0x8006;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> (8));

        vm.V[15] = (byte) (vm.V[x] & 0b00000001);
        vm.V[x] = (byte) ((vm.V[x] & INT_TO_BYTE_MASK) >>> 1);
    }
}
