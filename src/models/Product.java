package models;

public abstract class Product {
    private static int idCounter = 1;
    private final int id;
    private String name;
    private Category category;
    private String subcategory;
    private String description;
    private double price;


    public Product(String name, String description, Category category,String subcategory,  double price){
        this.id = idCounter++;
        this.name = name;
        this.description = description;
        this.price = price;
        this.category = category;
        this.subcategory = this.category.getSubcategory(subcategory);
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

    public Category getCategory() {return category;}

    public void setCategory(Category category){this.category = category;}
    public String getSubcategory(){return subcategory;}
    public void setSubcategory(String subcategory){this.subcategory = subcategory;}
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

    public abstract int getQuantity();
    public abstract void setQuantity(int quantity);
    public abstract String getUnit();
}
