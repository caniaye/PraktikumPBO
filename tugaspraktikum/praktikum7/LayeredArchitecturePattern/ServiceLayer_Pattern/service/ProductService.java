package ServiceLayer_Pattern.service;

import java.util.ArrayList;
import ServiceLayer_Pattern.model.Product;

public interface ProductService {
    void addProduct(String name, long price);
    ArrayList<Product> getAllProducts();
}
