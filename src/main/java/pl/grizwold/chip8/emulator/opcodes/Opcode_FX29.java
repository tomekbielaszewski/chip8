package pl.grizwold.chip8.emulator.opcodes;

import pl.grizwold.chip8.emulator.Sprites;
import pl.grizwold.chip8.emulator.VirtualMachine;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

public class Opcode_FX29 implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF029;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        byte spriteSymbol = vm.V[x];
        vm.I = (short) (spriteSymbol * Sprites.SPRITE_SIZE);
    }
}