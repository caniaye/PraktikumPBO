import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import net.miginfocom.swing.MigLayout;

public class ContohComponentConstraint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh Component Constraints");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(400, 280));

            JPanel panel = new JPanel(new MigLayout("fill, wrap 2, gapy 5, gapx 8, insets 15"));

            panel.add(new JLabel("Username:"));
            JTextField usernameField = new JTextField();
            panel.add(usernameField, "growx, pushx");

            panel.add(new JLabel("Password:"));
            JPasswordField passwordField = new JPasswordField();
            panel.add(passwordField, "growx, pushx");

            panel.add(new JLabel("Divisi:"));
            JTextField divisiField = new JTextField();
            panel.add(divisiField, "growx, pushx");

            // Label status
            JLabel statusLabel = new JLabel(" ");
            statusLabel.setForeground(new Color(76, 175, 80));
            panel.add(statusLabel, "span 2, align center, gaptop 8, wrap");

            JButton loginBtn = new JButton("Login");
            loginBtn.addActionListener(e -> {
                String username = usernameField.getText().trim();
                String divisi = divisiField.getText().trim();
                char[] password = passwordField.getPassword();

                if (username.isEmpty() || divisi.isEmpty() || password.length == 0) {
                    statusLabel.setText("Semua field harus diisi!");
                    statusLabel.setForeground(new Color(244, 67, 54));
                } else {
                    statusLabel.setText("✓ Login berhasil!");
                    statusLabel.setForeground(new Color(76, 175, 80));
                }
            });

            panel.add(loginBtn, "span 2, align center, gaptop 5");

            frame.add(panel);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}

