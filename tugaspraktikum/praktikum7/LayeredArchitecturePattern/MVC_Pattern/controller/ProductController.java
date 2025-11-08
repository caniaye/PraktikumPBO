package MVC_Pattern.controller;

import java.util.ArrayList;

import MVC_Pattern.model.Product;
import MVC_Pattern.view.ProductConsoleView;

public class ProductController {
    private final ProductConsoleView view;
    private final ArrayList<Product> model = new ArrayList<>();
    
    public ProductController(ProductConsoleView view) {
        this.view = view;
        model.add(new Product(1, "Laptop ASUS", 9500000));
        model.add(new Product(2, "Monitor Dell", 2500000));
    }

    public void handlingPilihanMenu(int pilihanMenu) {
        switch (pilihanMenu) {
            case 1:
                displayProductsAction();
                break;
            case 2:
                addProductAction();
                break;
            case 3:
                view.displayMessage("Keluar dari aplikasi.");
                break;
            default:
                view.displayError("Opsi tidak valid.");
        }
    }

    private void displayProductsAction() {
        view.displayAllProducts(model);
    }

    private void addProductAction() {
        String name = view.getProductNameFromUser();
        String priceStr = view.getProductPriceFromUser();
        try {
            long price = Long.parseLong(priceStr);
            if (price <= 0) {
                throw new IllegalArgumentException("Harga harus angka positif lebih dari 0.");
            }
            int newId = model.size() + 1;
            model.add(new Product(newId, name, price));
            view.displayMessage("Produk berhasil ditambahkan!");
        } catch (IllegalArgumentException e) {
            view.displayError("Gagal menambah produk: " + e.getMessage());
        }
    }
}
