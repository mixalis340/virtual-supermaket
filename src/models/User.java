package models;

import api.ProductManager;

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
        return role;
    }

    public void giveProducts(String title, String category, String subcategory){
        productManager.giveProducts(title,category,subcategory);
    }
    public abstract boolean login();
    public abstract boolean logout();

}
