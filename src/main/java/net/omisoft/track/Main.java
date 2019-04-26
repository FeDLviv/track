package net.omisoft.track;

import org.jnativehook.GlobalScreen;
import org.jnativehook.NativeHookException;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class Main {

    private final static String DIR_NAME = "track-screen";
    private final static Path SCREENSHOT_FILES_PATH = Paths.get(System.getProperty("user.home"), DIR_NAME);
    private final static int SCREENSHOT_MINUTES_PERIOD = 1;
    private final static ScheduledExecutorService SCHEDULED = Executors.newScheduledThreadPool(1);

    public static void main(String[] args) throws IOException {
        Path path = Paths.get(System.getProperty("user.home"), DIR_NAME);
        if (!Files.exists(path)) {
            Files.createDirectory(path);
        }

        runScreenshot();
        runMouseAndKeyboardListener();
    }

    private static void runScreenshot() {
        SCHEDULED.scheduleAtFixedRate(() -> {
            try {
                BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
                String fileName = UUID.randomUUID().toString() + ".png";
                ImageIO.write(image, "png", SCREENSHOT_FILES_PATH.resolve(fileName).toFile());
            } catch (AWTException | IOException e) {
                e.printStackTrace();
            }
        }, 0, SCREENSHOT_MINUTES_PERIOD, TimeUnit.MINUTES);
    }

    private static void runMouseAndKeyboardListener() {
        try {
            GlobalScreen.registerNativeHook();
        } catch (NativeHookException ex) {
            System.err.println("SORRY !!!There was a problem registering the native hook!!!");
            System.err.println(ex.getMessage());
        }
        GlobalScreen.addNativeKeyListener(new GlobalNativeKeyListenerExample());
    }

}