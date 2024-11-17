package api;

import models.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ProductManager {
    private List<Product> products;

    public ProductManager(){
        this.products = new ArrayList<>();
    }

    public List<Product> getProducts(){
        return products;
    }
    public void addProduct(Product product){
        if (products.contains(product)){
            System.out.println("Product already exists.");
        } else {
            products.add(product);
        }
    }

    public void removeProduct(Product product){
        if (!products.contains(product)){
            System.out.println("Product does not exist.");
        } else {
            products.remove(product);
            System.out.println("Product "+product.getName()+" removed.");
        }
    }

    public void editProduct(Product product){
        if(!products.contains(product)){
            System.out.println("This project does not exist.");
        }else {
            for(Product productToEdit: products){
                if(productToEdit.getId() == product.getId()){
                    Scanner scanner = new Scanner(System.in);
                    boolean editing = true;

                    while (editing){
                        System.out.println("Editing Product: " + productToEdit.getName());
                        System.out.println("1. Edit Title");
                        System.out.println("2. Edit Category");
                        System.out.println("3. Edit Quantity");
                        System.out.println("4. Exit Editing");
                        System.out.print("Select an option: ");

                        int choice = scanner.nextInt();
                        scanner.nextLine();

                        switch (choice){
                            case 1:
                                System.out.print("Enter new title: ");
                                String newTitle = scanner.nextLine();
                                productToEdit.setName(newTitle);
                                System.out.println("Title updated to: " + newTitle);
                                break;

                            case 2:
                                System.out.print("Enter new category: ");
                                String newCategory = scanner.nextLine();
                                productToEdit.setCategory(newCategory);
                                System.out.println("Category updated to: " + newCategory);
                                break;

                            case 3:
                                System.out.print("Enter new available quantity: ");
                                int newQuantity = scanner.nextInt();
                                scanner.nextLine();
                                if (newQuantity >= 0) {
                                    productToEdit.setQuantity(newQuantity);
                                    System.out.println("Quantity updated to: " + newQuantity);
                                } else {
                                    System.out.println("Invalid quantity. Quantity cannot be negative.");
                                }
                                break;

                            case 4:
                                editing = false;
                                System.out.println("Exiting edit mode.");
                                break;

                            default:
                                System.out.println("Invalid option. Please choose again.");
                                break;
                        }
                    }
                }
            }
        }
    }

    public void giveProducts(String title, String category, String subcategory){
        List<Product> results = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        for (Product product : products) {
            boolean matchesTitle = title == null || product.getName().toLowerCase().contains(title.toLowerCase());
            boolean matchesCategory = category == null || product.getCategory().toLowerCase().contains(category.toLowerCase());
            boolean matchesSubcategory = subcategory == null || product.getSubcategory().toLowerCase().contains(subcategory.toLowerCase());

            if (matchesTitle && matchesCategory && matchesSubcategory) {
                results.add(product);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No products found.");
        } else {
            System.out.println("Search Results:");
            for (Product product : results) {
                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Category: " + product.getCategory() + ", Subcategory: " + product.getSubcategory());
            }
            System.out.println();
            System.out.println("Give the ID of the product you want to see:");
            int choice = scanner.nextInt();
            scanner.nextLine();
            viewProduct(choice, results);
        }
    }
    public void viewProduct(int productId, List<Product> products){
        boolean productFound = false;
        for(Product product: products) {
            if (product.getId() == productId) {
                productFound = true;
                System.out.println("Product info:");
                System.out.println("Product id: "+product.getId());
                System.out.println("Name: "+product.getName());
                System.out.println("Quantity: "+product.getQuantity());
                break;
            }
        }

        if(!productFound){
            System.out.println("Product with ID " + productId + " does not exist.");
        }
    }
    public void getAllProducts(){
        if (products.isEmpty()) {
            System.out.println("No products available.");
        } else {
            System.out.println("Product List:");
            for (Product product : products) {
                System.out.println("Product ID: " + product.getId());
                System.out.println("Name: " + product.getName());
                System.out.println("Category: " + product.getCategory());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: " + product.getQuantity());
                System.out.println();
            }
        }
    }
}
