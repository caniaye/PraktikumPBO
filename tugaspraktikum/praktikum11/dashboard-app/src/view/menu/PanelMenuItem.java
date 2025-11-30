package view.menu;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import model.MenuItem;
import net.miginfocom.swing.MigLayout;

public class PanelMenuItem extends JPanel {

    private static final Color BG_SIDEBAR = new Color(245, 247, 250);
    private static final Color BG_HOVER = new Color(224, 238, 235);
    private static final Color BG_SELECTED = new Color(234, 242, 235);
    private static final Color TEXT_NORMAL = new Color(98, 117, 138);
    private static final Color TEXT_HOVER = new Color(8, 48, 73);
    private static final Color TEXT_SELECTED = new Color(131, 188, 160);
    
    private final String contentKey;
    private boolean isSubMenu;
    private boolean selected = false;
    private JPanel panelContainerSubMenu;
    private JLabel lblMenu;
    private PanelMenu panelMenu;

    public PanelMenuItem(MenuItem item, PanelMenu panelMenu) {
        this(item, panelMenu, false);
    }

    private PanelMenuItem(MenuItem item, PanelMenu panelMenu, boolean isSubMenu) {
        this.contentKey = item.getKeyCardLayout();
        this.panelMenu = panelMenu;
        this.isSubMenu = isSubMenu;

        initializeUI(item);
        setupEventHandlers();
    }

    private void initializeUI(MenuItem item) {
        panelContainerSubMenu = new JPanel(
            new MigLayout(
                "fillx, insets 0, gap 0, hidemode 3",
                "[grow]",
                ""
            )
        );
        panelContainerSubMenu.setBackground(Color.WHITE);
        panelContainerSubMenu.setOpaque(true);
        panelContainerSubMenu.setVisible(false);

        setLayout(new MigLayout("fill, insets 8, gap 8", "[grow]", "[]"));
        setBackground(isSubMenu ? Color.WHITE : BG_SIDEBAR);

        lblMenu = new JLabel();
        lblMenu.setText(item.getJudul());
        lblMenu.setFont(new Font("Inter", isSubMenu ? Font.PLAIN : Font.BOLD, 15));
        lblMenu.setForeground(TEXT_NORMAL);

        int gapLeft = isSubMenu ? 18 : 24;
        add(lblMenu, "gapleft " + gapLeft + ", grow, pushy, wrap");

        if (item.getListSubMenuItem() != null && !item.getListSubMenuItem().isEmpty()) {
            for (MenuItem sub : item.getListSubMenuItem()) {
                PanelMenuItem panelSubMenuItem = new PanelMenuItem(sub, panelMenu, true);
                panelContainerSubMenu.add(panelSubMenuItem, "growx, h 42!, wrap");
            }
            add(panelContainerSubMenu, "newline, growx, pushy, wrap");
        }
    }

    private void setupEventHandlers() {
        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseEntered(MouseEvent e) {
                if (!selected) {
                    setBackground(BG_HOVER);
                    lblMenu.setForeground(TEXT_HOVER);
                }
            }

            @Override
            public void mouseExited(MouseEvent e) {
                if (!selected) {
                    setBackground(isSubMenu ? Color.WHITE : BG_SIDEBAR);
                    lblMenu.setForeground(TEXT_NORMAL);
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {
                panelMenu.selectMenuItem(PanelMenuItem.this);

                if (panelContainerSubMenu.getComponentCount() > 0) {
                    boolean visible = panelContainerSubMenu.isVisible();
                    panelContainerSubMenu.setVisible(!visible);
                    
                    revalidate();
                    repaint();
                }
            }
        });
    }

    public void setSelectedByParent(boolean selected) {
        this.selected = selected;
        setBackground(selected ? BG_SELECTED : (isSubMenu ? Color.WHITE : BG_SIDEBAR));
        lblMenu.setForeground(selected ? TEXT_SELECTED : TEXT_NORMAL);
    }

    public String getContentKey() {
        return contentKey;
    }

    public JPanel getPanelCountainerSubMenu() {
        return panelContainerSubMenu;
    }
}