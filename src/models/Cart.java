package models;
import java.util.ArrayList;
import java.util.List;

public class Cart {
    private ArrayList<OrderItem> cartItems;

    public Cart(){
        this.cartItems = new ArrayList<>();
    }

    public void addProductToCart(Product product){
        cartItems.add(new OrderItem(product,1));
        System.out.println("Product "+product.getName()+" added to cart!");
    }

    public void increaseProductQuantity(Product product){
        OrderItem orderItem = findOrderItem(product);
        if (orderItem!=null){
            orderItem.increaseQuantity();
            System.out.println("Updated " + product.getName() + " to quantity: " + orderItem.getQuantity());
        }
    }

    public String decreaseQuantity(Product product){
        OrderItem orderItem = findOrderItem(product);
        if (orderItem!= null) {
            if (orderItem.getQuantity() == 1) {
                removeItem(product.getId());
                System.out.println(product.getName()+ " removed!");
                return "product removed";
            }
            else{
                orderItem.decreaseQuantity();
                System.out.println("Updated " + product.getName() + " to quantity: " + orderItem.getQuantity());
                return "quantity decreased";
            }
        }
        return null;
    }
    public void removeItem(int productId) {
        cartItems.removeIf(item -> {
            if (item.getProduct().getId() == productId) {
                System.out.println("Removed product: " + item.getProduct().getName());
                return true; // Remove this item
            }
            return false; // Keep the item
        });
    }
    public boolean updateItemQuantity(int productId, int newQuantity){
        for (OrderItem item : cartItems) {
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
        for (OrderItem item: cartItems){
            value += item.getTotalPrice();
        }
        return value;
    }
    public void displayCartInfo(){
        System.out.println("Info for this Cart:");
        int i = 0;
        for (OrderItem item: cartItems){
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
    public List<OrderItem> getItems(){
        return cartItems;
    }
    public List<Product> getCartProducts(){
        List<Product> products = new ArrayList<>();
        for(OrderItem item: cartItems){
            products.add(item.getProduct());
        }
        return products;
    }
    public OrderItem findOrderItem(Product product){
        for (OrderItem orderItem: cartItems){
            if (orderItem.getProduct().getId() == product.getId())
                return orderItem;
        }
        return null;
    }
    public void clearCart(){
        cartItems.clear();
    }
}