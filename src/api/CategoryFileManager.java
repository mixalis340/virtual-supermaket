package api;

import models.Category;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class CategoryFileManager {
    private String filePath;
    private List<Category> categories;

    public CategoryFileManager(String filePath){
        this.filePath = filePath;
        this.categories = new ArrayList<>();
    }

    public List<Category> loadCategoriesFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                if (line.isEmpty()) {
                    continue;
                }
                String[] parts = line.split("\\(");
                String category = parts[0].trim();
                String subcategories = parts[1].replace(")", "").trim();

                String[] subcategoryList = subcategories.split("@");

                categories.add(new Category(category, Arrays.stream(subcategoryList).toList()));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return categories;
    }
}
