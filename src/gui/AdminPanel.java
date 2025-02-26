package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

import api.ProductManager;
import models.Product;

import java.util.ArrayList;
import java.util.List;

public class AdminPanel extends JPanel {
    private MainFrame parentFrame;
    private List<Product> products;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> filterDropdown;
    private JPanel gridPanel;
    private ProductManager productManager;
    private JLabel noProductsLabel;

    public AdminPanel(MainFrame parentFrame,List<Product> products,ProductManager productManager) {
        System.out.println("ADMIN CONSTRUCTOR ACTIVATED!!!!");
        this.parentFrame = parentFrame;
        this.products = products;
        this.productManager = productManager;
        this.noProductsLabel = new JLabel("Δεν υπάρχουν σχετικά προϊόντα");
        setLayout(new BorderLayout());

        JPanel topBarPanel = createTopBar();
        add(topBarPanel, BorderLayout.NORTH);

        gridPanel = createProductGridPanel();
        JScrollPane jScrollPane = new JScrollPane(gridPanel);
        add(jScrollPane, BorderLayout.CENTER);

        addBottomBar();
    }
    private JPanel createTopBar() {
        JPanel topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));

        // Search label and field
        JLabel searchLabel = new JLabel("Αναζήτηση προϊόντος: ");
        searchField = new JTextField(20); // TextField for search (20 characters wide)

        // Search button
        searchButton = createButton("Αναζήτηση",e->handleSearchProducts(searchField.getText()), new Dimension(100,25));

        filterDropdown = new JComboBox<>(new String[]{"Όλα τα προϊόντα", "Μη διαθέσιμα", "Πιο δημοφιλή"});
        filterDropdown.addActionListener(e->handleFilterSelection((String) filterDropdown.getSelectedItem()));

        // Add search components to the top bar panel
        topBarPanel.add(searchLabel);
        topBarPanel.add(searchField);
        topBarPanel.add(searchButton);
        topBarPanel.add(filterDropdown);

        return topBarPanel;
    }

    private void addBottomBar() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton addProductButton = createButton("Προσθήκη προϊόντος",e-> parentFrame.switchPanel("ProductRegistration") ,new Dimension(160, 40));
        bottomPanel.add(addProductButton);
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        add(bottomPanel, BorderLayout.SOUTH);
    }

    private JPanel createProductGridPanel() {
        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        for (Product product : products) {
            ProductRowPanel productRowPanel = new ProductRowPanel(product);
            gridPanel.add(productRowPanel);
            addActionButtons(productRowPanel, product);
        }
        return gridPanel;
    }
    public void updateProductGrid(List<Product> filteredProducts) {
        gridPanel.removeAll();
        if (filteredProducts == null || filteredProducts.isEmpty()) {
            gridPanel.setLayout(new GridBagLayout());
            noProductsLabel.setFont(new Font("Arial", Font.BOLD, 14));
            noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            gridPanel.add(noProductsLabel);
        } else {
            gridPanel.setLayout(new GridLayout(0, 3, 10, 10));
            for (Product product : filteredProducts) {
                ProductRowPanel productRowPanel = new ProductRowPanel(product);
                gridPanel.add(productRowPanel);
                addActionButtons(productRowPanel, product);
            }
        }
        gridPanel.revalidate();
        gridPanel.repaint();
    }

    // Helper method to add action buttons to each product row
    private void addActionButtons(JPanel productRowPanel,Product product) {
        JButton removeButton = createButton("Αφαίρεση", e -> handleRemoveProduct(product),new Dimension(110, 30));
        JButton editButton = createButton("Επεξεργασία", e -> handleEditProduct(product),new Dimension(110, 30));


        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);


        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.setBackground(Color.white);

       productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
    }

    private static JButton createButton(String text, ActionListener actionListener, Dimension dimension) {
        JButton button = new JButton(text);
        button.addActionListener(actionListener);
        button.setPreferredSize(dimension);
        return button;
    }

    public void handleFilterSelection(String selectedFilter){
        switch (selectedFilter) {
            case "Όλα τα προϊόντα":
                updateProductGrid(productManager.getProducts());
                break;
            case "Μη διαθέσιμα":
                updateProductGrid(productManager.getUnavailableProducts());
                break;
            case "Πιο δημοφιλή":
//                updateProductGrid(productManager.getMostPopularProducts());
                break;
            default:
                break;
        }
    }
    public void handleSearchProducts(String name){
        String selectedFilter = (String) filterDropdown.getSelectedItem();
        List<Product> filteredProducts = productManager.filterAndSearchProducts(selectedFilter, name);
        updateProductGrid(filteredProducts);
    }

    private void handleRemoveProduct(Product product) {
        System.out.println("Product removed: " + product.getName());
        productManager.removeProduct(product);
        updateProductGrid(productManager.getProducts());
    }

    private void handleEditProduct(Product product) {
        System.out.println("Editing product: " + product.getName());
        parentFrame.switchToEditProductPanel(product);
    }
}
