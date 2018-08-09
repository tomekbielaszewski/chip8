package pl.grizwold.chip8;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import pl.grizwold.chip8.emulator.VirtualMachine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class Chip8EmulatorStarter extends Application {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {
        System.setOut(new Logger("out.log", System.out));
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        Group root = new Group();
        Canvas canvas = new Canvas(640, 320);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        boolean[] keys = new boolean[16];
        KeyCode[] keyCodes = new KeyCode[]{
                KeyCode.DIGIT1, KeyCode.DIGIT2, KeyCode.DIGIT3, KeyCode.DIGIT4,
                KeyCode.Q, KeyCode.W, KeyCode.E, KeyCode.R,
                KeyCode.A, KeyCode.S, KeyCode.D, KeyCode.F,
                KeyCode.Z, KeyCode.X, KeyCode.C, KeyCode.V
        };

        primaryStage.getScene().setOnKeyPressed(event -> {
            for (int i = 0; i < keyCodes.length; i++) {
                keys[i] = keyCodes[i].equals(event.getCode());
            }
        });

        primaryStage.getScene().setOnKeyReleased(event -> {
            for (int i = 0; i < keys.length; i++) {
                keys[i] = false;
            }
        });

        executorService.submit(() -> {
            VirtualMachine vm = new VirtualMachine(1000);
            try {
                InputStream inputStream = Files.newInputStream(Paths.get("C:\\Programming\\Prv\\chip8\\src\\main\\resources\\test\\Keypad Test [Hap, 2006].ch8"));
                byte[] bytes = IOUtils.toByteArray(inputStream);
                vm.start(bytes, (screen) -> drawShapes(gc, (boolean[][]) screen), keyboardSupplier(keys));
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
    }

    @Override
    public void stop() throws Exception {
        super.stop();
        executorService.shutdownNow();
    }

    private static void drawShapes(GraphicsContext gc, boolean[][] screen) {
        gc.clearRect(0, 0, 640, 480);
        for (int x = 0; x < screen.length; x++) {
            for (int y = 0; y < screen[x].length; y++) {
                if (screen[x][y]) {
                    gc.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }

    private static Supplier<Short> keyboardSupplier(boolean[] keys) {
        return () -> {
            short keyboard = 0;
            for (int i = 0; i < keys.length; i++) {
                if (keys[i]) {
                    keyboard = (short) ((1 << i) & 0xFFFF);
                }
            }
            return keyboard;
        };
    }
}
