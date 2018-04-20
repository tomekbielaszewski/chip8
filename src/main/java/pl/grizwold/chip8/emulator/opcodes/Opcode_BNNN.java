package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_BNNN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0xB000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        vm.PC = (short) ((0x0FFF & code) + vm.V[0]);
    }
}
