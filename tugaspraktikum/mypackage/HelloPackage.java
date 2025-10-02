import java.util.Scanner;

public class HelloPackage {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        // Baris ini yang perlu diperbaiki:
        System.out.print("Masukkan nama: "); 
        String nama = input.nextLine();
        System.out.println("Halo, " + nama + "!");
    }
}