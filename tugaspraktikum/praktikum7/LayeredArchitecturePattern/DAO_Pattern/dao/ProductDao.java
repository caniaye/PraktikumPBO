package LayeredArchitecturePattern.DAO_Pattern.dao;

import java.util.ArrayList;
import LayeredArchitecturePattern.DAO_Pattern.model.Product;

public interface ProductDao {
    void save(Product product);
    ArrayList<Product> findAll();
}
