package api;

import models.Category;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryManager {
    private final List<Category> categories;
    public CategoryManager(List<Category> categories){
        this.categories = categories;
    }

    public List<Category> getCategories(){return categories;}

    public Category getCategoryByTitle(String title){
        for(Category category: categories){
            if(title.equals(category.getTitle())){
                return category;
            }
        }
        return null;
    }
}
