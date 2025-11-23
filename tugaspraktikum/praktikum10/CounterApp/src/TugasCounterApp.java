import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.CardLayout;
import java.awt.Dimension;
import java.awt.Font;

import com.formdev.flatlaf.themes.FlatMacLightLaf;
import net.miginfocom.swing.MigLayout;

public class TugasCounterApp {

    // variabel static untuk menyimpan nilai counter yang akan ditampilkan di layar
    private static int nilaiCounter = 0;

    public static void main(String[] args) {
        try {
            // Mengganti tampilan default Swing dengan tema FlatLaf (FlatMacLightLaf)
            // Agar tampilan tombol, frame, dsb jadi lebih modern.
            UIManager.setLookAndFeel(new FlatMacLightLaf());
        } catch (Exception e) {
            // Jika gagal set look and feel, cetak error-nya di konsol, tapi program tetap dijalankan.
            e.printStackTrace();
        }

        // SwingUtilities.invokeLater() memastikan kode GUI dijalankan di Event Dispatch Thread (EDT)
        // Ini adalah best practice ketika bekerja dengan komponen Swing.
        SwingUtilities.invokeLater(() -> {

            // Membuat window utama aplikasi dengan judul "Counter App"
            JFrame frame = new JFrame("Counter App");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);     // ketika di-close, aplikasi berhenti
            frame.setPreferredSize(new Dimension(430, 300));          // ukuran awal jendela ketika pertama dibuka
            frame.setMinimumSize(new Dimension(250, 150));            // ukuran minimum ketika jendela di-resize

            // Panel "cards" akan berfungsi sebagai container utama yang memakai CardLayout
            // CardLayout memungkinkan kita berganti "halaman" (panelCounter <-> panelSummary)
            JPanel cards = new JPanel(new CardLayout());

            // ======================================================
            // PANEL 1 - HALAMAN COUNTER
            // ======================================================

            // panelCounter adalah panel untuk halaman utama yang menampilkan angka dan tombol - / +
            // MigLayout("fill, wrap 1", "[grow, center]", "[grow] [grow] [grow]")
            //  - "fill" : panel diisi penuh area yang tersedia
            //  - "wrap 1" : setiap baris hanya satu kolom (satu komponen per baris)
            //  - "[grow, center]" : kolom tunggal dengan lebar fleksibel (grow) dan align center
            //  - "[grow] [grow] [grow]" : 3 baris, masing-masing mempunyai tinggi fleksibel
            JPanel panelCounter = new JPanel(new MigLayout(
                    "fill, wrap 1",
                    "[grow, center]",
                    "[grow] [grow] [grow]"
            ));

            // ======================
            // Baris 1: Label angka
            // ======================

            // labelCounter menampilkan nilai counter (angka di tengah layar)
            JLabel labelCounter = new JLabel(String.valueOf(nilaiCounter));
            labelCounter.setFont(new Font("Arial", Font.BOLD, 50));  // ukuran font besar agar jelas terlihat

            // "bottom" → komponen ini akan ditempatkan di bagian bawah baris pertama.
            // Dengan baris pertama bertipe [grow], efeknya: angka berada sedikit di bawah tengah area baris atas.
            panelCounter.add(labelCounter, "bottom");

            // ======================
            // Baris 2: Tombol - dan +
            // ======================

            // panelButtons adalah panel kecil untuk menampung dua tombol (- dan +)
            // "insets 0" → tanpa padding dalam
            // "[]15[]" → dua kolom, di antara keduanya ada jarak horizontal 15 pixel
            JPanel panelButtons = new JPanel(new MigLayout("insets 0", "[]15[]"));

            // Tombol untuk mengurangi nilai counter
            JButton btnMin = new JButton("-");
            // Tombol untuk menambah nilai counter
            JButton btnPlus = new JButton("+");

            // Set font kedua tombol agar tampilannya konsisten
            btnMin.setFont(new Font("Arial", Font.BOLD, 25));
            btnPlus.setFont(new Font("Arial", Font.BOLD, 25));

            // Tambahkan tombol - dan + ke panelButtons secara berurutan
            panelButtons.add(btnMin);
            panelButtons.add(btnPlus);

            // Tambahkan panelButtons ke baris kedua panelCounter.
            // "top" → panelButtons akan ditempatkan di bagian atas baris kedua.
            panelCounter.add(panelButtons, "top");

            // ======================
            // Baris 3: Tombol >
            // ======================

            // Tombol untuk berpindah ke halaman berikutnya (summary)
            JButton btnNext = new JButton(">");
            btnNext.setFont(new Font("Arial", Font.BOLD, 22));   // sedikit lebih kecil dari angka
            btnNext.setPreferredSize(new Dimension(40, 40));     // ukuran kotak tombol

            // Tambahkan btnNext ke panelCounter dengan constraint pos
            // "pos 1.0al 1.0al" artinya:
            //   - 1.0al pada sumbu X  → posisi di sisi paling kanan (alignment 1.0 = kanan)
            //   - 1.0al pada sumbu Y  → posisi di sisi paling bawah (alignment 1.0 = bawah)
            // "gapright 15"  → beri jarak 15 pixel dari sisi kanan panel
            // "gapbottom 12" → beri jarak 12 pixel dari sisi bawah panel
            // Hasilnya: tombol > berada di pojok kanan bawah, tapi tidak menempel langsung ke border.
            panelCounter.add(btnNext, "pos 1.0al 1.0al, gapright 15, gapbottom 12");

            // Bungkus panelCounter di dalam JScrollPane agar sesuai dengan contoh tugas
            // dan memiliki border tipis di dalam frame.
            JScrollPane scroll1 = new JScrollPane(panelCounter);
            // setBorder(EtchedBorder) membuat border tipis abu-abu seperti panel contoh
            scroll1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

            // ======================================================
            // PANEL 2 - HALAMAN SUMMARY
            // ======================================================

            // panelSummary adalah panel halaman kedua yang menampilkan teks keterangan + nilai terakhir
            JPanel panelSummary = new JPanel(new MigLayout(
                    "fill, wrap 1",
                    "[grow, center]",
                    "[grow] [grow] [grow]"
            ));

            // ===== Baris 1: Teks + Angka (dalam satu blok) =====

            // summaryBlock adalah panel kecil untuk mengelompokkan label teks dan angka
            // "wrap 1" → satu kolom (teks di atas, angka di bawah)
            // "insets 0" → tanpa padding
            // "[center]" → isi rata tengah
            // "[]5[]"  → dua baris, jarak vertikal 5 pixel di antara mereka
            JPanel summaryBlock = new JPanel(new MigLayout("wrap 1, insets 0", "[center]", "[]5[]"));
            summaryBlock.setOpaque(false); // transparent, supaya warna latar belakang mengikuti panelSummary

            // Label teks yang menjelaskan arti angka
            JLabel lblInfo = new JLabel("Nilai terakhir Anda adalah");
            lblInfo.setFont(new Font("Arial", Font.PLAIN, 15));
            summaryBlock.add(lblInfo);  // baris pertama di summaryBlock

            // Label angka yang menampilkan nilaiCounter ketika pindah ke halaman summary
            JLabel lblResult = new JLabel(String.valueOf(nilaiCounter));
            lblResult.setFont(new Font("Arial", Font.BOLD, 50));
            summaryBlock.add(lblResult); // baris kedua di summaryBlock

            // Tambahkan summaryBlock ke panelSummary di baris pertama.
            // "bottom" → summaryBlock diletakkan di bagian bawah baris pertama (yang bertipe [grow]),
            // sehingga posisinya sedikit turun dari tengah, seperti contoh gambar.
            panelSummary.add(summaryBlock, "bottom");

            // ===== Baris 3: Tombol Kembali (<) =====

            // Tombol untuk kembali ke halaman utama (counter)
            JButton btnBack = new JButton("<");
            btnBack.setFont(new Font("Arial", Font.BOLD, 22));
            btnBack.setPreferredSize(new Dimension(40, 40));

            // Tambahkan btnBack ke panelSummary.
            // "pos 0.0al 1.0al" → pojok kiri bawah (X=0.0 = kiri, Y=1.0 = bawah)
            // "gapleft 15"  → jarak 15 pixel dari sisi kiri
            // "gapbottom 12" → jarak 12 pixel dari sisi bawah
            // Hasilnya: tombol < berada di pojok kiri bawah panel summary sesuai contoh.
            panelSummary.add(btnBack, "pos 0.0al 1.0al, gapleft 15, gapbottom 12");

            // Bungkus panelSummary ke dalam JScrollPane juga, supaya konsisten dengan halaman pertama
            JScrollPane scroll2 = new JScrollPane(panelSummary);
            scroll2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

            // ======================================================
            // CARD SWITCHING (LOGIKA GANTI HALAMAN)
            // ======================================================

            // Tambahkan kedua scrollPane ke panel "cards" dengan nama kartu "main" dan "summary"
            cards.add(scroll1, "main");      // halaman utama (counter)
            cards.add(scroll2, "summary");   // halaman ringkasan (summary)

            // Ambil referensi CardLayout dari panel cards
            CardLayout cl = (CardLayout) cards.getLayout();

            // ===== EVENT HANDLER TOMBOL + =====
            btnPlus.addActionListener(e -> {
                // Tambahkan nilai counter
                nilaiCounter++;
                // Update teks labelCounter di halaman utama
                labelCounter.setText(String.valueOf(nilaiCounter));
            });

            // ===== EVENT HANDLER TOMBOL - =====
            btnMin.addActionListener(e -> {
                // Kurangi nilai counter
                nilaiCounter--;
                // Update teks labelCounter di halaman utama
                labelCounter.setText(String.valueOf(nilaiCounter));
            });

            // ===== EVENT HANDLER TOMBOL > (NEXT) =====
            btnNext.addActionListener(e -> {
                // Set label di halaman summary agar menampilkan nilai counter yang terbaru
                lblResult.setText(String.valueOf(nilaiCounter));
                // Perintahkan CardLayout untuk menampilkan kartu dengan nama "summary"
                cl.show(cards, "summary");
            });

            // ===== EVENT HANDLER TOMBOL < (BACK) =====
            btnBack.addActionListener(e -> {
                // Kembali ke halaman utama (counter)
                cl.show(cards, "main");
            });

            // ======================================================
            // SETUP FRAME UTAMA
            // ======================================================

            // Tambahkan panel cards (yang berisi kedua halaman) ke frame utama
            frame.add(cards);

            // pack() akan menyesuaikan ukuran frame berdasarkan preferredSize komponen-komponen di dalamnya
            frame.pack();

            // setLocationRelativeTo(null) → posisikan jendela di tengah layar
            frame.setLocationRelativeTo(null);

            // Tampilkan window
            frame.setVisible(true);
        });
    }
}
