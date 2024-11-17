package models;

import java.util.List;

public class Customer extends User{
    private Cart cart;
    private List<Order> orderHistory;

    public Customer(String username, String password){
        super(username, password, "Customer");
    }

    @Override
    public boolean login() {
        return false;
    }

    @Override
    public boolean logout() {
        return false;
    }

    public void searchProduct() {

    }

    public void addToCart() {

    }

    public void removeFromCart() {

    }

    public boolean updateCartQuantity() {
      return false;
    }

    public void placeOrder() {

    }

    public List<Order> viewOrderHistory() {
       return null;
    }

}
