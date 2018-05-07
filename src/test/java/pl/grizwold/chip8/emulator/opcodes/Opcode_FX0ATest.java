package pl.grizwold.chip8.emulator.opcodes;

import lombok.SneakyThrows;
import org.junit.Test;
import pl.grizwold.chip8.emulator.VirtualMachine;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

public class Opcode_FX0ATest {
    private final VirtualMachine vm = new VirtualMachine();
    private final Opcode opcode = new Opcode_FX0A();

    @Test
    public void shouldAcceptCode() throws Exception {
        short code = (short) 0xFA0A;

        assertTrue(opcode.accept(code));
    }

    @Test(timeout = 1000)
    public void shouldWaitForKeystroke() throws Exception {
        short code = (short) 0xFA0A;
        byte vxBefore = 0xf;
        vm.V[0xA] = vxBefore;
        boolean[] isKeypressed = {false};

        ExecutorService executor = Executors.newSingleThreadExecutor();

        executor.execute(() -> {
            sleep(500);

            isKeypressed[0] = true;
            vm.keyboard = 0b1 << 5;
        });

        opcode.execute(code, vm);

        if (!isKeypressed[0]) fail();
        assertTrue(vm.V[0xA] == 6);
    }

    @SneakyThrows
    private void sleep(long time) {
        Thread.sleep(time);
    }
}