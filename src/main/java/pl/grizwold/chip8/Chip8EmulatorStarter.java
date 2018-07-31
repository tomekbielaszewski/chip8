package pl.grizwold.chip8;

import org.apache.commons.io.IOUtils;
import pl.grizwold.chip8.emulator.VirtualMachine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Chip8EmulatorStarter {

    public static void main(String[] args) throws IOException {
        InputStream inputStream = Files.newInputStream(Paths.get("C:\\Programming\\Prv\\chip8\\src\\main\\resources\\15PUZZLE.ch8"));
        byte[] bytes = IOUtils.toByteArray(inputStream);

        VirtualMachine vm = new VirtualMachine(50);
        vm.start(bytes);

    }
}
