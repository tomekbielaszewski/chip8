package pl.grizwold.chip8.emulator;

import lombok.SneakyThrows;

public class VirtualMachine {
    private final short PROGRAM_START_ADDRESS = 0x200;
    private final Sprites sprites = new Sprites();
    private final Opcodes opcodes = new Opcodes();

    private int frequency;
    public byte[] memory = new byte[4096];
    public byte[] V = new byte[16]; //VX: V0, ..., VF

    public short I = 0; //memory program pointer; index register
    public short PC = 0; //program counter

    public Stack stack = new Stack();

    public byte delayTimer = 0;
    public byte soundTimer = 0;

    public short keyboard = 0;
    public boolean[][] screen = new boolean[64][32];

    public VirtualMachine(int frequency) {
        initMemory();
        if (frequency >= 1000) frequency = 999;
        if (frequency <= 0) frequency = 1;
        this.frequency = frequency;
    }

    public void start(byte[] cartridge) {
        loadMemory(cartridge);
        boolean exit = false;

        while (!exit) {
            cpu();
            timers();
            exit = shouldExit();

            throttle();
        }
    }

    private void loadMemory(byte[] cartridge) {
        System.arraycopy(cartridge, 0, this.memory, PROGRAM_START_ADDRESS, cartridge.length);
    }

    private void cpu() {
        short code = (short) ((this.memory[PC] << 8) + this.memory[PC + 1]);

        opcodes.getOpcodes().stream()
                .filter(opcode -> opcode.accept(code))
                .findFirst().orElseThrow(UnsupportedOperationException::new)
                .execute(code, this);

        PC += 2;
    }

    private void timers() {
        delayTimer = (byte) (delayTimer > 0 ? delayTimer - 1 : 0);
        soundTimer = (byte) (soundTimer > 0 ? soundTimer - 1 : 0);
    }

    private boolean shouldExit() {
        return this.keyboard == (short) 0b1111111111111111;
    }

    @SneakyThrows
    private void throttle() {
        Thread.sleep(this.frequency / 1000);
    }

    private void initMemory() {
        byte[][] sprites = this.sprites.getSprites();

        for (int i = 0; i < sprites.length; i++) {
            byte[] sprite = sprites[i];
            System.arraycopy(sprite, 0, memory, i * Sprites.SPRITE_SIZE, sprite.length);
        }
    }
}