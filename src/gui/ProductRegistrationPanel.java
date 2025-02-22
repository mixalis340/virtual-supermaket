package gui;

import models.Product;

import javax.swing.*;
import java.awt.*;

public class ProductRegistrationPanel extends ProductFormPanel {
    public ProductRegistrationPanel(MainFrame parentFrame) {
        super(parentFrame);
    }

    @Override
    protected void setTitleLabel(String title) {
        return;
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
    protected JTextField getCategoryField() {
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
