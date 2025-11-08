package MVC_Pattern;

import MVC_Pattern.controller.ProductController;
import MVC_Pattern.view.ProductConsoleView;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ProductConsoleView view = new ProductConsoleView(scanner);
        ProductController controller = new ProductController(view);

        while(true){
            System.out.println("\n--- APLIKASI MVC PATTERN ---");
            System.out.println("1. Tampilkan Produk");
            System.out.println("2. Tambah Produk");
            System.out.println("3. Keluar");
            System.out.print("Pilih: ");

            try {
                int pilihan = Integer.parseInt(scanner.nextLine());
                if (pilihan == 3) break;
                controller.handlingPilihanMenu(pilihan);
            } catch (NumberFormatException e) {
                view.displayError("Input tidak valid. Masukkan angka.");
            }
        }

        scanner.close();
    }
}
