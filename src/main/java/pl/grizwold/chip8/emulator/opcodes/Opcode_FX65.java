package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX65 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF065;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        System.arraycopy(vm.memory, vm.I, vm.V, 0, x + 1);
        vm.I += x + 1;
    }
}