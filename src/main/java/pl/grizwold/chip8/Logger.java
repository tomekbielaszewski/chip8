package pl.grizwold.chip8;

import java.io.FileNotFoundException;
import java.io.PrintStream;

public class Logger extends PrintStream {
    private PrintStream standard;

    public Logger(String fileName, PrintStream standardPrintStream) throws FileNotFoundException {
        super(fileName);
        this.standard = standardPrintStream;
    }

    @Override
    public void println(Object x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println() {
        super.println();
        standard.println();
    }

    @Override
    public void println(int x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(char x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(long x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(float x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(char[] x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(double x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(String x) {
        super.println(x);
        standard.println(x);
    }

    @Override
    public void println(boolean x) {
        super.println(x);
        standard.println(x);
    }
}
