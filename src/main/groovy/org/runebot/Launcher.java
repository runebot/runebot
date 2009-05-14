package org.runebot;

import javax.swing.UIManager;
import org.runebot.gui.MainWindow;
import com.google.inject.Guice;

public class Launcher {
    public static void main(String[] args) throws Exception {
        UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        Guice.createInjector().getInstance(MainWindow.class).createAndShow();
    }
}
