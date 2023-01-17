package eltech.client;

import javax.swing.*;
import javax.swing.plaf.nimbus.NimbusLookAndFeel;

public class Application {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new NimbusLookAndFeel());
        } catch (UnsupportedLookAndFeelException e) {
            throw new RuntimeException(e);
        }
        JFrame mainFrame = new MainFrame();

    }
}