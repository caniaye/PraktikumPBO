package view.konten;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;
import ui.swing.responsif.UISwingResponsif; // BARU: Import kelas UISwingResponsif

public class PanelUISwingResponsif extends JPanel {

    public PanelUISwingResponsif() {
        setLayout(new MigLayout("fill, insets 0, gap 0", "[grow]", "[grow]")); // Layout mengisi penuh
        setBackground(Color.WHITE);

        try {
            // Instansiasi UISwingResponsif dalam mode embedded (sebagai JPanel)
            UISwingResponsif responsiveApp = new UISwingResponsif(true); 
            // Tambahkan mainPanel dari UISwingResponsif ke panel ini
            add(responsiveApp.getMainPanel(), "grow");

        } catch (Exception e) {
            // Fallback jika ada masalah dalam memuat UISwingResponsif
            JLabel errorLabel = new JLabel("<html><center>Gagal memuat Konten UI Swing Responsif.<br>"
                                          + "Pastikan `UISwingResponsif` telah dimodifikasi untuk mode embedded.</center></html>");
            errorLabel.setFont(new Font("Inter", Font.BOLD, 20));
            errorLabel.setForeground(Color.RED);
            add(errorLabel, "alignx center, aligny center");
            e.printStackTrace(); // Cetak stack trace untuk debugging
        }
    }
}