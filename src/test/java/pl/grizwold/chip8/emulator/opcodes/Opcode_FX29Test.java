package pl.grizwold.chip8.emulator.opcodes;

import org.junit.Test;
import pl.grizwold.chip8.emulator.Sprites;
import pl.grizwold.chip8.emulator.VirtualMachine;

import static org.hamcrest.Matchers.is;
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
    public void shouldPointIToHexCharacterSpriteInVx_0() throws Exception {
        short code = (short) 0xFA29;
        vm.V[0xA] = 0x0;

        opcode.execute(code, vm);

        byte[] character = new byte[5];
        System.arraycopy(vm.memory, vm.I, character, 0, 5);

        assertThat(character, is(Sprites.SPRITE_0));
    }
}