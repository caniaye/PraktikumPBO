import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.util.List;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

public class Demo3 extends JFrame {

    private JPanel panelUtama;
    private JButton buttonMulaiTask;
    private JLabel labelStatus;
    private JTextArea textAreaHasil;

    public Demo3() {
        initializeUI();
        setupEventHandlers();
    }

    private void initializeUI() {
        setTitle("Demo 3: SwingWorker (Static Nested Class)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(600, 450));

        // Perbaikan: Inisialisasi JPanel dan MigLayout digabungkan
        panelUtama = new JPanel(new MigLayout("fill, wrap 1, insets 20"));

        // Perbaikan: Penulisan inisialisasi
        buttonMulaiTask = new JButton("Mulai Task (8 detik)");
        buttonMulaiTask.setFont(new Font("Inter", Font.BOLD, 18));
        panelUtama.add(buttonMulaiTask, "growx, h 20%");

        labelStatus = new JLabel("Status: Siap", JLabel.CENTER);
        labelStatus.setFont(new Font("Inter", Font.PLAIN, 22));
        labelStatus.setForeground(new Color(0, 128, 0));
        panelUtama.add(labelStatus, "growx, h 20%");

        // Perbaikan: Penulisan inisialisasi
        textAreaHasil = new JTextArea();
        textAreaHasil.setEditable(false);
        textAreaHasil.setFont(new Font("Consolas", Font.PLAIN, 15));
        JScrollPane scroll = new JScrollPane(textAreaHasil);
        panelUtama.add(scroll, "growx, h 60%");

        add(panelUtama);
        pack();
        setLocationRelativeTo(null);
    }

    private void setupEventHandlers() {
        buttonMulaiTask.addActionListener(
                // Perbaikan: Penggunaan lambda untuk ActionEvent
                e -> new HitungMundurWorker(this).execute()
        );
    }

    // Static Nested Class untuk operasi background
    private static class HitungMundurWorker extends SwingWorker<String, Integer> {

        private final Demo3 frame;

        private HitungMundurWorker(Demo3 frame) {
            this.frame = frame;
        }

        @Override
        protected String doInBackground() throws Exception {
            // Akses komponen UI di awal task (diperbolehkan karena EDT belum sibuk)
            frame.buttonMulaiTask.setEnabled(false);
            frame.labelStatus.setText("Status: Mulai bekerja");
            frame.textAreaHasil.setText("Mengerjakan Background Task (8 detik)\n");

            for (int i = 8; i >= 0; i--) {
                Thread.sleep(1000); // Operasi lama di thread background
                publish(i); // Kirim update ke EDT
            }
            return "Background Task Selesai"; // Hasil akhir (untuk method done)
        }

        @Override
        protected void process(List<Integer> chunks) {
            // Berjalan di EDT: Update UI
            int detik = chunks.get(chunks.size() - 1);
            frame.textAreaHasil.append("Hitung mundur: " + detik + " detik\n");
            frame.textAreaHasil.setCaretPosition(frame.textAreaHasil.getDocument().getLength());
            
            // Perbaikan: Kesalahan penulisan "detik"
            frame.labelStatus.setText("Sisa " + detik + " detik");
        }

        @Override
        protected void done() {
            // Berjalan di EDT: Update UI setelah task selesai
            try {
                String hasil = get(); // Ambil hasil dari doInBackground
                frame.textAreaHasil.append(hasil + "\n");
                frame.labelStatus.setText("Status: Selesai!");
                
                JOptionPane.showMessageDialog(frame, hasil, "Informasi", JOptionPane.INFORMATION_MESSAGE);
            } catch (Exception ex) {
                frame.labelStatus.setText("Status: Error");
                frame.textAreaHasil.append("Error: " + ex.getMessage() + "\n");
            } finally {
                frame.buttonMulaiTask.setEnabled(true);
            }
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                System.err.println(e.getMessage());
            }
            new Demo3().setVisible(true);
        });
    }
}