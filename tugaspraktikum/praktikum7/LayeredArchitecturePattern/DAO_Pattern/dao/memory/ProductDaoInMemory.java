package LayeredArchitecturePattern.DAO_Pattern.dao.memory;

import java.util.ArrayList;
import LayeredArchitecturePattern.DAO_Pattern.dao.ProductDao;
import LayeredArchitecturePattern.DAO_Pattern.model.Product;

public class ProductDaoInMemory implements ProductDao {

    private final ArrayList<Product> storage = new ArrayList<>();

    public ProductDaoInMemory() {
        storage.add(new Product(1, "Laptop ASUS", 9500000));
        storage.add(new Product(2, "Monitor Dell", 2500000));
    }

    @Override
    public void save(Product product) {
        storage.add(product);
    }

    @Override
    public ArrayList<Product> findAll() {
        return storage;
    }
}
