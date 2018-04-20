package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

import java.util.concurrent.ThreadLocalRandom;

public class Opcode_CXKK implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0xC000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short kk = (short) (code & 0x00FF);

        byte[] rnd = new byte[1];
        ThreadLocalRandom.current().nextBytes(rnd);

        vm.V[x] = (byte) (kk & rnd[0]);
    }
}
