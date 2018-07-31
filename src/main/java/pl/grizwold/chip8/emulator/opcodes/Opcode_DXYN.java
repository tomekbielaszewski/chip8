package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.concurrent.ThreadLocalRandom;

public class Opcode_DXYN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0xD000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short y = (short) ((code & 0x00F0) >>> 4);
        short n = (short) (code & 0x000F);

        byte sprite = loadSprite(n, vm);
        boolean collision = drawSprite(x, y, sprite, vm);
        vm.V[15] = (byte) (collision ? 1 : 0);
    }

    private byte loadSprite(short n, VirtualMachine vm) {
        throw new NotImplementedException();
    }

    private boolean drawSprite(short x, short y, byte sprite, VirtualMachine vm) {
        throw new NotImplementedException();
    }


}
