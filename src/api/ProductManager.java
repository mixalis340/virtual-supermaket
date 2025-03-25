package api;

import models.*;

import java.util.*;

public class ProductManager {
    private List<Product> products;
    private List<OrderItem> orderItems;
    private CategoryManager categoryManager;

    public ProductManager(CategoryManager categoryManager, List<Product> products){
        this.categoryManager = categoryManager;
        this.products = products;
        this.orderItems = initializeOrderItems();
    }

    
    public List<Product> getProducts(){
        return products;
    }
    public List<OrderItem> initializeOrderItems(){
        List<OrderItem> orderItems = new ArrayList<>();
        for (Product product : products) {
            orderItems.add(new OrderItem(product, 0));
        }
        return orderItems;
    }

    public List<OrderItem> getOrderItems(){
        return orderItems;
    }
    public String addProduct(String name, String description, Category category,String subcategory,double price,int quantity,String type){
        try {
            // I assume name and description are already validated in the GUI
            if (type.equals("kg")) {
                products.add(new WeightProduct(name, description, category, subcategory, price, quantity));
            } else {
                products.add(new CountProduct(name, description, category, subcategory, price, quantity));
            }
            return "Το προϊόν αποθηκεύτηκε επιτυχώς!";
        } catch (Exception e) {
            return "Παρουσιάστηκε σφάλμα κατά την αποθήκευση του προϊόντος.";
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

    public String editProduct(Product product,String newName, String newDescription, Category newCategory, String newSubcategory,double newPrice,int newQuantity){
           product.setName(newName);
           product.setDescription(newDescription);
           product.setCategory(newCategory);
           product.setSubcategory(newSubcategory);
           product.setPrice(newPrice);
           product.setQuantity(newQuantity);
           return "Το προϊόν επεξεργάστηκε επιτυχώς!";
        }

        public List<Product> getMostPopularProducts(List<Order> totalOrders){
            Map<Product, Integer> products = new HashMap<>();

            for (Order order: totalOrders){
                Set<Product> uniqueProductsInOrder = new HashSet<>();
                for (OrderItem orderItem: order.getOrderItems()){
                    uniqueProductsInOrder.add(orderItem.getProduct());
                }

                for (Product product : uniqueProductsInOrder) {
                    if (products.containsKey(product)) {
                        products.put(product, products.get(product) + 1);
                    } else {
                        products.put(product, 1);
                    }
                }
            }

            int maxCount = 0;
            for (int count : products.values()) {
                if (count > maxCount) {
                    maxCount = count;
                }
            }

            List<Product> mostPopularProducts = new ArrayList<>();
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                if (entry.getValue() == maxCount) {
                    mostPopularProducts.add(entry.getKey());
                }
            }

            return mostPopularProducts;
        }

        public List<Product> getUnavailableProducts(){
            List<Product> unavailableProducts = new ArrayList<>();

            for (Product product: products){
                if (product.getQuantity() == 0){
                    unavailableProducts.add(product);
                }
            }
            return unavailableProducts;
         }

    public List<Product> giveProducts(List<Product> products,String title, String category, String subcategory){
        List<Product> results = new ArrayList<>();
        for (Product product : products) {
            boolean matchesTitle = title == null || product.getName().toLowerCase().contains(title.toLowerCase());
            boolean matchesCategory = category == null || product.getCategory().getTitle().toLowerCase().contains(category.toLowerCase());
            boolean matchesSubcategory = subcategory == null || product.getSubcategory().toLowerCase().contains(subcategory.toLowerCase());

            if (matchesTitle && matchesCategory && matchesSubcategory) {
                results.add(product);
            }
        }
        if (results.isEmpty()) {
            System.out.println("No products found.");}
        return results;

//        } else {
//            System.out.println("Search Results:");
//            for (Product product : results) {
//                System.out.println("ID: " + product.getId() + ", Name: " + product.getName() + ", Category: " + product.getCategory().getTitle() + ", Subcategory: " + product.getSubcategory());
//            }
//            System.out.println();
//            System.out.println("Give the ID of the product you want to see:");
//            int choice = scanner.nextInt();
//            scanner.nextLine();
//            viewProduct(choice, results);
//        }
    }
    public List<Product> filterAndSearchProducts(String filter, String name) {
        List<Product> filteredProducts;
        switch (filter) {
            case "Όλα τα προϊόντα":
                filteredProducts = getProducts();
                break;
            case "Μη διαθέσιμα":
                filteredProducts = getUnavailableProducts();
                break;
            case "Πιο δημοφιλή":
                filteredProducts = new ArrayList<>();
                break;
            default:
                filteredProducts = new ArrayList<>();
        }
        return giveProducts(filteredProducts, name, null, null);
    }

    public void viewProduct(int productId, List<Product> products){
        boolean productFound = false;
        for(Product product: products) {
            if (product.getId() == productId) {
                productFound = true;
                System.out.println("Product info:");
                System.out.println("Product id: "+product.getId());
                System.out.println("Name: "+product.getName());
                System.out.println("Quantity: "+product.getQuantity() + product.getUnit());
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
                System.out.println("Category: " + product.getCategory().getTitle());
                System.out.println("Subcategory " + product.getSubcategory());
                System.out.println("Price: " + product.getPrice());
                System.out.println("Quantity: "+product.getQuantity() + product.getUnit());
                System.out.println();
            }
        }
    }
}
