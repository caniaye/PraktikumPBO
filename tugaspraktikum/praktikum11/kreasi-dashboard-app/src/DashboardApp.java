import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import view.FrameUtama;

import java.util.Collections;

public class DashboardApp {

    public static void main(String[] args) {

        try {
            FlatLaf.setGlobalExtraDefaults(Collections.singletonMap("@accentColor", "#FF69B4"));

            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) { 
            System.err.println(e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            new FrameUtama().setVisible(true); 
        });
    }
}