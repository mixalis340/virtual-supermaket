package models;

public class Product {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private String category;
    private String subcategory;
    private String description;
    private double price;
    private int quantity;


    public Product(String name, int quantity, double price){
        this.id = idCounter++;
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
    public int getId(){
        return this.id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCategory() {return category;}

    public void setCategory(String category) {this.category = category;}

    public String getSubcategory() {return subcategory;}

    public void setSubcategory(String subcategory) {this.subcategory = subcategory;}

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

}
