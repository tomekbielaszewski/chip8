package pl.grizwold.chip8;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;
import org.apache.commons.io.IOUtils;
import pl.grizwold.chip8.emulator.VirtualMachine;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Chip8EmulatorStarter extends Application {
    private ExecutorService executorService = Executors.newSingleThreadExecutor();

    public static void main(String[] args) throws IOException {
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

        executorService.submit(() -> {
            VirtualMachine vm = new VirtualMachine(50);
            try {
                InputStream inputStream = Files.newInputStream(Paths.get("C:\\Programming\\Prv\\chip8\\src\\main\\resources\\test\\C8PIC.ch8"));
                byte[] bytes = IOUtils.toByteArray(inputStream);
                vm.start(bytes, (screen) -> drawShapes(gc, (boolean[][]) screen));
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
        for (int x = 0; x < screen.length; x++) {
            for (int y = 0; y < screen[x].length; y++) {
                if (screen[x][y]) {
                    gc.fillRect(x * 10, y * 10, 10, 10);
                }
            }
        }
    }
}
