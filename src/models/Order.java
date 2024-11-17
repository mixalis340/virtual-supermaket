package models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private static int idCounter = 1;
    private final int orderId;
    private Date date;
    private List<OrderItem> products;
    private double totalCost;

    public Order(List<OrderItem> products,double totalCost){
        this.orderId = idCounter++;
        this.date = new Date();
        this.products = new ArrayList<>();
        this.totalCost = totalCost;
    }

    public void displayOrderInfo() {
        System.out.println("Order #" + orderId);
        System.out.println("Date: " + date);
        for (OrderItem item : products) {
            System.out.println("Product: " + item.getProduct().getName());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Cost: " + item.getTotalPrice() + "€");
        }
        System.out.println("Total cost: " + totalCost + "€");
    }
}
