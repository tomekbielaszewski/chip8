package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_2NNN implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF000) == 0x2000;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        vm.stack.push(vm.PC);
        vm.PC = (short) (0x0FFF & code);
    }

    @Override
    public String getDescription() {
        return "Call subroutine at nnn";
    }

    @Override
    public String getAsm(short code) {
        return "CALL " + Integer.toHexString((0x0FFF & code) & 0xFFF);
    }
}
