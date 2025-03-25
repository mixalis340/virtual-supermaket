package models;

import javax.swing.*;
import java.awt.*;

public class OrderItem {
    private Product product;
    private int quantity;
    private JLabel quantityLabel;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
        this.quantityLabel = new JLabel(" " + 1 + " ");
        this.quantityLabel.setFont(new Font("Arial", Font.PLAIN, 20));
    }

    public void increaseQuantity() {
        this.quantity++;
    }

    public void decreaseQuantity() {
        this.quantity--;
    }

    public Product getProduct() {
        return product;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public void setQuantityLabel(int quantity){this.quantityLabel.setText(" " + quantity + " ");}

    public int getQuantity() {
        return quantity;
    }

    public JLabel getQuantityLabel(){
        return quantityLabel;
    }

    public double getTotalPrice() {
        double value = quantity * product.getPrice();
        return Math.round(value * 100.0) / 100.0;
    }
}
