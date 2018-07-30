package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.junit.Assert.*;

public class Opcode_FX29Test {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX29();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA29;

        assertTrue(opcode.accept(code));
    }

    @Test
    public void shouldPointIToHexCharacterSpriteInVx() throws Exception {
        short code = (short) 0xFA29;

        opcode.execute(code, vm);
    }
}