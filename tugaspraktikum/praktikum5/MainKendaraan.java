class Kendaraan {
    public void jalankan() {
        System.out.println("Kendaraan bergerak maju.");
    }
}

// Contoh Subkelas yang meng-override metode jalankan()
class Mobil extends Kendaraan {
    @Override 
    public void jalankan() {
        System.out.println("Mobil melaju di jalan raya dengan empat roda.");
    }
}

// Contoh Subkelas lain yang meng-override metode jalankan()
class Sepeda extends Kendaraan {
    @Override
    public void jalankan() {
        System.out.println("Sepeda dikayuh di jalur sepeda.");
    }
}

public class MainKendaraan {
    public static void main(String[] args) {
        // Objek Mobil direferensikan sebagai tipe Kendaraan
        Kendaraan transportasi1 = new Mobil(); 
        // Objek Sepeda direferensikan sebagai tipe Kendaraan
        Kendaraan transportasi2 = new Sepeda(); 

        // Pemanggilan metode yang sama (jalankan) menghasilkan perilaku yang berbeda
        transportasi1.jalankan(); // Output: Mobil melaju di jalan raya dengan empat roda.
        transportasi2.jalankan(); // Output: Sepeda dikayuh di jalur sepeda.
    }
}