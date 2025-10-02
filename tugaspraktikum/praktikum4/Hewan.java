// File: Hewan.java (Superclass / Level 1)
class Hewan {
    public void makan() {
        System.out.println("Hewan sedang makan.");
    }
}

// File: Mamalia.java (Subclass dari Hewan / Superclass untuk Kucing / Level 2)
class Mamalia extends Hewan {
    public void menyusui() {
        System.out.println("Mamalia dapat menyusui.");
    }
}

// File: Kucing.java (Subclass dari Mamalia / Level 3)
class Kucing extends Mamalia {
    public void mengeong() {
        System.out.println("Kucing mengeong 'Meow!'");
    }
    
    // Metode utama untuk menjalankan
    public static void main(String[] args) {
        Kucing myCat = new Kucing();
        
        System.out.println("--- Multilevel Inheritance ---");
        
        // Memanggil metode dari Kucing (Level 3)
        myCat.mengeong(); 
        
        // Memanggil metode dari Mamalia (Level 2 - Parent)
        myCat.menyusui(); 
        
        // Memanggil metode dari Hewan (Level 1 - Grandparent)
        myCat.makan(); 
    }
}