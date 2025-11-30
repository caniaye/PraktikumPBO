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
import view.konten.PanelLatihanRenovation; 

public class FrameUtama extends JFrame {

    MenuItem menuDashboard;
    MenuItem menuProduk;
    MenuItem menuMasterData;
    MenuItem menuLatihanUI; 
    List<MenuItem> listDaftarMenuItem;
    
    CardLayout cardLayout;
    JPanel panelKonten;
    PanelMenu panelMenu;

    public FrameUtama() {
        initializeUI();
        setupPanelKonten();
        setupPanelMenu();
        addPanel();
        pack();
        setLocationRelativeTo(null);
    }

    private void initializeUI() {
        setTitle("Frame Utama");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(1280, 720));
        setMinimumSize(new Dimension(992, 600));
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
        menuLatihanUI = new MenuItem("Latihan UI"); 

        menuProduk.addSubMenuItem(new MenuItem("Data Produk", "produk"));
        menuProduk.addSubMenuItem(new MenuItem("Kategori Produk", "kategori"));
        menuMasterData.addSubMenuItem(new MenuItem("Supplier", "supplier"));
        menuMasterData.addSubMenuItem(new MenuItem("Satuan", "satuan"));
        menuLatihanUI.addSubMenuItem(new MenuItem("Renovation Detail", "renovation_detail")); 

        listDaftarMenuItem.add(menuDashboard);
        listDaftarMenuItem.add(menuProduk);
        listDaftarMenuItem.add(menuMasterData);
        listDaftarMenuItem.add(menuLatihanUI); 

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
        panelKonten.add(new PanelLatihanRenovation(), "renovation_detail"); 
    }

    private void addPanel() {
        add(panelMenu, "growy");
        add(panelKonten, "grow");
    }
}