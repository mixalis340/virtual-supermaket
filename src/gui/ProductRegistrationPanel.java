package gui;

import api.CategoryManager;
import models.Product;

import javax.swing.*;
import java.awt.*;

public class ProductRegistrationPanel extends ProductFormPanel {
    public ProductRegistrationPanel(MainFrame parentFrame, CategoryManager categoryManager) {
        super(parentFrame, categoryManager);
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
    protected JTextField getPriceField() {
        return new JTextField();
    }

    @Override
    protected JTextField getQuantityField() {
        return new JTextField();
    }
}
