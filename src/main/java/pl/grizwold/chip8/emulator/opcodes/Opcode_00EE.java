package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_00EE implements Opcode {
    @Override
    public boolean accept(short code) {
        return code == 0x00EE;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        vm.PC = vm.stack.pop();
    }

    @Override
    public String getDescription() {
        return "Return from a subroutine";
    }

    @Override
    public String getAsm(short code) {
        return "RET";
    }
}
