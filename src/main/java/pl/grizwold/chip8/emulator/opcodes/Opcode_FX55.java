package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Opcode_FX55 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF055;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        System.arraycopy(vm.V, 0, vm.memory, vm.I, x+1);
        vm.I += x + 1;
    }

    @Override
    public String getDescription() {
        return "Store registers V0 through Vx in memory starting at location I";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD [I], V%X", ((code & 0x0F00) >>> 8));
    }
}