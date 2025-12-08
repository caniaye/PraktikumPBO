import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import net.miginfocom.swing.MigLayout;

public class ContohGridConstraint {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Contoh Grid Constraints");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(420, 260));

            // Panel utama dengan padding
            JPanel container = new JPanel(new MigLayout("insets 10, fill", "[grow]"));
            container.setBorder(new EmptyBorder(8, 12, 12, 12));

            // Header
            JLabel header = new JLabel("Login Aplikasi");
            header.setFont(header.getFont().deriveFont(Font.BOLD, 18f));
            header.setForeground(new Color(40, 40, 40));
            container.add(header, "wrap, align center, gapbottom 10");

            // Form dengan dua kolom: label kanan, field fill
            String colConstraints = "[right][grow, fill]";
            JPanel form = new JPanel(new MigLayout("wrap 2, gapy 8, insets 0", colConstraints));
            JTextField usernameField = new JTextField(18);
            JPasswordField passwordField = new JPasswordField(18);

            form.add(new JLabel("Username:"), "gapright 8");
            form.add(usernameField, "growx, pushx");

            form.add(new JLabel("Password:"), "gapright 8");
            form.add(passwordField, "growx, pushx, wrap");

            container.add(form, "growx, pushx, wrap");

            // Status label
            JLabel statusLabel = new JLabel(" ");
            statusLabel.setFont(statusLabel.getFont().deriveFont(12f));
            statusLabel.setForeground(new Color(100, 100, 100));
            container.add(statusLabel, "growx, pushx, wrap, gapbottom 8");

            // Tombol di kanan: Cancel | Login
            JPanel btnPanel = new JPanel(new MigLayout("insets 0, fill", "[grow][][]"));
            JButton cancelBtn = new JButton("Cancel");
            JButton loginBtn = new JButton("Login");
            cancelBtn.setPreferredSize(new Dimension(90, 28));
            loginBtn.setPreferredSize(new Dimension(90, 28));

            btnPanel.add(new JLabel(), "growx"); // spacer
            btnPanel.add(cancelBtn, "sg btn");
            btnPanel.add(loginBtn, "sg btn, gapleft 8");

            container.add(btnPanel, "growx, pushx, wrap");

            // Footer: copyright 2025, docked ke bawah kiri
            JLabel footer = new JLabel("\u00A9 2025");
            footer.setFont(footer.getFont().deriveFont(11f));
            footer.setForeground(new Color(120, 120, 120));
            container.add(footer, "dock south, align left, gaptop 8");

            // Aksi tombol simpel untuk feedback UI
            loginBtn.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String user = usernameField.getText().trim();
                    char[] pass = passwordField.getPassword();
                    if (user.isEmpty()) {
                        statusLabel.setText("Isi username terlebih dahulu.");
                    } else if (pass.length == 0) {
                        statusLabel.setText("Isi password terlebih dahulu.");
                    } else {
                        statusLabel.setText("Login berhasil (demo) — user: " + user);
                    }
                }
            });

            cancelBtn.addActionListener(e -> {
                usernameField.setText("");
                passwordField.setText("");
                statusLabel.setText(" ");
            });

            frame.setContentPane(container);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }
}