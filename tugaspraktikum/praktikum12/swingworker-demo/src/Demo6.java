import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;
import javax.swing.UIManager;
import net.miginfocom.swing.MigLayout;

public class Demo6 extends JFrame {

    private JPanel panelUtama;
    private JButton buttonMulaiTask;
    private JButton buttonCancel;
    private JLabel labelStatus;
    private JProgressBar progressBar;
    private JTextArea textAreaHasil;
    private SwingWorker<Integer, Integer> worker;

    public Demo6() {
        initializeUI();
        setupEventHandlers();
    }

    private void initializeUI() {
        setTitle("Demo 6: SwingWorker + Cancelation (Separate Class)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setPreferredSize(new Dimension(720, 520));

        panelUtama = new JPanel(
                new MigLayout("fill, wrap 1, insets 20"));

        addPanelTombol();
        addLabelStatus();
        addProgressBar();
        addScrollTextArea();

        add(panelUtama);
        pack();
        setLocationRelativeTo(null);
    }

    private void addPanelTombol() {
        JPanel panelTombol = new JPanel(
                new MigLayout("fill", "[grow] [grow]"));

        buttonMulaiTask = new JButton("Mulai Task (8 detik)");
        buttonCancel = new JButton("Cancel");
        buttonMulaiTask.setFont(new Font("Inter", Font.BOLD, 18));
        buttonCancel.setFont(new Font("Inter", Font.BOLD, 18));
        buttonCancel.setEnabled(false);

        panelTombol.add(buttonMulaiTask, "w 90%, growy");
        panelTombol.add(buttonCancel, "w 10%, growy");
        panelUtama.add(panelTombol, "h 20%, hmin pref, grow");
    }

    private void addLabelStatus() {
        labelStatus = new JLabel("Status: Siap", JLabel.CENTER);
        labelStatus.setFont(new Font("Inter", Font.BOLD, 24));
        labelStatus.setForeground(new Color(0, 100, 0));
        panelUtama.add(labelStatus, "h 10%, hmin pref, growx");
    }

    private void addProgressBar() {
        progressBar = new JProgressBar(0, 100);
        progressBar.setStringPainted(true);
        progressBar.setFont(new Font("Inter", Font.BOLD, 18));
        progressBar.setValue(0);
        panelUtama.add(progressBar, "h 10%, hmin pref, growx");
    }

    private void addScrollTextArea() {
        textAreaHasil = new JTextArea();
        textAreaHasil.setEditable(false);
        textAreaHasil.setFont(new Font("Consolas", Font.PLAIN, 14));
        JScrollPane scroll = new JScrollPane(textAreaHasil);
        panelUtama.add(scroll, "h 60%, growx");
    }

    private void setupEventHandlers() {
        buttonMulaiTask.addActionListener((ActionEvent e) -> mulaiProses());
        buttonCancel.addActionListener((ActionEvent e) -> {
            if (worker != null && !worker.isDone()) {
                worker.cancel(true);
                addLog("Proses dibatalkan oleh user...");
            }
        });
    }

    private void mulaiProses() {
        buttonMulaiTask.setEnabled(false);
        buttonCancel.setEnabled(true);
        progressBar.setValue(0);
        textAreaHasil.setText("");
        labelStatus.setText("Status: Mulai bekerja");
        addLog("Mengerjakan Background Task (8 detik)");

        worker = new TaskWorker(this, labelStatus, progressBar, textAreaHasil, buttonMulaiTask, buttonCancel);
        worker.execute();
    }

    private void addLog(String text) {
        textAreaHasil.append(text + "\n");
        textAreaHasil.setCaretPosition(textAreaHasil.getDocument().getLength());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception ignored) {
            }
            new Demo6().setVisible(true);
        });
    }
}