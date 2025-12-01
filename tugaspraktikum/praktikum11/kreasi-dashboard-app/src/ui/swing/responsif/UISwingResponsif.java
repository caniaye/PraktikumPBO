package ui.swing.responsif; // BARU: Pastikan package ini sesuai dengan struktur Anda

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import net.miginfocom.swing.MigLayout;
// import com.formdev.flatlaf.themes.FlatMacLightLaf; // Tidak perlu diimport jika L&F diset di DashboardApp

public class UISwingResponsif extends JFrame {

    private JPanel mainPanel; // mainPanel harus bisa diakses dari luar
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;
    private JPanel panel4;
    private JPanel panel5;
    
    // BARU: Getter untuk mainPanel agar bisa diakses oleh PanelUISwingResponsif
    public JPanel getMainPanel() {
        return mainPanel;
    }

    private static final int COMPACT_VIEW_MAX = 576;
    private static final int SPLIT_VIEW_MAX = 768;
    private static final int INTERMEDIATE_VIEW_MAX = 992;
    private static final int DESKTOP_SMALL_MAX = 1200;
    private static final int DESKTOP_STANDARD_MAX = 1400;

    // BARU: Konstruktor dengan parameter untuk menentukan mode operasi
    // true: embedded mode (sebagai JPanel), false: standalone mode (sebagai JFrame)
    public UISwingResponsif(boolean embeddedMode) {
        // Inisialisasi mainPanel sebelum digunakan
        mainPanel = new JPanel();

        if (!embeddedMode) { 
            // Konfigurasi JFrame hanya jika dalam mode standalone
            setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            setMinimumSize(new Dimension(400, 600));
            
            // Listener untuk JFrame (saat mode standalone)
            addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    updateLayoutAndTitle(); // Mengupdate judul dan layout
                }
            });
            
            // Tambahkan mainPanel ke JFrame
            super.add(mainPanel); 
            
            initializePanels(); // Inisialisasi sub-panel
            rebuildLayout(getWidth() - getInsets().left - getInsets().right); // Panggil untuk pertama kali
            
            pack();
            setExtendedState(JFrame.MAXIMIZED_BOTH);
            setLocationRelativeTo(null);
            updateLayoutAndTitle(); // Panggil lagi untuk mengatur judul awal
        } else {
            // Mode embedded: hanya menyiapkan mainPanel dan listener-nya
            initializePanels(); // Inisialisasi sub-panel
            
            // Listener untuk mainPanel (saat mode embedded)
            mainPanel.addComponentListener(new ComponentAdapter() {
                @Override
                public void componentResized(ComponentEvent e) {
                    rebuildLayout(mainPanel.getWidth()); // Rebuild layout berdasarkan lebar mainPanel
                }
            });
            // Atur layout awal mainPanel dengan ukuran default (akan disesuaikan saat direfresh)
            rebuildLayout(800); // Ukuran default sementara untuk inisialisasi
        }
    }
    
    // BARU: Konstruktor default, memanggil konstruktor utama dalam mode standalone
    public UISwingResponsif() {
        this(false); // Default ke mode JFrame (non-embedded)
    }
    
    private void initializePanels() {
        panel1 = createColoredPanel("Panel 1 Header", new Color(70, 130, 180));
        panel2 = createColoredPanel("Panel 2 Sidebar", new Color(60, 179, 113));
        panel3 = createColoredPanel("Panel 3 Content", new Color(255, 206, 86));
        panel4 = createColoredPanel("Panel 4 Secondary", new Color(147, 112, 219));
        panel5 = createColoredPanel("Panel 5 Footer", new Color(220, 20, 60));
    }

    private void updateLayoutAndTitle() {
        // Metode ini hanya relevan untuk mode JFrame
        if (mainPanel.getParent() instanceof JFrame) { // Cek apakah parent adalah JFrame
            Dimension size = getSize();
            Insets insets = getInsets();
            int innerWidth = size.width - insets.left - insets.right;
            int innerHeight = size.height - insets.top - insets.bottom;

            String category = getDeviceCategory(innerWidth);
            setTitle(category + " | " + innerWidth + " x " + innerHeight + " px");

            rebuildLayout(innerWidth);
        } else {
            // Jika bukan JFrame, hanya panggil rebuildLayout tanpa mengubah judul
            rebuildLayout(mainPanel.getWidth());
        }
    }

    private String getDeviceCategory(int width) {
        if (width <= COMPACT_VIEW_MAX) return "Compact View (≤576px)";
        else if (width <= SPLIT_VIEW_MAX) return "Split View (576-768px)";
        else if (width <= INTERMEDIATE_VIEW_MAX) return "Intermediate View (768-992px)";
        else if (width <= DESKTOP_SMALL_MAX) return "Desktop Small (992-1200px)";
        else if (width <= DESKTOP_STANDARD_MAX) return "Desktop Standard (1200-1400px)";
        else return "Desktop Large (>1400px)";
    }

    private void rebuildLayout(int width) {
        mainPanel.removeAll();
        mainPanel.setLayout(new MigLayout("fill, insets 0, gap 0"));

        if (width <= COMPACT_VIEW_MAX) {
            mainPanel.add(panel1, "grow, wrap");
            mainPanel.add(panel2, "grow, wrap");
            mainPanel.add(panel3, "grow, push, h 300::, wrap");
            mainPanel.add(panel4, "grow, wrap");
            mainPanel.add(panel5, "grow, wrap");

        } else if (width <= SPLIT_VIEW_MAX) {
            mainPanel.add(panel1, "span 2, growx, wrap");
            mainPanel.add(panel2, "w 200!, growy");
            mainPanel.add(panel3, "grow, push, wrap");
            mainPanel.add(panel4, "span 2, growx, wrap");
            mainPanel.add(panel5, "span 2, growx, wrap");

        } else if (width <= INTERMEDIATE_VIEW_MAX) {
            mainPanel.add(panel1, "span 2, growx, wrap");
            mainPanel.add(panel2, "w 280!, growy");
            mainPanel.add(panel3, "grow, push, wrap, spany 2");
            mainPanel.add(panel4, "growx, h 180!, wrap");
            mainPanel.add(panel5, "span 2, growx, wrap");

        } else if (width <= DESKTOP_SMALL_MAX) {
            mainPanel.add(panel1, "span 3, growx, wrap");
            mainPanel.add(panel2, "growy, span 1 2, w 300!, h 100%");
            mainPanel.add(panel3, "grow, push, wrap");
            mainPanel.add(panel4, "h 25%, growx, wrap");
            mainPanel.add(panel5, "span 3, growx");

        } else if (width <= DESKTOP_STANDARD_MAX) {
            mainPanel.add(panel1, "span 3, growx, wrap");
            mainPanel.add(panel2, "w 300!, growy");
            mainPanel.add(panel3, "grow, pushx 2, pushy");
            mainPanel.add(panel4, "grow, wrap");
            mainPanel.add(panel5, "span 3, growx, wrap");

        } else {
            mainPanel.add(panel1, "span 3, growx, wrap");
            mainPanel.add(panel2, "w 340!, growy");
            mainPanel.add(panel3, "grow, pushx 3, pushy");
            mainPanel.add(panel4, "grow, wrap");
            mainPanel.add(panel5, "span 3, growx, wrap");
        }

        mainPanel.revalidate();
        mainPanel.repaint();
    }

    private JPanel createColoredPanel(String title, Color background) {
        JPanel panel = new JPanel(new MigLayout("fill, insets 0"));
        panel.setBackground(background);

        panel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));

        JLabel label = new JLabel(title, SwingConstants.CENTER);
        label.setFont(new Font("Inter", Font.BOLD, 16));
        label.setForeground(Color.WHITE);

        panel.add(label, "grow, align center");

        return panel;
    }

    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(new com.formdev.flatlaf.themes.FlatMacLightLaf());
        } catch (UnsupportedLookAndFeelException e) {
            e.printStackTrace();
        }

        SwingUtilities.invokeLater(() -> {
            // Memanggil konstruktor default (mode JFrame)
            new UISwingResponsif().setVisible(true); 
        });
    }
}