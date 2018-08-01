package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.VirtualMachine;

import java.util.Arrays;

import static java.lang.Integer.toHexString;

public interface Opcode {
    int INT_TO_BYTE_MASK = 0xff;

    boolean accept(short code);
    void execute(short code, VirtualMachine vm);
    String getDescription();
    String getAsm(short code);

    default void execute_(short code, VirtualMachine vm) {
        System.out.println(String.format("0x%-3X: [%-4X] %-15s\\\\%-115s |  I = %-5X V%s", (vm.PC & 0xFF), (code & 0xFFFF), getAsm(code), getDescription(), vm.I, Arrays.toString(vm.V)));
        this.execute(code, vm);
    }
}
