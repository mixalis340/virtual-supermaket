package api;

import models.Category;
import models.CountProduct;
import models.Product;
import models.WeightProduct;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class ProductFileManager {
    private List<Product> products;
    private String filePath;
    private CategoryManager categoryManager;

    public ProductFileManager(CategoryManager categoryManager, String filePath){
        this.products = new ArrayList<>();
        this.categoryManager = categoryManager;
        this.filePath = filePath;
    }

    public List<Product> loadProductsFromFile(){
        try(BufferedReader br = new BufferedReader(new FileReader(filePath))){
            String line;
            int lineCounter = 0;
            List<String> parts = new ArrayList<>();
            String productName;
            String productDescription;
            String productCategory;
            String productSubcategory;
            String productPrice;
            String productQuantity;

            while ((line = br.readLine()) != null){
                if (line.isEmpty()){
                    continue;
                }
                String part = line.split(": ", 2)[1];
                parts.add(part);
                lineCounter += 1;

                if (lineCounter == 6){
                    productName = parts.get(0);
                    productDescription = parts.get(1);
                    productCategory = parts.get(2);
                    productSubcategory = parts.get(3);
                    productPrice = parts.get(4).replace("€", "").replace(",", ".").trim();;
                    productQuantity = parts.get(5);


                    double price = Double.parseDouble(productPrice);
                    int quantity = Integer.parseInt(productQuantity.replaceAll("\\D", ""));

                    Category category = categoryManager.getCategoryByTitle(productCategory);

                    if (parts.get(5).contains("kg")){
                        products.add(new WeightProduct(
                                productName,
                                productDescription,
                                category,
                                category.getSubcategory(productSubcategory),
                                price,
                                quantity));
                    }else{
                        products.add(new CountProduct(
                                productName,
                                productDescription,
                                category,
                                category.getSubcategory(productSubcategory),
                                price,
                                quantity));
                    }
                    parts.clear();
                    lineCounter = 0;
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }
        return products;
    }
}
