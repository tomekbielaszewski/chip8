package pl.grizwold.chip8.emulator;

public class VirtualMachine {
    private final short PROGRAM_START_ADDRESS = 0x200;

    public byte[] memory = new byte[4096];
    public byte[] V = new byte[16]; //VX: V0, ..., VF

    public short I = 0; //memory program pointer; index register
    public short PC = 0; //program counter

    public Stack stack = new Stack();

    public byte delayTimer = 0;
    public byte soundTimer = 0;

    public short keyboard = 0;
    public byte[] screen = new byte[64*32];
}