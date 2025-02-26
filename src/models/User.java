package models;

import api.ProductManager;

import java.util.List;

public abstract class User {
    private String username;
    private String password;
    private String role;
    private ProductManager productManager;

    public User(String username, String password, String role, ProductManager productManager){
        this.username = username;
        this.password = password;
        this.role = role;
        this.productManager = productManager;
    }

    public String getUsername(){
        return username;
    }
    public String getPassword(){
        return password;
    }

    public String getRole(){
        return role.toLowerCase();
    }

    public List<Product> giveProducts(List<Product> products,String title, String category, String subcategory){
        return productManager.giveProducts(products,title,category,subcategory);
    }
    public abstract boolean login();
    public abstract boolean logout();

}
