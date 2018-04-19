package pl.grizwold.chip8.emulator;

public class Stack {
    private final static long TOP_ELEMENT_MASK = 0xFF00000000000000L;

    private long stack = 0;

    public void push(short subroutine) {
        long topSubroutine = (long) subroutine << (64-8);
        this.stack = this.stack >>> (8*2);
        this.stack = this.stack | topSubroutine;
    }

    public short pop() {
        long topSubroutine = (this.stack & TOP_ELEMENT_MASK) >>> (64-8);
        this.stack = this.stack << (8*2);
        return (short) topSubroutine;
    }
}
