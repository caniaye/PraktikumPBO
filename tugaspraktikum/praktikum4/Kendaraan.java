class Kendaraan {
    protected String jenis = "Mobil";

    public void start() {
        System.out.println(jenis + " telah dinyalakan.");
    }

    public void stop() {
        System.out.println(jenis + " telah dimatikan.");
    }
}

class Mobil extends Kendaraan {
    public String merk;

    public Mobil(String merk) {
        // Mengubah nilai variabel 'jenis' dari superclass
        this.jenis = "Mobil Sedan";
        this.merk = merk;
    }

    public void klakson() {
        System.out.println(merk + " berbunyi 'Tin Tin!'");
    }
    
    // Metode utama untuk menjalankan
    public static void main(String[] args) {
        Mobil mobilSaya = new Mobil("Toyota");
        
        System.out.println("--- Single Inheritance ---");
        
        // Memanggil metode dari subclass
        mobilSaya.klakson(); 
        
        // Memanggil properti dan metode yang diwarisi dari Kendaraan
        System.out.println("Jenis Kendaraan: " + mobilSaya.jenis);
        mobilSaya.start();
        mobilSaya.stop();
    }
}