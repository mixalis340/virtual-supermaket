package interfaces;

import models.Product;

@FunctionalInterface
public interface ButtonActionListener {
    void onButtonClick(Product product);
}
