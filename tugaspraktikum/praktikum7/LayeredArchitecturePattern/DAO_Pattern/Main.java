package LayeredArchitecturePattern.DAO_Pattern;

import java.util.ArrayList;
import java.util.Scanner;
import LayeredArchitecturePattern.DAO_Pattern.dao.ProductDao;
import LayeredArchitecturePattern.DAO_Pattern.dao.memory.ProductDaoInMemory;
import LayeredArchitecturePattern.DAO_Pattern.model.Product;

public class Main {
    private final ProductDao productDao = new ProductDaoInMemory();
    private final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Main app = new Main();
        app.startMenuLoop();
        app.scanner.close();
    }

    public void startMenuLoop() {
        boolean running = true;
        while (running) {
            System.out.println("\n--- APLIKASI DAO PATTERN ---");
            System.out.println("1. Tampilkan Semua Produk");
            System.out.println("2. Tambah Produk Baru");
            System.out.println("3. Keluar");
            System.out.print("Pilih opsi: ");

            try {
                int choice = Integer.parseInt(scanner.nextLine());
                switch (choice) {
                    case 1 -> displayProducts();
                    case 2 -> addNewProduct();
                    case 3 -> {
                        running = false;
                        System.out.println("Keluar dari aplikasi...");
                    }
                    default -> System.out.println("Opsi tidak valid.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Input tidak valid. Masukkan angka 1-3.");
            }
        }
    }

    private void displayProducts() {
        System.out.println("\n--- Daftar Produk ---");
        ArrayList<Product> products = productDao.findAll();
        if (products.isEmpty()) {
            System.out.println("Belum ada produk yang tersimpan.");
        } else {
            for (Product product : products) {
                System.out.println(product);
            }
        }
    }

    private void addNewProduct() {
        System.out.print("Nama Produk: ");
        String name = scanner.nextLine();

        System.out.print("Harga Produk: ");
        try {
            long price = Long.parseLong(scanner.nextLine());
            if (price <= 0) {
                System.out.println("Error: Harga harus lebih besar dari 0.");
                return;
            }
            int newId = productDao.findAll().size() + 1;
            Product newProduct = new Product(newId, name, price);
            productDao.save(newProduct);
            System.out.println("Produk berhasil ditambahkan!");
        } catch (NumberFormatException e) {
            System.out.println("Input harga tidak valid. Harus berupa angka.");
        }
    }
}
