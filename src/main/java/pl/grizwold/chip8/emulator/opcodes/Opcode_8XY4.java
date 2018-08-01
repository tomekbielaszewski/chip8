package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8XY4 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF00F) == 0x8004;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short y = (short) ((code & 0x00F0) >>> 4);

        int sum = vm.V[y] + vm.V[x];
        vm.V[x] = (byte) sum;

        if(sum > vm.V[x]) {
            vm.V[15] = 1;
        } else {
            vm.V[15] = 0;
        }
    }

    @Override
    public String getDescription() {
        return "Set Vx = Vx + Vy, set VF = carry. If the result is greater than 8 bits (i.e., > 255,) VF is set to 1, otherwise 0";
    }

    @Override
    public String getAsm(short code) {
        return String.format("ADD V%X, V%X", ((code & 0x0F00) >>> 8), ((code & 0x00F0) >>> 4));
    }
}
