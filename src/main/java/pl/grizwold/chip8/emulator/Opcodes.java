package pl.grizwold.chip8.emulator;

import pl.grizwold.chip8.emulator.opcodes.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Opcodes {
    private List<Opcode> opcodes = new ArrayList<>(34);

    public Opcodes() {
        opcodes.addAll(Arrays.asList(
                new Opcode_00E0(),
                new Opcode_00EE(),
                new Opcode_1NNN(),
                new Opcode_2NNN(),
                new Opcode_3XKK(),
                new Opcode_4XKK(),
                new Opcode_5XY0(),
                new Opcode_6XKK(),
                new Opcode_7XKK(),
                new Opcode_8X0E(),
                new Opcode_8X06(),
                new Opcode_8XY0(),
                new Opcode_8XY1(),
                new Opcode_8XY2(),
                new Opcode_8XY3(),
                new Opcode_8XY4(),
                new Opcode_8XY5(),
                new Opcode_8XY7(),
                new Opcode_9XY0(),
                new Opcode_ANNN(),
                new Opcode_BNNN(),
                new Opcode_CXKK(),
                new Opcode_DXYN(),
                new Opcode_EX9E(),
                new Opcode_EXA1(),
                new Opcode_FX0A(),
                new Opcode_FX1E(),
                new Opcode_FX07(),
                new Opcode_FX15(),
                new Opcode_FX18(),
                new Opcode_FX29(),
                new Opcode_FX33(),
                new Opcode_FX55(),
                new Opcode_FX65()
        ));
    }

    public List<Opcode> getOpcodes() {
        return opcodes;
    }
}
