import api.*;
import gui.MainFrame;
import models.*;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        CategoryFileManager categoryFileManager = new CategoryFileManager("src/data/categories_subcategories.txt");
        List<Category> categories = categoryFileManager.loadCategoriesFromFile();

        CategoryManager categoryManager = new CategoryManager(categories);
        ProductFileManager productFileManager = new ProductFileManager(categoryManager, "src/data/products.txt");
        List<Product> products = productFileManager.loadProductsFromFile();

        ProductManager productManager = new ProductManager(categoryManager, products);
        Admin admin = new Admin("mike","123", productManager);
        UserManager userManager = new UserManager();

        userManager.registerUser(admin);


        MainFrame mainFrame = new MainFrame(userManager, products, categoryManager, productManager);

//        int[] array = {5,5,5,5};
//        test(array);
    }

    public static void test(int[] array) {

    }

}