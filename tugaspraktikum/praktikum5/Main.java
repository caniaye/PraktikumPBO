package praktikum5;

public class Main { // Baris 18
    // Hapus "class Kalkulator" di baris 2
    
    // Metode 1: Menambah dua bilangan integer
    public int tambah(int a, int b) {
        return a + b;
    }

    // Metode 2: Menambah tiga bilangan integer
    public int tambah(int a, int b, int c) {
        return a + b + c;
    }
    
    // Metode 3: Menambah dua bilangan double
    public double tambah(double a, double b) {
        return a + b;
    }

    public static void main(String[] args) {
        // Baris 19: Objek dibuat dari kelas "Main" itu sendiri
        Main calc = new Main(); 
        
        System.out.println("Hasil 1: " + calc.tambah(5, 10)); 
        System.out.println("Hasil 2: " + calc.tambah(1, 2, 3));
        System.out.println("Hasil 3: " + calc.tambah(2.5, 3.5));
    }
}