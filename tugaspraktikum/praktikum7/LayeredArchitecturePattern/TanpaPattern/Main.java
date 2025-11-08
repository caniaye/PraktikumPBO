import java.util.ArrayList;
import java.util.Scanner;

import model.Product;

public class Main {

    private final ArrayList<Product> productList = new ArrayList<>();
    private final Scanner scanner = new Scanner(System.in);

    public Main() {
        productList.add(new Product(1, "Laptop ASUS", 9500000));
        productList.add(new Product(2, "Monitor Dell", 2500000));
    }

    public static void main(String[] args) {
        Main app = new Main();
        app.startMenuLoop();
        app.scanner.close();
    }

    
    public void startMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- APLIKASI TANPA PATTERN ---");
            System.out.println("1. Tampilkan Semua Produk");
            System.out.println("2. Tambah Produk Baru");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1:
                        displayAllProducts();
                        break;
                    case 2:
                        addNewProduct();
                        break;
                    case 3:
                        running = false;
                        System.out.println("Terima kasih! Program selesai.");
                        break;
                    default:
                        System.out.println("Opsi tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka.");
            }
        }
    }

   
    private void displayAllProducts() {
        System.out.println("\n--- Daftar Produk ---");
        for (Product product : productList) {
            System.out.println(product.getId() + " - " + product.getName() + " | Rp " + product.getPrice());
        }
    }

    
    private void addNewProduct() {
        System.out.print("Masukkan Nama Produk: ");
        String name = scanner.nextLine();

        System.out.print("Masukkan Harga Produk: ");
        String priceString = scanner.nextLine();

        try {
            long price = Long.parseLong(priceString);

            if (price <= 0) {
                throw new IllegalArgumentException("Harga harus angka positif di atas nol");
            }

            int newId = productList.size() + 1;
            productList.add(new Product(newId, name, price));
            System.out.println("Produk berhasil ditambahkan!");

        } catch (NumberFormatException e) {
            System.out.println("Error: Harga tidak valid.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
