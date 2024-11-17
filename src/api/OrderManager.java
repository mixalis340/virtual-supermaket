package api;
import models.Cart;
import models.Order;
import models.OrderItem;
import models.Product;

import java.util.List;

public class OrderManager {

    public OrderManager(){

    }
    public void makeOrder(Cart cart, List<Order> orderHistory, ProductManager productManager){
        if(cart.getItems().isEmpty()){
            System.out.println("Cart is empty. Cannot place an order.");
            return;
        }
        orderHistory.add(new Order(cart.getItems(),cart.getTotalCartValue()));

        updateProductQuantities(cart.getItems(),productManager.getProducts());

        cart.clearCart();
        System.out.println("Order placed successfully!");
    }

    public void updateProductQuantities(List<OrderItem> orderItems, List<Product> products){
        for(OrderItem cartItem: orderItems){
            for(Product product: products){
                if(cartItem.getProduct().getId() == product.getId()){
                    int newQuantity = product.getQuantity() - cartItem.getQuantity();
                    product.setQuantity(newQuantity);
                }
            }
        }
    }
    public void displayOrderHistory(List<Order> orderHistory){
        if(orderHistory.isEmpty()){
            System.out.println("You haven't created any order yet.");
        } else
            for(Order order: orderHistory){
                order.displayOrderInfo();
            }
    }
}
