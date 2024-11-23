package models;

import api.ProductManager;

public class Admin extends User {
    ProductManager productManager;
    public Admin(String username, String password, ProductManager productManager){
        super(username, password, "Admin", productManager);
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

    public void addProduct(Product product){
        productManager.addProduct(product);
    }
    public void removeProduct(Product product){
        productManager.removeProduct(product);
    }
    public void editProduct(int productId){
        productManager.editProduct(productId);
    }

    public void showStatistics(){
        productManager.getAllProducts();
    }
}
