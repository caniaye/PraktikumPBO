// Contoh Interface: Kontrak yang mendefinisikan metode pembayaran
interface MetodePembayaran {
    // Metode abstract secara implisit
    public void otorisasi();
    public void prosesPembayaran(double jumlah);
    public double cekSaldo();
}

// Kelas yang mengimplementasikan Interface
class KartuKredit implements MetodePembayaran {
    private double saldoBatas = 10000000.0; // Batas kredit

    // Implementasi wajib untuk otorisasi
    @Override
    public void otorisasi() {
        System.out.println("Kartu Kredit berhasil diotorisasi. Siap digunakan.");
    }

    // Implementasi wajib untuk memproses pembayaran
    @Override
    public void prosesPembayaran(double jumlah) {
        if (jumlah <= cekSaldo()) {
            System.out.println("Pembayaran Rp" + jumlah + " menggunakan Kartu Kredit berhasil.");
        } else {
            System.out.println("Pembayaran gagal. Melebihi batas kredit.");
        }
    }
    
    // Implementasi wajib untuk cek saldo (sisa batas kredit)
    @Override
    public double cekSaldo() {
        // Dalam skenario nyata, ini akan mengambil sisa batas kredit dari sistem bank
        return saldoBatas;
    }
}

public class MainMetode{
    public static void main(String[] args) {
        // Objek diinstansiasi dengan tipe Interface
        MetodePembayaran payment = new KartuKredit();
        
        payment.otorisasi();
        payment.prosesPembayaran(500000.0);
        
        System.out.println("Sisa batas kredit (perkiraan): Rp" + payment.cekSaldo());
    }
}