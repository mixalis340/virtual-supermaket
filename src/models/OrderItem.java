package models;

public class OrderItem {
    private Product product;
    private int quantity;
    private double price;

    public OrderItem(Product product, int quantity, double price){
        this.product = product;
        this.quantity =  quantity;
        this.price = price;
    }

    public Product getProduct(){
        return product;
    }
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }
    public int getQuantity(){
        return quantity;
    }

    public double getTotalPrice(){
        return quantity * price;
    }
}