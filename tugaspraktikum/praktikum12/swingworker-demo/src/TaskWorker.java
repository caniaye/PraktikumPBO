import java.util.List;
import java.util.concurrent.CancellationException;
import java.util.concurrent.ExecutionException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JProgressBar;
import javax.swing.JTextArea;
import javax.swing.SwingWorker;

public class TaskWorker extends SwingWorker<Integer, Integer> {

    // Referensi komponen UI
    private final JFrame frame;
    private final JLabel labelStatus;
    private final JProgressBar progressBar;
    private final JTextArea textAreaHasil;
    private final JButton buttonMulaiTask;
    private final JButton buttonCancel;

    public TaskWorker(JFrame frame, JLabel labelStatus, JProgressBar progressBar, JTextArea textAreaHasil, JButton buttonMulaiTask, JButton buttonCancel) {
        this.frame = frame;
        this.labelStatus = labelStatus;
        this.progressBar = progressBar;
        this.textAreaHasil = textAreaHasil;
        this.buttonMulaiTask = buttonMulaiTask;
        this.buttonCancel = buttonCancel;
    }

    private void addLog(String text) {
        textAreaHasil.append(text + "\n");
        textAreaHasil.setCaretPosition(textAreaHasil.getDocument().getLength());
    }

    @Override
    protected Integer doInBackground() throws Exception {
        int total = 100;
        for (int i = 1; i <= total; i++) {
            // Mengecek status pembatalan
            if (isCancelled()) return null;

            Thread.sleep(80);
            publish(i);
        }
        return total;
    }

    @Override
    protected void process(List<Integer> chunks) {
        // Berjalan di Event Dispatch Thread (EDT)
        if (isCancelled()) return;
        int progress = chunks.get(chunks.size() - 1);
        progressBar.setValue(progress);

        // Perhitungan sisa detik
        int remainingSeconds = (8 * (100 - progress)) / 100;

        labelStatus.setText("Sisa " + remainingSeconds + " detik");
        addLog("Persentase pengerjaan task: " + progress + "%");
    }

    @Override
    protected void done() {
        // Berjalan di Event Dispatch Thread (EDT)
        try {
            Integer result = get();
            labelStatus.setText("Status: Selesai");
            addLog("Background Task Selesai: " + result + "%");

            JOptionPane.showMessageDialog(frame,
                    "Background Task Selesai",
                    "Informasi", JOptionPane.INFORMATION_MESSAGE);

        } catch (ExecutionException ex) {
            labelStatus.setText("Status: Error");
            addLog("Error: " + ex.getCause().getMessage());
            JOptionPane.showMessageDialog(frame,
                    "Terjadi kesalahan: " + ex.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);

        } catch (CancellationException ce) {
            // Ditangkap ketika task dibatalkan
            labelStatus.setText("Status: Dibatalkan");
            addLog("Task dibatalkan.");

        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();

        } finally {
            // Memastikan kedua tombol direset statusnya
            buttonCancel.setEnabled(false);
            buttonMulaiTask.setEnabled(true);
        }
    }
}