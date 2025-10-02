// File: Bentuk.java (Superclass)
class Bentuk {
    public double hitungLuas() {
        return 0.0; // Implementasi dasar
    }
}

// File: Lingkaran.java (Subclass)
class Lingkaran extends Bentuk {
    private double radius;
    
    public Lingkaran(double radius) {
        this.radius = radius;
    }

    // Override method dari Superclass
    @Override
    public double hitungLuas() {
        return Math.PI * radius * radius;
    }
}

// File: Persegi.java (Subclass)
class Persegi extends Bentuk {
    private double sisi;

    public Persegi(double sisi) {
        this.sisi = sisi;
    }

    // Override method dari Superclass
    @Override
    public double hitungLuas() {
        return sisi * sisi;
    }

    // Metode utama untuk menjalankan
    public static void main(String[] args) {
        System.out.println("--- Hierarchical Inheritance ---");
        
        Lingkaran objLingkaran = new Lingkaran(7.0);
        Persegi objPersegi = new Persegi(4.0);
        
        System.out.println("Luas Lingkaran (radius 7): " + objLingkaran.hitungLuas());
        System.out.println("Luas Persegi (sisi 4): " + objPersegi.hitungLuas());
        
        // Kedua objek mewarisi properti Bentuk, tetapi mengimplementasikannya berbeda.
    }
}