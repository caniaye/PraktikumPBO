import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.Dimension;
import java.awt.Font;

import com.formdev.flatlaf.themes.FlatMacLightLaf;

import net.miginfocom.swing.MigLayout;

public class LatihanCounterApp {

    private static int nilaiCounter = 0;

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {

            JFrame frame = new JFrame("Counter App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(400, 300));
            frame.setMinimumSize(new Dimension(200, 150));

            // Layout dengan MigLayout
            JPanel panelCounter = new JPanel(new MigLayout(
                    "fill, wrap 1",
                    "[grow, center]",
                    "[grow] [grow]"
            ));

            JLabel labelCounter = new JLabel(String.valueOf(nilaiCounter));
            labelCounter.setFont(new Font("Arial", Font.BOLD, 50));

            // POSISI bottom pada row 1
            panelCounter.add(labelCounter, "bottom");

            JButton buttonAdd = new JButton("+");
            buttonAdd.setFont(new Font("Arial", Font.BOLD, 25));

            // POSISI top pada row 2
            panelCounter.add(buttonAdd, "top");

            buttonAdd.addActionListener(e -> {
                nilaiCounter++;
                labelCounter.setText(String.valueOf(nilaiCounter));
            });

            JScrollPane scrollPane = new JScrollPane(panelCounter);
            scrollPane.getVerticalScrollBar().setUnitIncrement(10);
            scrollPane.getVerticalScrollBar()
                    .putClientProperty("JScrollBar.fastWheelScrolling", true);

            frame.add(scrollPane);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
