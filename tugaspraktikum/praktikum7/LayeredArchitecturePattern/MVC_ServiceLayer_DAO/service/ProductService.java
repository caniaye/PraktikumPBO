package MVC_ServiceLayer_DAO.service;

import java.util.ArrayList;
import MVC_ServiceLayer_DAO.model.Product;

public interface ProductService {
    void addProduct(String name, long price);
    ArrayList<Product> getAllProducts();
}
