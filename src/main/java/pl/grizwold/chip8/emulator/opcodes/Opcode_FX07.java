package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX07 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF007;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        vm.V[x] = vm.delayTimer;
    }

    @Override
    public String getDescription() {
        return "Set Vx = delay timer value";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD V%X, DT", ((code & 0x0F00) >>> 8));
    }
}