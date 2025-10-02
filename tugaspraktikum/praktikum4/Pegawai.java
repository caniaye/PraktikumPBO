public class Pegawai {
    
    // 1. Variabel (field) dideklarasikan private (Data Hiding)
    private String nama;
    private double gaji;

    // Non-Access Modifier: final untuk konstanta
    private final String POSISI_DEFAULT = "Staf Administrasi";

    // 2. Setter Method: Untuk mengubah/menetapkan nilai data
    public void setNama(String nama) {
        // Logika kontrol/validasi (contoh: tidak boleh kosong)
        if (nama != null && !nama.trim().isEmpty()) {
            this.nama = nama;
        } else {
            System.out.println("Error: Nama tidak boleh kosong.");
        }
    }

    public void setGaji(double gaji) {
        // Logika kontrol/validasi (contoh: gaji harus positif)
        if (gaji >= 0) {
            this.gaji = gaji;
        } else {
            this.gaji = 0; // Set ke 0 jika nilai negatif
            System.out.println("Peringatan: Gaji diatur ke 0 karena nilai yang dimasukkan negatif.");
        }
    }

    // 3. Getter Method: Untuk mengambil/membaca nilai data
    public String getNama() {
        return nama;
    }

    public double getGaji() {
        return gaji;
    }

    // Getter untuk konstanta
    public String getPosisiDefault() {
        return POSISI_DEFAULT;
    }
}