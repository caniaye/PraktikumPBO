package MVC_ServiceLayer_DAO.service;

import java.util.ArrayList;
import MVC_ServiceLayer_DAO.dao.ProductDao;
import MVC_ServiceLayer_DAO.model.Product;

public class ProductServiceDefault implements ProductService {
    
    private final ProductDao productDao;
    
    public ProductServiceDefault(ProductDao productDao) {
        this.productDao = productDao;
    }

    @Override
    public void addProduct(String name, long price) {
        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Nama tidak boleh kosong.");
        }
        if (price <= 0) {
            throw new IllegalArgumentException("Harga harus > 0");
        }
        productDao.save(new Product(0, name, price));
    }

    @Override
    public ArrayList<Product> getAllProducts() {
        return productDao.findAll();
    }
}
