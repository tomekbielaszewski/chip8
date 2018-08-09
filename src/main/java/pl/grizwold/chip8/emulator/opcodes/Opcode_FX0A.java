package pl.grizwold.chip8.emulator.opcodes;

import lombok.SneakyThrows;
import pl.grizwold.chip8.emulator.VirtualMachine;

public class Opcode_FX0A implements Opcode {
    @Override
    public boolean accept(short code) {
        return (code & 0xF0FF) == 0xF00A;
    }

    @Override
    public void execute(short code, VirtualMachine vm) {
        short x = (short) ((code & 0x0F00) >>> 8);

        if(isKeyPressed(vm.keyboard)) {
            vm.V[x] = getKeyValue(vm.keyboard);
        } else {
            vm.PC -= 2;
        }
    }

    private byte getKeyValue(short keyboard) {
        for (byte bitNum = 1; bitNum <= 16; bitNum++) {
            if (isNthBitOn(keyboard, bitNum)) {
                return bitNum;
            }
        }
        return -1; //should not happen
    }

    @SneakyThrows
    private boolean isKeyPressed(short keyboard) {
        Thread.sleep(10);
        return keyboard != 0;
    }

    private boolean isNthBitOn(short num, byte whichBit) {
        return (num & (1 << (whichBit - 1))) >= 1;
    }

    @Override
    public String getDescription() {
        return "Wait for a key press, store the value of the key in Vx";
    }

    @Override
    public String getAsm(short code) {
        return String.format("LD V%X, K", ((code & 0x0F00) >>> 8));
    }
}