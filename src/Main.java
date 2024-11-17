import api.ProductManager;
import models.*;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product product = new Product("chips","Siskevasmena","patatakia",10,2);
        Product product1 = new Product("laptop","Hlektronika","dc",5,100);
        Product product2 = new Product("skoupa","Hlektronika","cleaning",5,50);
        ProductManager productManager = new ProductManager();
        Admin admin = new Admin("mike","123",productManager);
        Customer customer = new Customer("john","123",productManager);

        admin.addProduct(product);
        admin.addProduct(product1);
        admin.addProduct(product2);

        customer.addToCart(product, 7);
        customer.addToCart(product,1);
        customer.addToCart(product1,3);
        customer.displayCart();
        customer.placeOrder();
        customer.displayOrderHistory();

        System.out.println(product1.getQuantity());
    }
}