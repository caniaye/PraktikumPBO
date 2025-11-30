package view;

import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

import net.miginfocom.swing.MigLayout;

import model.MenuItem;
import view.konten.PanelDashboard;
import view.konten.PanelKategori;
import view.konten.PanelProduk;
import view.konten.PanelSatuan;
import view.konten.PanelSupplier;
import view.menu.PanelMenu;

public class FrameUtama extends JFrame {

    MenuItem menuDashboard;
    MenuItem menuProduk;
    MenuItem menuMasterData;
    List<MenuItem> listDaftarMenuItem;
    
    CardLayout cardLayout;
    JPanel panelKonten;
    PanelMenu panelMenu;

    public FrameUtama() {
        initializeUI();
        setupPanelKonten();
        setupPanelMenu();
        addPanel();
    }

    private void initializeUI() {
        setTitle("Frame Utama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(992, 600));
        setLocationRelativeTo(null);
        setLayout(new MigLayout(
        "fill, insets 0, gap 0", 
        "[224!] [grow]", 
        "[grow]"
      ));
    }

    private void setupPanelMenu() {
        listDaftarMenuItem = new ArrayList<>();

        menuDashboard = new MenuItem("Dashboard", "dashboard");
        menuProduk = new MenuItem("Produk");
        menuMasterData = new MenuItem("Master Data");

        menuProduk.addSubMenuItem(new MenuItem("Data Produk", "produk"));
        menuProduk.addSubMenuItem(new MenuItem("Kategori Produk", "kategori"));
        menuMasterData.addSubMenuItem(new MenuItem("Supplier", "supplier"));
        menuMasterData.addSubMenuItem(new MenuItem("Satuan", "satuan"));

        listDaftarMenuItem.add(menuDashboard);
        listDaftarMenuItem.add(menuProduk);
        listDaftarMenuItem.add(menuMasterData);

        panelMenu = new PanelMenu(listDaftarMenuItem, cardLayout, panelKonten);
        panelMenu.setBackground(new Color(245, 247, 250));
    }

    private void setupPanelKonten() {
        cardLayout = new CardLayout();
        panelKonten = new JPanel(cardLayout);

        panelKonten.add(new PanelDashboard(), "dashboard");
        panelKonten.add(new PanelProduk(), "produk");
        panelKonten.add(new PanelKategori(), "kategori");
        panelKonten.add(new PanelSupplier(), "supplier");
        panelKonten.add(new PanelSatuan(), "satuan");
    }

    private void addPanel() {
        add(panelMenu, "growy");
        add(panelKonten, "grow");
    }
}
