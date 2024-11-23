package models;

public class CountProduct extends Product{
    private int quantity;

    public CountProduct(String name, String description,Category category,String subcategory,double price, int quantity){
        super(name, description,category, subcategory, price);
        this.quantity = quantity;
    }

    @Override
    public int getQuantity(){
        return quantity;
    }

    @Override
    public void setQuantity(int quantity){
        this.quantity = quantity;
    }

    @Override
    public String getUnit(){
        return " τεμάχια";
    }
}
