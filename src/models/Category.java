package models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Category {
    private final String title;
    private final List<String> subcategories;
    private final Map<String,String> subCategoriesType;

    public Category(String title, List<String> subcategories){
        this.title = title;
        this.subcategories = subcategories;
        this.subCategoriesType= setSubCategoriesType(this.subcategories);
    }

    public String getTitle() {
        return title;
    }

    public List<String> getSubcategories() {
        return subcategories;
    }

    public void displayCategoryInfo() {
        System.out.println("Category: " + title);
        if (subcategories != null && !subcategories.isEmpty()) {
            System.out.println("Subcategories: ");
            for (String subcategory : subcategories) {
                System.out.println("- " + subcategory);
            }
        } else {
            System.out.println("No subcategories available.");
        }
    }

    public String getSubcategory(String title){
        for(String existingTitle: subcategories){
            if(existingTitle.equals(title)){
                return title;
            }
        }
        return null;
    }
    public String getSubcategoryType(String subcategoryName){
        String subcategory = getSubcategory(subcategoryName);
        return subCategoriesType.get(subcategory);
    }

    public Map<String,String> setSubCategoriesType(List<String> subcategories){
        Map<String, String> typeMap = new HashMap<>();
        List<String> kgCategories = List.of("Φρούτα", "Λαχανικά");

        for (String subCategory : subcategories) {
            if (kgCategories.contains(subCategory)) {
                typeMap.put(subCategory, "kg");
            } else {
                typeMap.put(subCategory, "τεμάχια");
            }
        }
        return typeMap;
    }
}
