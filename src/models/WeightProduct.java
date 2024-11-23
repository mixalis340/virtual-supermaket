package models;

public class WeightProduct extends Product{
    private int weight;

    public WeightProduct(String name, String description,Category category,String subcategory,double price, int weight){
        super(name, description,category, subcategory, price);
        this.weight = weight;
    }

    @Override
    public int getQuantity(){
        return weight;
    }

    @Override
    public void setQuantity(int weight){
        this.weight = weight;
    }

    @Override
    public String getUnit(){
        return " kg";
    }
}
