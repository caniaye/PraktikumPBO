package MVC_ServiceLayer_DAO;

import java.util.Scanner;

import MVC_ServiceLayer_DAO.controller.ProductController;
import MVC_ServiceLayer_DAO.dao.ProductDao;
import MVC_ServiceLayer_DAO.dao.memory.ProductDaoInMemory;
import MVC_ServiceLayer_DAO.service.ProductService;
import MVC_ServiceLayer_DAO.service.ProductServiceDefault;
import MVC_ServiceLayer_DAO.view.ProductConsoleView;

public class Main {
    public static void main(String[] args) {
        
        // Layer DAO
        ProductDao dao = new ProductDaoInMemory();
        
        // Layer Service
        ProductService service = new ProductServiceDefault(dao);
        
        // Layer View
        Scanner scanner = new Scanner(System.in);
        ProductConsoleView view = new ProductConsoleView(scanner);
        
        // Layer Controller
        ProductController controller = new ProductController(service, view);
        
        // Loop Menu Utama
        while (true) {
            view.displayMenu();
            String pilihanMenu = view.getPilihanMenuFromUser();
            controller.handlingPilihanMenu(pilihanMenu);
        }
    }
}
