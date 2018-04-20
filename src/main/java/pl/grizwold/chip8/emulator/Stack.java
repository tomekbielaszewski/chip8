package pl.grizwold.chip8.emulator;

public class Stack {
    private short[] stack = new short[16];
    private byte stackPointer;

    public void push(short subroutine) {
        if(stackPointer > 15) return;
        stack[stackPointer++] = subroutine;
    }

    public short pop() {
        if(stackPointer <= 0) return stack[0];
        return stack[--stackPointer];
    }
}
