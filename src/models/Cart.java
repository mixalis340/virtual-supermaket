package models;

import java.util.ArrayList;

public class Cart {
    private ArrayList<OrderItem> items;

    public Cart(){
        this.items = new ArrayList<>();
    }

    public void addProduct(Product product, int quantity){
        if (quantity > product.getQuantity()) {
            System.out.println("Cannot add " + quantity + " " + product.getName() + " to the cart. Not enough stock available.");
            return;
        }
        for (OrderItem item : items) {
            if (item.getProduct().getId() == product.getId()) {
                if (item.getQuantity() + quantity > product.getQuantity()) {
                    System.out.println("Cannot add " + quantity + " more. Only " + product.getQuantity() + " available.");
                    return;
                }
                item.setQuantity(item.getQuantity() + quantity);
                return;
            }
        }
        items.add(new OrderItem(product, quantity, product.getPrice()));
    }

    public void removeItem(int productId){
        items.removeIf(item -> item.getProduct().getId() == productId);
    }
    public boolean updateItemQuantity(int productId, int newQuantity){
        for (OrderItem item : items) {
            if (item.getProduct().getId() == productId) {
                if (newQuantity <= item.getProduct().getQuantity()) {
                    item.setQuantity(newQuantity);
                    return true;
                }
                return false;
            }
        }
        return false;
    }
    public double getTotalCartValue(){
        double value = 0.0;
        for (OrderItem item: items){
            value += item.getTotalPrice();
        }
        return value;
    }
    public void displayCartInfo(){
        System.out.println("Info for this Cart:");
        int i = 0;
        for (OrderItem item: items){
            i++;
            System.out.println("Product " + i);
            System.out.println(item.getProduct().getName());
            System.out.println("Quantity: "+ item.getQuantity());
            System.out.println("Total cost of x"+item.getQuantity()+" "+item.getProduct().getName()+ " is " + item.getTotalPrice());
            System.out.println();
        }
        double totalCartValue = getTotalCartValue();
        System.out.println("Total Cart value is: "+totalCartValue+"€");
        System.out.println();
    }
    public ArrayList<OrderItem> getItems(){
        return items;
    }
}
