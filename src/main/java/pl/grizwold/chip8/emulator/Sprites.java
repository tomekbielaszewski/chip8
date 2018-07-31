package pl.grizwold.chip8.emulator;

public class Sprites {
    public static final int SPRITE_SIZE = 5;
    public final byte[] SPRITE_0 = new byte[]{
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0x90,
            (byte) 0x90,
            (byte) 0xF0
    };
    public final byte[] SPRITE_1 = new byte[]{
            (byte) 0x20,
            (byte) 0x60,
            (byte) 0x20,
            (byte) 0x20,
            (byte) 0x70
    };
    public final byte[] SPRITE_2 = new byte[]{
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0
    };
    public final byte[] SPRITE_3 = new byte[]{
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0xF0
    };
    public final byte[] SPRITE_4 = new byte[]{
            (byte) 0x90,
            (byte) 0x90,
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0x10
    };
    public final byte[] SPRITE_5 = new byte[]{
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0xF0
    };
    public final byte[] SPRITE_6 = new byte[]{
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0xF0
    };
    public final byte[] SPRITE_7 = new byte[]{
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0x20,
            (byte) 0x40,
            (byte) 0x40
    };
    public final byte[] SPRITE_8 = new byte[]{
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0xF0
    };
    public final byte[] SPRITE_9 = new byte[]{
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0xF0,
            (byte) 0x10,
            (byte) 0xF0
    };
    public final byte[] SPRITE_A = new byte[]{
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0xF0,
            (byte) 0x90,
            (byte) 0x90
    };
    public final byte[] SPRITE_B = new byte[]{
            (byte) 0xE0,
            (byte) 0x90,
            (byte) 0xE0,
            (byte) 0x90,
            (byte) 0xE0
    };
    public final byte[] SPRITE_C = new byte[]{
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0x80,
            (byte) 0x80,
            (byte) 0xF0
    };
    public final byte[] SPRITE_D = new byte[]{
            (byte) 0xE0,
            (byte) 0x90,
            (byte) 0x90,
            (byte) 0x90,
            (byte) 0xE0
    };
    public final byte[] SPRITE_E = new byte[]{
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0
    };
    public final byte[] SPRITE_F = new byte[]{
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0xF0,
            (byte) 0x80,
            (byte) 0x80
    };

    private byte[][] sprites = new byte[][]{
            SPRITE_0,
            SPRITE_1,
            SPRITE_2,
            SPRITE_3,
            SPRITE_4,
            SPRITE_5,
            SPRITE_6,
            SPRITE_7,
            SPRITE_8,
            SPRITE_9,
            SPRITE_A,
            SPRITE_B,
            SPRITE_C,
            SPRITE_D,
            SPRITE_E,
            SPRITE_F

    };

    public byte[][] getSprites() {
        return sprites;
    }
}
