import models.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product chips = new Product("chips",15,2);
        Cart cart = new Cart();
        cart.addProduct(chips, 5);
        cart.addProduct(chips, 3);

        cart.updateItemQuantity(1, 9);
        cart.displayCartInfo();
        Order order = new Order(cart.getItems(), cart.getTotalCartValue());
        order.displayOrderInfo();


    }
}