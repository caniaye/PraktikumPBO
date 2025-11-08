package ServiceLayer_Pattern;

import java.util.List;
import java.util.Scanner;

import ServiceLayer_Pattern.model.Product;
import ServiceLayer_Pattern.service.ProductServiceDefault;

public class Main {

    private final ProductServiceDefault productService = new ProductServiceDefault();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.startMenuLoop();
        app.scanner.close();
    }

    public void startMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- APLIKASI SERVICE LAYER PATTERN ---");
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
        List<Product> products = productService.getAllProducts();
        for (Product product : products) {
            System.out.println(product.getId() + " - " 
                + product.getName() + " Rp. " + product.getPrice());
        }
    }

    private void addNewProduct() {
        System.out.print("Masukkan Nama Produk: ");
        String name = scanner.nextLine();
        System.out.print("Masukkan Harga Produk: ");
        String priceString = scanner.nextLine();

        try {
            long price = Long.parseLong(priceString);
            productService.addProduct(name, price);
            System.out.println("Produk berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            System.out.println("Error: Harga tidak valid.");
        } catch (IllegalArgumentException e) {
            System.out.println("Error: " + e.getMessage());
        }
    }
}
