import api.*;
import gui.MainFrame;
import models.*;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        CategoryManager categoryManager = new CategoryManager();
        FileManager fileManager = new FileManager("src/data/products.txt",categoryManager);
        List<Product> products = fileManager.getProducts();

        ProductManager productManager = new ProductManager(products, categoryManager);
        OrderManager orderManager = new OrderManager();

        Admin admin = new Admin("john","123", productManager);
        admin.showStatistics();
    }
}