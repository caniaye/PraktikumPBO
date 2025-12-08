import java.awt.*;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.plaf.basic.BasicComboBoxRenderer;
import net.miginfocom.swing.MigLayout;

public class InventoryApp {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ex) {
                // ignore
            }

            JFrame frame = new JFrame("Inventory App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setPreferredSize(new Dimension(900, 520));

            JPanel main = new JPanel(new MigLayout("fill, insets 12, gap 12", "[300px][grow]", "[grow]"));
            main.setBackground(new Color(246,246,246));

            // Left panel (form)
            JPanel left = new JPanel(new MigLayout("wrap 2, gapy 8, gapx 8, insets 16", "[95px][grow]", "[]20[]"));
            left.setBackground(new Color(246,246,246));
            left.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224,224,224)), new EmptyBorder(8,8,8,8)));

            JLabel lblNama = new JLabel("Nama Produk:");
            lblNama.setFont(lblNama.getFont().deriveFont(12f));
            RoundedTextField tfNama = new RoundedTextField(20);
            tfNama.setPreferredSize(new Dimension(180, 30));

            JLabel lblStok = new JLabel("Stok:");
            lblStok.setFont(lblStok.getFont().deriveFont(12f));
            RoundedTextField tfStok = new RoundedTextField(8);
            tfStok.setPreferredSize(new Dimension(180, 30));

            JLabel lblKategori = new JLabel("Kategori:");
            lblKategori.setFont(lblKategori.getFont().deriveFont(12f));

            
            RoundedComboBox<String> combo = new RoundedComboBox<>(new String[]{"Elektronik","Pakaian","Makanan"});
            combo.setPreferredSize(new Dimension(170, 30));

            JLabel lblPrior = new JLabel("Prioritas Kirim:");
            lblPrior.setFont(lblPrior.getFont().deriveFont(12f));
            JCheckBox cbPrior = new JCheckBox("Ya, Prioritas");
            cbPrior.setBackground(new Color(246,246,246));

            // Spacer and save button centered
            RoundedButton btnSave = new RoundedButton("Simpan & Notifikasi");
            btnSave.setPreferredSize(new Dimension(180,36));

            left.add(lblNama);
            left.add(tfNama, "h 32!");
            left.add(lblStok);
            left.add(tfStok, "h 32!");
            left.add(lblKategori);
            left.add(combo, "growx");
            left.add(lblPrior);
            left.add(cbPrior);
            left.add(new JLabel(""), "span 2, grow, push");
            left.add(btnSave, "span 2, align center");

            // Right panel (log)
            JPanel right = new JPanel(new MigLayout("wrap 1, insets 12", "[grow, fill]"));
            right.setBackground(new Color(246,246,246));
            right.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(224,224,224)), new EmptyBorder(8,8,8,8)));

            JLabel lblLog = new JLabel("Log Aktivitas Tambahan:");
            lblLog.setFont(lblLog.getFont().deriveFont(Font.BOLD, 12f));
            JTextArea taLog = new JTextArea(12,30);
            taLog.setEditable(false);
            taLog.setLineWrap(true);
            taLog.setWrapStyleWord(true);
            taLog.setBackground(new Color(250,250,250));
            taLog.setBorder(BorderFactory.createLineBorder(new Color(220,220,220)));
            right.add(lblLog);
            right.add(taLog, "grow, push");

            main.add(left, "growy");
            main.add(right, "grow, push");

            // Action
            btnSave.addActionListener(ev -> {
                String nama = tfNama.getText().trim();
                String stok = tfStok.getText().trim();
                String kategori = (String) combo.getSelectedItem();
                String prior = cbPrior.isSelected() ? "YA" : "TIDAK";

                if (nama.isEmpty() || stok.isEmpty()) {
                    JOptionPane.showMessageDialog(frame, "Nama produk dan stok tidak boleh kosong!", "Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }

                taLog.append("\n[INFO] Disimpan: " + nama + " (" + kategori + ")");

                CustomSuccessDialog.show(frame,
                    "Data Produk Berhasil Disimpan!\n" +
                    "Nama: " + nama + "\n" +
                    "Stok: " + stok + "\n" +
                    "Kategori: " + kategori + "\n" +
                    "Prioritas: " + prior);

                tfNama.setText("");
                tfStok.setText("");
                combo.setSelectedIndex(0);
                cbPrior.setSelected(false);
            });

            frame.add(main);
            frame.pack();
            frame.setLocationRelativeTo(null);
            frame.setVisible(true);
        });
    }

    // --- Custom components ---
    static class RoundedTextField extends JTextField {
        private final int arc = 8;
        public RoundedTextField(int cols){
            super(cols);
            setOpaque(false);
            setBorder(BorderFactory.createEmptyBorder(6,8,6,8));
            addFocusListener(new FocusAdapter(){
                public void focusGained(FocusEvent e){ repaint(); }
                public void focusLost(FocusEvent e){ repaint(); }
            });
        }
        @Override protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            if (isFocusOwner()) g2.setColor(new Color(232,244,255)); else g2.setColor(getParent().getBackground());
            g2.fillRoundRect(0,0,getWidth(),getHeight(),arc,arc);
            if (isFocusOwner()) g2.setColor(new Color(0,120,215)); else g2.setColor(new Color(200,200,200));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,arc,arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedButton extends JButton {
        private final int arc = 10;
        public RoundedButton(String text){
            super(text);
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder(8,12,8,12));
        }
        @Override protected void paintComponent(Graphics g){
            Graphics2D g2 = (Graphics2D)g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            Color bg = getModel().isArmed() ? new Color(200,225,255) : Color.WHITE;
            g2.setColor(bg);
            g2.fillRoundRect(0,0,getWidth(),getHeight(),arc,arc);
            g2.setColor(new Color(200,200,200));
            g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,arc,arc);
            g2.dispose();
            super.paintComponent(g);
        }
    }

    static class RoundedComboBox<E> extends JComboBox<E> {
        public RoundedComboBox(E[] items){
            super(items);
            setOpaque(false);
            // reserve extra right padding for custom arrow area
            setBorder(BorderFactory.createEmptyBorder(4,8,4,28));
            setRenderer(new BasicComboBoxRenderer(){
                @Override public Component getListCellRendererComponent(JList list, Object value, int index, boolean isSelected, boolean cellHasFocus){
                    JLabel l = (JLabel) super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                    l.setBorder(new EmptyBorder(4,6,4,6));
                    return l;
                }
            });
        }
        @Override protected void paintComponent(Graphics g){
            int arc = 10;
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

            // background
            g2.setColor(getParent().getBackground());
            g2.fillRoundRect(0,0,getWidth(),getHeight(),arc,arc);

            // border
            g2.setColor(new Color(200,200,200));
            g2.setStroke(new BasicStroke(1f));
            g2.drawRoundRect(0,0,getWidth()-1,getHeight()-1,arc,arc);

            // Draw blue arrow area on right
            int aw = 26;
            int ax = getWidth() - aw;
            int ay = 2;
            int ah = getHeight() - 4;
            g2.setColor(new Color(0,120,215)); // blue
            g2.fillRoundRect(ax, ay, aw, ah, arc, arc);

            // draw chevron (down) in white
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2.2f, BasicStroke.CAP_ROUND, BasicStroke.JOIN_ROUND));
            int cx = ax + aw/2;
            int cy = getHeight()/2 - 1;
            int s = 4;
            g2.drawLine(cx - s, cy - 1, cx, cy + 2);
            g2.drawLine(cx + s, cy - 1, cx, cy + 2);

            g2.dispose();

            // let super draw the text/editor on top
            super.paintComponent(g);
        }
    }

    static class BlueIconButton extends JButton {
        public BlueIconButton() {
            setContentAreaFilled(false);
            setFocusPainted(false);
            setBorder(BorderFactory.createEmptyBorder());
        }
        @Override protected void paintComponent(Graphics g) {
            Graphics2D g2 = (Graphics2D) g.create();
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            int w = getWidth();
            int h = getHeight();
            int arc = 12;

            // Blue rounded background
            g2.setColor(new Color(0, 120, 215));
            g2.fillRoundRect(0, 0, w, h, arc, arc);

            // White chevron arrows
            g2.setColor(Color.WHITE);
            g2.setStroke(new BasicStroke(2.6f));

            int cx = w / 2;
            int cy = h / 2;
            int size = 4;

            // UP arrow (chevron)
            g2.drawLine(cx - size, cy - 4, cx, cy - 8);
            g2.drawLine(cx + size, cy - 4, cx, cy - 8);

            // DOWN arrow (chevron)
            g2.drawLine(cx - size, cy + 4, cx, cy + 8);
            g2.drawLine(cx + size, cy + 4, cx, cy + 8);

            g2.dispose();
        }
    }

    static class CustomSuccessDialog {
        public static void show(Window owner, String message){
            JDialog dlg = new JDialog(owner, "Sukses", Dialog.ModalityType.APPLICATION_MODAL);
            dlg.setUndecorated(true);
            JPanel p = new JPanel(new MigLayout("wrap 1, insets 12"));
            p.setBackground(Color.WHITE);
            p.setBorder(BorderFactory.createCompoundBorder(BorderFactory.createLineBorder(new Color(200,200,200)), new EmptyBorder(10,12,10,12)));

            JLabel icon = new JLabel(UIManager.getIcon("OptionPane.informationIcon"));
            JLabel lbl = new JLabel("<html>" + message.replaceAll("\n","<br>") + "</html>");
            lbl.setFont(lbl.getFont().deriveFont(12f));

            JButton ok = new JButton("OK");
            ok.setPreferredSize(new Dimension(80,28));
            // tampilkan OK sebagai teks berwarna biru tanpa latar putih
            ok.setFocusPainted(false);
            ok.setContentAreaFilled(false);
            ok.setOpaque(false);
            ok.setForeground(new Color(0,120,215));
            ok.setBorder(BorderFactory.createLineBorder(new Color(0,120,215), 1, true));
 
            JPanel content = new JPanel(new MigLayout("ins 6, wrap 2", "[][grow]"));
            content.setBackground(Color.WHITE);
            content.add(icon);
            content.add(lbl);
            p.add(content);
            p.add(ok, "align center");

            ok.addActionListener(e -> dlg.dispose());

            dlg.add(p);
            dlg.pack();
            dlg.setLocationRelativeTo(owner);
            dlg.setVisible(true);
        }
    }
}
