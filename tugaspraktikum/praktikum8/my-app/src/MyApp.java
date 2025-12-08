import java.awt.Dimension;

import javax.swing.JFrame;
import javax.swing.SwingUtilities;

public class MyApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Main Frame");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(1024, 768));
            frame.setMinimumSize(new Dimension(800, 600));
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}