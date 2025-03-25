package interfaces;
import models.OrderItem;
import models.Product;

import javax.swing.*;
import java.awt.event.ActionListener;

public interface CartActionListener {

    void increaseProductQuantity(Product product);
    void addProductToCart(Product product);
    void decreaseProductQuantity( JPanel buttonPanel, Product product,JButton addToCartButton,boolean isFromCart);
    void handleQuantityZero(JPanel buttonPanel, Product product,JButton addToCartButton);
    void removeProduct(int productId);
}
