package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8XY1 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x8001;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short y = (short) ((code & 0x00F0) >>> 4);

        vm.V[x] = (byte) (vm.V[y] | vm.V[x]);
    }

    @Override
    public String getDescription() {
        return "Vx = Vx OR Vy";
    }

    @Override
    public String getAsm(short code) {
        return String.format("OR V%X, V%X", ((code & 0x0F00) >>> 8), ((code & 0x00F0) >>> 4));
    }
}
