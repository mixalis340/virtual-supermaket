package models;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Order {
    private Date date;
    private List<OrderItem> products;
    private double totalCost;
    private final int orderNumber;

    public Order(List<OrderItem> products,double totalCost, int orderNumber){
        this.date = new Date();
        this.products = new ArrayList<>();
        this.totalCost = totalCost;
        this.orderNumber = orderNumber;
    }

    public void displayOrderInfo() {
        System.out.println("Order #" + orderNumber);
        System.out.println("Date: " + date);
        for (OrderItem item : products) {
            System.out.println("Product: " + item.getProduct().getName());
            System.out.println("Quantity: " + item.getQuantity());
            System.out.println("Cost: " + item.getTotalPrice() + "€");
        }
        System.out.println("Total cost: " + totalCost + "€");
    }
}
