package gui;
import api.CategoryManager;
import api.ProductManager;
import models.Category;
import models.CountProduct;
import models.Product;
import models.WeightProduct;

import javax.swing.*;
import java.awt.*;

public class ProductRegistrationPanel extends ProductFormPanel {
    public ProductRegistrationPanel(MainFrame parentFrame, CategoryManager categoryManager, ProductManager productManager, AdminPanel adminPanel) {
        super(parentFrame, categoryManager, productManager, adminPanel);
    }

    @Override
    protected String getTitleLabel(){
        return "Προσθήκη προϊόντος";
    }
    @Override
    protected String getSaveButtonText() {
        return "Αποθήκευση";
    }

    @Override
    protected JTextField getNameField(){
        return new JTextField();
    }

    @Override
    protected JTextField getDescriptionField(){
        System.out.println("CALLED");
        return new JTextField();
    }

    @Override
    protected JTextField getPriceField() {
        return new JTextField();
    }

    @Override
    protected JTextField getQuantityField() {
        return new JTextField();
    }

    @Override
    protected String performSave(String name, String description, Category category, String subcategory, double price, int quantity){
        return productManager.addProduct(name,description,category,subcategory,price,quantity,typeField.getText());
    }
}
