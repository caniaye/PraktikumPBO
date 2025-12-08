import java.awt.Dimension;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;

public class ContohLayoutConstraint {

    public static void main(String[] args) {

        try {
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            System.err.println("Gagal mengatur Look and Feel: " + e.getMessage());
        }

        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Layout Constraints (Contoh)");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(500, 400));

            // hidemode 3 = saat komponen disembunyikan, ruangnya dilipat
            String layoutConstraints = "fill, insets 20, hidemode 3";
            // ubah menjadi dua kolom sehingga button3 bisa span 2 dan berada di tengah bawah
            JPanel panel = new JPanel(new MigLayout(layoutConstraints, "[grow][grow]"));

            JButton button1 = new JButton("Button 1");
            JButton button2 = new JButton("Button 2");
            JButton button3 = new JButton("Button 3");

            // Menambahkan tombol: button1 & button2 sejajar, button3 di bawah dan span 2 kolom (tengah)
            panel.add(button1, "w 100!, h 50!, align center");
            panel.add(button2, "w 100!, h 50!, align center, wrap");

            panel.add(button3, "span 2, w 120!, h 50!, align center, wrap");

            JLabel statusLabel = new JLabel("Button 2 terlihat");
            panel.add(statusLabel, "span 2, align center, gaptop 20");

            // Tombol untuk hide/show Button 2
            button1.addActionListener(e -> {
                boolean isVisible = button2.isVisible();
                button2.setVisible(!isVisible);

                if (isVisible) {
                    statusLabel.setText("Button 2 Disembunyikan (Ruang Kosong Dilipat)");
                } else {
                    statusLabel.setText("Button 2 Terlihat Kembali");
                }

                panel.revalidate();
                panel.repaint();
            });

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}
