package MVC_ServiceLayer_DAO.view;

import java.util.ArrayList;
import java.util.Scanner;
import MVC_ServiceLayer_DAO.model.Product;

public class ProductConsoleView {

    private final Scanner scanner;

    public ProductConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayMenu() {
        System.out.println("\n--- APLIKASI MVC + SERVICE LAYER + DAO PATTERN ---");
        System.out.println("1. Tampilkan Semua Produk");
        System.out.println("2. Tambah Produk Baru");
        System.out.println("3. Keluar");
        System.out.print("Pilih opsi: ");
    }

    public void displayAllProducts(ArrayList<Product> products) {
        System.out.println("\n--- Daftar Produk ---");
        if (products.isEmpty()) {
            System.out.println("Tidak ada produk tersedia.");
        } else {
            for (Product product : products) {
                System.out.println(product.getId() + " - " + product.getName()
                        + " Rp. " + product.getPrice());
            }
        }
    }

    public void displayMessage(String message) {
        System.out.println("INFO: " + message);
    }

    public void displayError(String message) {
        System.out.println("ERROR: " + message);
    }

    public String getProductNameFromUser() {
        System.out.print("Masukkan Nama Produk: ");
        return scanner.nextLine();
    }

    public String getProductPriceFromUser() {
        System.out.print("Masukkan Harga Produk: ");
        return scanner.nextLine();
    }

    public String getPilihanMenuFromUser() {
        return scanner.nextLine();
    }
}
