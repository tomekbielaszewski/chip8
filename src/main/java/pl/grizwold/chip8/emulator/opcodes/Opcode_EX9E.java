package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_EX9E implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xE09E;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        if((vm.keyboard & (1 << vm.V[x])) > 0) {
            vm.PC += 2;
        }
    }

    @Override
    public String getDescription() {
        return "Skip next instruction if key with the value of Vx is pressed";
    }

    @Override
    public String getAsm(short code) {
        return String.format("SKP V%X", ((code & 0x0F00) >>> 8));
    }
}
