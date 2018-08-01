package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_4XKK implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0x4000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);
        short kk = (short) (code & 0x00FF);

        if(vm.V[x] != kk) {
            vm.PC += 2;
        }
    }

    @Override
    public String getDescription() {
        return "Skip next instruction if Vx != kk";
    }

    @Override
    public String getAsm(short code) {
        return String.format("SNE V%X, %X", ((code & 0x0F00) >>> 8), (code & 0x00FF));
    }
}
