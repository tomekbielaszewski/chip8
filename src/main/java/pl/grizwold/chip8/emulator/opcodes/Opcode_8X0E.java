package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_8X0E implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0x800E;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        vm.V[15] = (byte) ((vm.V[x] & INT_TO_BYTE_MASK) >>> 7);
        vm.V[x] = (byte) (vm.V[x] << 1);
    }

    @Override
    public String getDescription() {
        return "If the most-significant bit of Vx is 1, then VF is set to 1, otherwise to 0. Then Vx is multiplied by 2.";
    }

    @Override
    public String getAsm(short code) {
        return String.format("SHL V%X", ((code & 0x0F00) >>> 8));
    }
}
