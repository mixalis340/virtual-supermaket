package api;
import models.Cart;
import models.Order;
import models.OrderItem;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class OrderManager {
   private List<Order> orders;

    public OrderManager(){
        this.orders = new ArrayList<>();
    }
    public void makeOrder(Cart cart, List<Order> orderHistory, ProductManager productManager,int orderNumber){
        if(cart.getItems().isEmpty()){
            System.out.println("Cart is empty. Cannot place an order.");
            return;
        }
        List<OrderItem> orderItemsCopy = new ArrayList<>(cart.getItems());
        Order order = new Order(orderItemsCopy,cart.getTotalCartValue(), orderNumber);

        orders.add(order);
        orderHistory.add(order);
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

    public List<Order> getAllOrders(){
        return this.orders;
    }
}
