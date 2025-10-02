public class MainClass {
    public static void main(String[] args) {
        // Membuat objek Pegawai
        Pegawai p1 = new Pegawai();

        // Menggunakan Setter untuk menetapkan nilai
        System.out.println("--- Menggunakan Setter ---");
        p1.setNama("Budi Santoso");
        p1.setGaji(5500000.0);

        // Mencoba nilai yang tidak valid (validasi di setter akan berjalan)
        p1.setGaji(-100.0);
        p1.setNama("");

        // Menggunakan Getter untuk mendapatkan nilai
        System.out.println("\n--- Menggunakan Getter ---");
        System.out.println("Nama Pegawai: " + p1.getNama());
        System.out.println("Gaji Pegawai: Rp" + p1.getGaji());
        System.out.println("Posisi Default: " + p1.getPosisiDefault());

        // *Akses langsung ke p1.nama atau p1.gaji akan menyebabkan error kompilasi
        // karena mereka private.*
    }
}