package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_DXYN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0xD000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short _x = (short) ((code & 0x0F00) >>> 8);
        short _y = (short) ((code & 0x00F0) >>> 4);
        short n = (short) (code & 0x000F);

        byte x = vm.V[_x];
        byte y = vm.V[_y];

        byte[] sprite = loadSprite(n, vm);
        boolean collision = drawSprite(x, y, sprite, vm);
        vm.V[0xF] = (byte) (collision ? 1 : 0);
    }

    private byte[] loadSprite(short n, VirtualMachine vm) {
        byte[] sprite = new byte[n];
        System.arraycopy(vm.memory, vm.I, sprite, 0, n);
        return sprite;
    }

    private boolean drawSprite(int x, int y, byte[] sprite, VirtualMachine vm) {
        boolean collision = false;

        for (int spriteNum = 0; spriteNum < sprite.length; spriteNum++) {
            byte spritePart = sprite[spriteNum];
            collision |= drawSpritePart(x + (8 * spriteNum), y, spritePart, vm);
        }

        return collision;
    }

    private boolean drawSpritePart(int x, int y, byte spritePart, VirtualMachine vm) {
        boolean collision = false;
        spritePart = reverse(spritePart);

        for (int bitNum = 1; bitNum <= 8; bitNum++) {
            int _x = x + bitNum - 1;
            _x = _x >= 64 ? _x - 64 : _x;
            boolean pixelBefore = vm.screen[_x][y];
            vm.screen[_x][y] ^= isNthBitOn(spritePart, bitNum);

            collision |= (pixelBefore && !vm.screen[_x][y]);
        }

        return collision;
    }

    private boolean isNthBitOn(byte _byte, int bitNum) {
        return (_byte & (1 << (bitNum - 1))) >= 1;
    }

    private byte reverse(byte _byte) {
        return (byte) (Integer.reverse(_byte) >>> (Integer.SIZE - Byte.SIZE));
    }
}
