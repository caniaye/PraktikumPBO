package view.konten;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

public class PanelDashboard extends JPanel {

    public PanelDashboard() {   
        initializeUI();
    }

    private void initializeUI() {
        setLayout(new MigLayout("fill, insets 60", "[grow]", "[grow]"));
        setBackground(Color.WHITE);
        setOpaque(true);

        JLabel title = new JLabel("Panel Dashboard");
        title.setFont(new Font("Inter", Font.BOLD, 36));
        title.setForeground(new Color(0, 48, 73)); // #003049

        JLabel info = new JLabel("Deskripsi panel dashboard");
        info.setFont(new Font("Inter", Font.PLAIN, 16));
        info.setForeground(new Color(98, 117, 138)); // #62758A

        add(title, "alignx center, wrap 30");
        add(info, "alignx center");
    }

}
