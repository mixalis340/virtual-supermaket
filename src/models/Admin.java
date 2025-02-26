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

    public void addProduct(String name, String description, Category category,String subcategory,double price,int quantity,String type){
        productManager.addProduct(name, description, category,subcategory,price,quantity,type);
    }
    public void removeProduct(Product product){
        productManager.removeProduct(product);
    }
    public void editProduct(Product product,String newName, String newDescription, Category newCategory, String newSubcategory, Double newPrice,int newQuantity){
        productManager.editProduct(product,newName, newDescription,newCategory,newSubcategory,newPrice,newQuantity);
    }

    public void showStatistics(){
        productManager.getAllProducts();
    }
}
