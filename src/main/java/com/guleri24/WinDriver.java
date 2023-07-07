package com.guleri24;

import java.awt.*;
import java.io.File;
import java.io.IOException;

public class WinDriver {
    public static void start() {
        try {
            Desktop desktop = Desktop.getDesktop();

            File winAppDriver = new File("C:\\Program Files\\Windows Application Driver\\WinAppDriver.exe");

            // check if there is support for Desktop or not
            if (!Desktop.isDesktopSupported()) {
                System.out.println("Desktop not supported");
                return;
            }
            if (winAppDriver.exists()) {
                System.out.println("Opening WinAppDriver.exe\n");
                desktop.open(winAppDriver);
            }
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Encountered Exception\n");
            throw new RuntimeException(exception);
        }
    }

    public static void stop() {
        try {
            ProcessBuilder processBuilder = new ProcessBuilder("taskkill", "/f", "/IM", "WinAppDriver.app");
            processBuilder.start();
        } catch (IOException exception) {
            exception.printStackTrace();
            System.out.println("Encountered Exception\n");
            throw new RuntimeException(exception);
        }
    }
}