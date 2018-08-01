package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_5XY0 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x5000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short y = (short) ((code & 0x00F0) >>> 4);

        if(vm.V[x] == vm.V[y]) {
            vm.PC += 2;
        }
    }

    @Override
    public String getDescription() {
        return "Skip next instruction if Vx = Vy";
    }

    @Override
    public String getAsm(short code) {
        return String.format("SE V%X, V%X", ((code & 0x0F00) >>> 8), ((code & 0x00F0) >>> 4));
    }
}
