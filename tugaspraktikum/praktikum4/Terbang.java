// File: Terbang.java (Interface 1)
interface Terbang {
    void terbang();
}

// File: Berenang.java (Interface 2)
interface Berenang {
    void berenang();
}

// File: Bebek.java (Class mengimplementasikan kedua Interface)
class Bebek implements Terbang, Berenang {
    @Override
    public void terbang() {
        System.out.println("Bebek bisa terbang rendah.");
    }

    @Override
    public void berenang() {
        System.out.println("Bebek bisa berenang di air.");
    }
    
    // Metode utama untuk menjalankan
    public static void main(String[] args) {
        Bebek donald = new Bebek();
        
        System.out.println("--- Multiple Inheritance (via Interface) ---");
        
        // Memanggil perilaku dari Interface Terbang
        donald.terbang(); 
        
        // Memanggil perilaku dari Interface Berenang
        donald.berenang(); 
    }
}