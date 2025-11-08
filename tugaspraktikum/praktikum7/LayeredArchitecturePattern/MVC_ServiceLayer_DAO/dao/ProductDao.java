package MVC_ServiceLayer_DAO.dao;

import java.util.ArrayList;
import MVC_ServiceLayer_DAO.model.Product;

public interface ProductDao {
    void save(Product product);
    ArrayList<Product> findAll();
}
