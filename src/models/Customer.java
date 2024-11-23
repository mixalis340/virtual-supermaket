package models;

import api.OrderManager;
import api.ProductManager;

import java.util.ArrayList;
import java.util.List;

public class Customer extends User{
    private Cart cart;
    private List<Order> orderHistory;
    private OrderManager orderManager;
    private ProductManager productManager;

    public Customer(String username, String password, ProductManager productManager, OrderManager orderManager){
        super(username, password, "Customer", productManager);
        this.cart = new Cart();
        this.orderHistory = new ArrayList<>();
        this.orderManager = orderManager;
        this.productManager = productManager;
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }


    public void addToCart(Product product, int quantity) {
        cart.addProduct(product, quantity);
    }

    public void removeFromCart(int productId) {
        cart.removeItem(productId);
    }

    public void displayCart(){
        cart.displayCartInfo();
    }

    public boolean updateCartQuantity(int productId, int newQuantity) {
        return cart.updateItemQuantity(productId, newQuantity);
    }

    public void placeOrder() {
        orderManager.makeOrder(cart, orderHistory, productManager, orderHistory.size() + 1);
    }

    public void displayOrderHistory() {
       orderManager.displayOrderHistory(orderHistory);
    }

}
