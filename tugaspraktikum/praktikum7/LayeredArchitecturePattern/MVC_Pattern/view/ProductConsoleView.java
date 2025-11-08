package MVC_Pattern.view;

import java.util.ArrayList;
import java.util.Scanner;
import MVC_Pattern.model.Product;

public class ProductConsoleView {
    private final Scanner scanner;

    public ProductConsoleView(Scanner scanner) {
        this.scanner = scanner;
    }

    public void displayAllProducts(ArrayList<Product> products) {
        System.out.println("\n=== DAFTAR PRODUK ===");
        for (Product p : products) {
            System.out.println(p.getId() + ". " + p.getName() + " - Rp" + p.getPrice());
        }
    }

    public String getProductNameFromUser() {
        System.out.print("Masukkan nama produk: ");
        return scanner.nextLine();
    }

    public String getProductPriceFromUser() {
        System.out.print("Masukkan harga produk: ");
        return scanner.nextLine();
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void displayError(String message) {
        System.err.println("Error: " + message);
    }
}
