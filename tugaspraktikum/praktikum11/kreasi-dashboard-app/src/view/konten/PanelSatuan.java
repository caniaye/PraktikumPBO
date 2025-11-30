package view.konten;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;
import net.miginfocom.swing.MigLayout;

public class PanelSatuan extends JPanel {

    public PanelSatuan() {
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        setBackground(Color.WHITE);

        JLabel label = new JLabel("Panel Satuan");
        label.setFont(new Font("Inter", Font.BOLD, 32));
        label.setForeground(new Color(0, 48, 73));

        add(label, "alignx center, aligny center");
    }
}
