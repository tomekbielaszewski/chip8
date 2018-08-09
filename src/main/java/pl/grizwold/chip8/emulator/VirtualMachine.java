package pl.grizwold.chip8.emulator;

import lombok.SneakyThrows;

import java.util.function.Consumer;
import java.util.function.Supplier;

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
    public boolean repaint;

    private boolean finished;

    public VirtualMachine() {
        initMemory();
        this.frequency = 50;
        this.PC = PROGRAM_START_ADDRESS;
    }

    public VirtualMachine(int frequency) {
        initMemory();
        if (frequency >= 1000) frequency = 999;
        if (frequency <= 0) frequency = 1;
        this.frequency = frequency;
        this.PC = PROGRAM_START_ADDRESS;
    }

    public void start(byte[] cartridge, Consumer drawer, Supplier<Short> keyboardSupplier) {
        loadMemory(cartridge);
        boolean exit = false;

        while (!exit) {
            cpu();
            timers();
            exit = shouldExit();
            if(this.repaint) {
                drawer.accept(this.screen);
                this.repaint = false;
            }
            keyboard = keyboardSupplier.get();

            throttle();
        }
    }

    private void loadMemory(byte[] cartridge) {
        System.out.println(String.format("Loaded cartridge. Max Program Counter %d", cartridge.length));

        System.out.println("## Cartridge content: ##");
        printBytes(cartridge);

        System.arraycopy(cartridge, 0, this.memory, PROGRAM_START_ADDRESS, cartridge.length);

        System.out.println("## Memory content: ##");
        printBytes(memory);
    }

    private void printBytes(byte[] cartridge) {
        StringBuilder sb = new StringBuilder();
        int i = 0;
        for (; i < cartridge.length; i++) {
            byte _byte = cartridge[i];

            if (i % 16 == 0) {
                System.out.println(sb.toString());
                sb.delete(0, sb.length());

                sb.append(String.format("%04d | ", i));
            }

            sb.append(String.format("%02x ", _byte));
        }
        System.out.println(sb.toString() + "\n");
    }

    private void cpu() {
        if (PC < memory.length && PC >= 0) {
            short code = (short) (((memory[PC] & 0xFF) << 8) | (memory[PC + 1] & 0xFF));

            opcodes.getOpcodes().stream()
                    .filter(opcode -> opcode.accept(code))
                    .findFirst().orElseThrow(() -> new UnsupportedOperationException(String.format("Code %s, PC %d", Integer.toHexString(code & 0xffff), PC)))
                    .execute_(code, this);

            PC += 2;
        } else {
            if (!finished) {
                System.out.println("Chip8 Program finished running. PC IOOB.");
                finished = true;
            }
        }
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
        Thread.sleep(1000 / this.frequency);
    }

    private void initMemory() {
        byte[][] sprites = this.sprites.getSprites();

        for (int i = 0; i < sprites.length; i++) {
            byte[] sprite = sprites[i];
            System.arraycopy(sprite, 0, memory, i * Sprites.SPRITE_SIZE, sprite.length);
        }
    }
}