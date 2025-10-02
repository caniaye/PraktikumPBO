// Contoh Abstract Class: Karyawan
abstract class Karyawan {
    private String nama;
    private String id;

    public Karyawan(String nama, String id) {
        this.nama = nama;
        this.id = id;
    }

    // Metode abstract: Subkelas wajib mengimplementasikan cara menghitung gaji
    public abstract double hitungGaji();

    // Metode konkret: Implementasi standar untuk semua subkelas
    public void infoKaryawan() {
        System.out.println("Nama: " + nama);
        System.out.println("ID: " + id);
    }
}

// Subkelas Konkret 1: Karyawan Penuh Waktu
class PenuhWaktu extends Karyawan {
    private double gajiBulanan;

    public PenuhWaktu(String nama, String id, double gajiBulanan) {
        super(nama, id);
        this.gajiBulanan = gajiBulanan;
    }

    // Implementasi wajib untuk metode abstract
    @Override
    public double hitungGaji() {
        return gajiBulanan;
    }
}

public class MainKaryawan {
    public static void main(String[] args) {
        PenuhWaktu k1 = new PenuhWaktu("Alice", "FT001", 8000000.0);
        
        k1.infoKaryawan();
        System.out.println("Gaji yang diterima: Rp" + k1.hitungGaji());
        
        // Karyawan k2 = new Karyawan("Test", "000"); // Error: Karyawan is abstract; cannot be instantiated
    }
}