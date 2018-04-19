package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public interface Opcode {
    int INT_TO_BYTE_MASK = 0xff;

    boolean accept(short code);
    void execute(short code, VirtualMachine vm);
}
