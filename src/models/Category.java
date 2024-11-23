package models;

import java.util.List;

public class Category {
    private final String title;
    private final List<String> subcategories;

    public Category(String title, List<String> subcategories){
        this.title = title;
        this.subcategories = subcategories;
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
}
