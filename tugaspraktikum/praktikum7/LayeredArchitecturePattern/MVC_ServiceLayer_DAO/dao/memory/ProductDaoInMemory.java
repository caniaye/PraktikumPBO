package MVC_ServiceLayer_DAO.dao.memory;

import java.util.ArrayList;
import MVC_ServiceLayer_DAO.dao.ProductDao;
import MVC_ServiceLayer_DAO.model.Product;

public class ProductDaoInMemory implements ProductDao {
    
    private final ArrayList<Product> storage = new ArrayList<>();
    private int nextId = 1;

    public ProductDaoInMemory() {
        storage.add(new Product(1, "Laptop ASUS", 9500000));
        storage.add(new Product(2, "Monitor Dell", 2500000));
        nextId = 3;
    }

    @Override
    public void save(Product p) {
        if (p.getId() == 0) {
            p.setId(nextId++);
        }
        storage.add(p);
    }

    @Override
    public ArrayList<Product> findAll() {
        return storage;
    }
}
