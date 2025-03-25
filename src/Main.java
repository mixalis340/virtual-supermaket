import api.*;
import gui.MainFrame;
import gui.ProductGridPanel;
import models.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.List;

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
        Admin admin = new Admin("mike", "123", productManager);
        UserManager userManager = new UserManager();

        userManager.registerUser(admin);


        MainFrame mainFrame = new MainFrame(userManager, products, categoryManager, productManager);
    }
}