package view.konten;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import javax.swing.table.DefaultTableModel;

import java.awt.event.ComponentAdapter;
import java.awt.event.ComponentEvent;

import net.miginfocom.swing.MigLayout;

public class PanelLatihanRenovation extends JPanel {

    private static String buttonColor = "background: $Component.accentColor; foreground: #FFFFFF;";

    public PanelLatihanRenovation() {
        
        setLayout(new MigLayout("fill, insets 0", "[grow]", "[grow]"));
        
        JPanel panelUtama = new JPanel();

        // ----------------------------------------------------
        // 1. CLIENT PANEL
        // ----------------------------------------------------
        JPanel panelClient = new JPanel(new MigLayout("wrap 2", "[right]10[left]"));
        panelClient.setBorder(new TitledBorder("Client"));
        panelClient.add(new JLabel("Client ID:"));
        panelClient.add(new JLabel("<html><b>101</b></html>"));
        panelClient.add(new JLabel("Name:"));
        panelClient.add(new JLabel("<html><b>Bapak Alex Gunawan</b></html>"));
        panelClient.add(new JLabel("Phone:"));
        panelClient.add(new JLabel("<html><b>(+62) 8123456789</b></html>"));
        panelClient.add(new JLabel("Registration No:"));
        panelClient.add(new JLabel("<html><b>RNV-JKT-AXG-001</b></html>"));
        JButton buttonDetails = new JButton("Details");
        buttonDetails.putClientProperty("FlatLaf.style", buttonColor); 
        panelClient.add(buttonDetails, "span 2, center, gaptop 10");

        // ----------------------------------------------------
        // 2. INFORMATION PANEL
        // ----------------------------------------------------
        JPanel panelInformation = new JPanel(new MigLayout("wrap 3", "[right]10[left, grow, fill]5[fill]"));
        panelInformation.setBorder(new TitledBorder("Information"));
        panelInformation.add(new JLabel("Reserve days:"));
        panelInformation.add(new JTextField("0 of 30"));
        panelInformation.add(new JButton("Add Days"));
        panelInformation.add(new JLabel("Buyer:"));
        panelInformation.add(new JTextField("Bapak Alex Gunawan"), "span 2");
        panelInformation.add(new JLabel("Seller:"));
        panelInformation.add(new JTextField("PT Bangun Jaya Abadi"), "span 2");
        panelInformation.add(new JLabel("Address:"));
        panelInformation.add(new JTextField("Jl. Raya Lohbener Baru, 778A"), "span 2");
        panelInformation.add(new JLabel("Credit rating:"));
        panelInformation.add(new JTextField("AAA"));
        panelInformation.add(new JButton("S&P Update"));
        panelInformation.add(new JCheckBox("Approved:"));
        panelInformation.add(new JTextField("Proyek renovasi telah disetujui, siap dimulai"), "span 2");

        // ----------------------------------------------------
        // 3. ADDITIONAL PANEL
        // ----------------------------------------------------
        JPanel panelAdditional = new JPanel(new MigLayout("wrap 3", "[right]10[left, grow, fill][]"));
        panelAdditional.setBorder(new TitledBorder("Additional Information"));
        panelAdditional.add(new JLabel("Estimated close"));
        panelAdditional.add(new JTextField("2025-12-15"), "wmin pref");
        panelAdditional.add(new JButton("Edit"), "wmax pref");
        panelAdditional.add(new JLabel("Creation date:"));
        panelAdditional.add(new JLabel("<html><b>2025-10-15</b></html>"), "span 2");
        panelAdditional.add(new JLabel("Created by:"));
        panelAdditional.add(new JLabel("<html><b>Admin</b></html>"), "span 2");
        panelAdditional.add(new JLabel("Last edit date:"));
        panelAdditional.add(new JLabel("<html><b>2025-11-16</b></html>"), "span 2");
        panelAdditional.add(new JLabel("Last edited by:"));
        panelAdditional.add(new JLabel("<html><b>Warnoto</b></html>"), "span 2");
        panelAdditional.add(new JLabel("Closed date:"));
        panelAdditional.add(new JLabel("<html><b>null</b></html>"), "span 2");
        panelAdditional.add(new JLabel("Closed by:"));
        panelAdditional.add(new JLabel("<html><b>null</b></html>"), "span 2");

        // ----------------------------------------------------
        // 4. PRODUCT PANEL
        // ----------------------------------------------------
        JPanel panelProduk = new JPanel(new MigLayout("", "[grow][]"));
        panelProduk.setBorder(new TitledBorder("Product List"));

        String[] kolomTableProductList = {
            "Renovation", "Description", "Part #", "Quantity", "List Price", "Discount", 
            "Price", "Wholesale Discount", "Wholesaler Price"
        };
        Object[][] dataProductList = {
            { "Dapur", "Keramik Dinding Putih", "KW-PT-DLX-01", 50, 150000, 0, 7500000L, 5, 7125000L },
            { "Dapur", "Lem Keramik Instan", "LMI-GRY-STD", 5, 50000, 0, 250000L, 0, 250000L },
            { "Dapur", "Pipa PVC 3 inch", "PVC-3IN-STD", 12, 35000, 0, 420000L, 10, 378000L },
            { "Kamar Mandi", "Shower Set Minimalis", "SHW-MN-STL", 1, 850000, 15, 722500L, 5, 686375L },
            { "Kamar Mandi", "Closet Duduk Premium", "CLO-DD-PRM", 1, 2500000, 5, 2375000L, 10, 2137500L },
            { "Ruang Tamu", "Lampu Gantung Hias", "LMP-HNG-CRS", 2, 750000, 0, 1500000L, 0, 1500000L },
            { "Ruang Tamu", "Cat Tembok Putih 20L", "CAT-PT-20L", 3, 450000, 10, 1215000L, 15, 1032750L },
            { "Garasi", "Semen PC 50kg", "SMN-PC-50", 20, 60000, 0, 1200000L, 5, 1140000L },
        };

        JTable tableProductList = new JTable(new DefaultTableModel(dataProductList, kolomTableProductList));
        JScrollPane scrollPaneTableProductList = new JScrollPane(tableProductList);

        JPanel panelTableProductList = new JPanel(new MigLayout("wrap 1", "[grow]"));
        panelTableProductList.add(scrollPaneTableProductList, "grow, h 150!");
        panelProduk.add(panelTableProductList, "grow");

        JPanel panelButtonProductList = new JPanel(new MigLayout("wrap 1"));
        panelButtonProductList.add(new JButton("Add"));
        panelButtonProductList.add(new JButton("Edit"));
        panelButtonProductList.add(new JButton("Delete"));
        panelProduk.add(panelButtonProductList);

        // ----------------------------------------------------
        // 5. TASK PANEL
        // ----------------------------------------------------
        JPanel panelTask = new JPanel(new MigLayout("", "[grow][]"));
        panelTask.setBorder(new TitledBorder("Tasks"));

        String[] kolomTableTask = { "State", "Task", "Assigner", "Executer", "Creation Date", "Estimated Date", "Executed Date" };
        Object[][] dataTask = {
            { "Completed", "Pengecatan ulang ruang tamu", "Warnoto", "Toni", "2025-10-25", "2025-10-28", "2025-10-27" },
            { "In Progress", "Instalasi closet duduk premium", "Warnoto", "Goang", "2025-11-15", "2025-11-17", null },
            { "Delayed", "Pemasangan keramik dinding dapur", "Warnoto", "Toni", "2025-11-01", "2025-11-04", null },
            { "Completed", "Pemasangan pipa PVC di area garasi", "Warnoto", "Goang", "2025-10-20", "2025-10-21", "2025-10-21" },
            { "Not Started", "Pembelian dan instalasi lampu gantung hias", "Warnoto", "Toni", "2025-11-16", "2025-11-18", null }
        };

        JTable tableTask = new JTable(new DefaultTableModel(dataTask, kolomTableTask));
        JScrollPane scrollPaneTableTask = new JScrollPane(tableTask);
        panelTask.add(scrollPaneTableTask, "grow, h 120!");

        JPanel panelButtonTask = new JPanel(new MigLayout("wrap 1"));
        panelButtonTask.add(new JButton("Add"));
        panelButtonTask.add(new JButton("Edit"));
        panelButtonTask.add(new JButton("Delete"));
        panelTask.add(panelButtonTask);

        // ----------------------------------------------------
        // 6. COMMENTS PANEL
        // ----------------------------------------------------
        JPanel panelComments = new JPanel(new MigLayout("", "[grow][]"));
        panelComments.setBorder(new TitledBorder("Comments"));

        String[] kolomTableComment = { "Date", "User", "Comment" };
        Object[][] dataComment = {
            { "2025-10-26 10:15", "Toni", "Cat tembok sudah diolesi lapisan pertama. Menunggu kering sebelum lapisan kedua." },
            { "2025-11-15 14:30", "Goang", "Barang sudah sampai di lokasi. Mulai proses pembongkaran closet lama sore ini." },
            { "2025-11-16 09:00", "Warnoto", "Pastikan sambungan pipa air bersih dan pembuangan tidak ada yang bocor." },
            { "2025-11-03 16:45", "Toni", "Cuaca hujan deras selama 2 hari, area kerja sedikit basah. Pemasangan ditunda besok pagi." },
            { "2025-11-16 20:00", "Warnoto", "Lampu yang diinginkan customer stoknya habis. Mencari alternatif lampu model serupa." },
        };

        JTable tableComment = new JTable(new DefaultTableModel(dataComment, kolomTableComment));
        JScrollPane scrollPaneTableComment = new JScrollPane(tableComment);
        panelComments.add(scrollPaneTableComment, "grow, h 120!");

        JPanel panelButtonComment = new JPanel(new MigLayout("wrap 1"));
        panelButtonComment.add(new JButton("Add"));
        panelButtonComment.add(new JButton("Edit"));
        panelButtonComment.add(new JButton("Delete"));
        panelComments.add(panelButtonComment);

        // ----------------------------------------------------
        // 7. CONTROLS
        // ----------------------------------------------------
        JPanel panelControls = new JPanel(new MigLayout("insets 0", "[grow][right]"));
        JPanel left = new JPanel(new MigLayout());
        left.add(new JButton("Export"));
        panelControls.add(left, "grow");

        JPanel right = new JPanel(new MigLayout());
        JButton buttonSave = new JButton("Save");
        buttonSave.putClientProperty("FlatLaf.style", buttonColor); 
        right.add(buttonSave);
        right.add(new JButton("Cancel"));
        panelControls.add(right);

        // ----------------------------------------------------
        // INITIAL SETUP & RESPONSIVE LISTENER
        // ----------------------------------------------------
        
        applyResponsiveLayout(panelUtama, 1200,
                panelClient, panelInformation, panelAdditional,
                panelProduk, panelTask, panelComments, panelControls);

        JScrollPane scrollRoot = new JScrollPane(panelUtama);
        scrollRoot.getVerticalScrollBar().setUnitIncrement(16);
        
        add(scrollRoot, "grow"); 

        addComponentListener(new ComponentAdapter() { 
            @Override
            public void componentResized(ComponentEvent e) {
                int w = PanelLatihanRenovation.this.getWidth(); 
                applyResponsiveLayout(panelUtama, w,
                        panelClient, panelInformation, panelAdditional,
                        panelProduk, panelTask, panelComments, panelControls);
            }
        });
    }

    // ----------------------------------------------------
    // RESPONSIVE HANDLER (Logika Layout)
    // ----------------------------------------------------
    private static void applyResponsiveLayout(
            JPanel root, int width,
            JPanel panelClient, JPanel panelInformation, JPanel panelAdditional,
            JPanel panelProduk, JPanel panelTask, JPanel panelComments, JPanel panelControls
    ) {

        root.removeAll();

        if (width < 768) { 
            root.setLayout(new MigLayout("fill, wrap 1", "[grow]", "[]"));
            root.add(panelClient, "growx");
            root.add(panelAdditional, "growx");
            root.add(panelInformation, "growx");
            root.add(panelProduk, "growx");
            root.add(panelTask, "growx");
            root.add(panelComments, "growx");
            root.add(panelControls, "growx");

        } else if (width < 1200) { 
            root.setLayout(new MigLayout("fill, wrap 2", "[grow][grow]", "[]"));
            root.add(panelClient, "grow");
            root.add(panelAdditional, "grow");
            root.add(panelInformation, "span 2, growx");
            root.add(panelProduk, "span 2, growx");
            root.add(panelTask, "span 2, growx");
            root.add(panelComments, "span 2, growx");
            root.add(panelControls, "span 2, growx");

        } else { 
            root.setLayout(new MigLayout("fill, wrap 3, insets 10", "[25%] [grow] [25%]", "[] [] [] [] []"));
            root.add(panelClient, "grow");
            root.add(panelInformation, "grow");
            root.add(panelAdditional, "grow");
            root.add(panelProduk, "span 3, grow");
            root.add(panelTask, "span 3, grow");
            root.add(panelComments, "span 3, grow");
            root.add(panelControls, "span 3, grow");
        }

        root.revalidate();
        root.repaint();
    }
}