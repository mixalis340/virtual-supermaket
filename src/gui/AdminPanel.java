package gui;
import javax.swing.*;
import java.awt.*;

import api.ProductManager;
import interfaces.BottomBarListener;
import interfaces.TopBarListener;
import models.Product;

import java.util.List;

public class AdminPanel extends JPanel implements TopBarListener, BottomBarListener {
    private MainFrame parentFrame;
    private JTextField searchField;
    private JButton searchButton;
    private JComboBox<String> filterDropdown;
    private ProductGridPanel productGridPanel;
    private ProductManager productManager;
    private TopBar topBar;
    private BottomBar bottomBar;

    public AdminPanel(MainFrame parentFrame,ProductManager productManager) {
        System.out.println("ADMIN CONSTRUCTOR ACTIVATED!!!!");
        this.parentFrame = parentFrame;
        this.productManager = productManager;
        setLayout(new BorderLayout());

        topBar = new TopBar(new FlowLayout(FlowLayout.LEFT, 10, 10),this,this);
        add(topBar, BorderLayout.NORTH);

//        gridPanel = createProductGridPanel();
        productGridPanel = new ProductGridPanel(productManager, true,null,this::handleRemoveProduct,this::handleEditProduct,null,null);
        JScrollPane jScrollPane = new JScrollPane(productGridPanel);
        add(jScrollPane, BorderLayout.CENTER);
        this.bottomBar = new BottomBar("Προσθήκη προϊόντος",this);
        add(bottomBar,BorderLayout.SOUTH);
    }
    public ProductGridPanel getProductGridPanel(){
        return productGridPanel;
    }
//    private JPanel createTopBar() {
//
////        JPanel topBarPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
////
////        // Search label and field
////        JLabel searchLabel = new JLabel("Αναζήτηση προϊόντος: ");
////        searchField = new JTextField(20); // TextField for search (20 characters wide)
////
////        // Search button
//////        searchButton = createButton("Αναζήτηση",e->handleSearchProducts(searchField.getText()), new Dimension(100,25));
////        searchButton = new Button("Αναζήτηση",e->handleSearchProducts(searchField.getText()), new Dimension(100,25));
////
////        filterDropdown = new JComboBox<>(new String[]{"Όλα τα προϊόντα", "Μη διαθέσιμα", "Πιο δημοφιλή"});
////        filterDropdown.addActionListener(e->handleFilterSelection((String) filterDropdown.getSelectedItem()));
////
////        // Add search components to the top bar panel
////        topBarPanel.add(searchLabel);
////        topBarPanel.add(searchField);
////        topBarPanel.add(searchButton);
////        topBarPanel.add(filterDropdown);
////
////        return topBarPanel;
//        return topBar;
//    }
    private void addBottomBar() {
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
//        JButton addProductButton = createButton("Προσθήκη προϊόντος",e-> parentFrame.switchPanel("ProductRegistration") ,new Dimension(160, 40));
        JButton addProductButton = new Button("Προσθήκη προϊόντος",e-> parentFrame.switchPanel("ProductRegistration") ,new Dimension(160, 40));
        bottomPanel.add(addProductButton);
        bottomPanel.setBackground(Color.LIGHT_GRAY);
        add(bottomPanel, BorderLayout.SOUTH);
    }

////    private JPanel createProductGridPanel() {
////        JPanel gridPanel = new JPanel(new GridLayout(0, 3, 10, 10));
////        for (Product product : products) {
////            ProductRowPanel productRowPanel = new ProdshouuctRowPanel(product);
////            gridPanel.add(productRowPanel);
////            addActionButtons(productRowPanel, product);
////        }
////        return gridPanel;
//    }
//    public void updateProductGrid(List<Product> filteredProducts) {
//        gridPanel.removeAll();
//        if (filteredProducts == null || filteredProducts.isEmpty()) {
//            gridPanel.setLayout(new GridBagLayout());
//            noProductsLabel.setFont(new Font("Arial", Font.BOLD, 14));
//            noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
//            gridPanel.add(noProductsLabel);
//        } else {
//            gridPanel.setLayout(new GridLayout(0, 3, 10, 10));
//            for (Product product : filteredProducts) {
//                ProductRowPanel productRowPanel = new ProductRowPanel(product);
//                gridPanel.add(productRowPanel);
//                addActionButtons(productRowPanel, product);
//            }
//        }
//        gridPanel.revalidate();
//        gridPanel.repaint();
//    }

//    // Helper method to add action buttons to each product row
//    private void addActionButtons(JPanel productRowPanel,Product product) {
//        JButton removeButton = createButton("Αφαίρεση", e -> handleRemoveProduct(product),new Dimension(110, 30));
//        JButton editButton = createButton("Επεξεργασία", e -> handleEditProduct(product),new Dimension(110, 30));
//
//
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//
//        removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//        editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//
//
//        buttonPanel.add(removeButton);
//        buttonPanel.add(editButton);
//        buttonPanel.setBackground(Color.white);
//
//       productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
//    }
//
//    private static JButton createButton(String text, ActionListener actionListener, Dimension dimension) {
//        JButton button = new JButton(text);
//        button.addActionListener(actionListener);
//        button.setPreferredSize(dimension);
//        return button;
//    }

    public void handleFilterSelection(String selectedFilter){
        switch (selectedFilter) {
            case "Όλα τα προϊόντα":
//                updateProductGrid(productManager.getProducts());
                productGridPanel.updateProductGridPanel(productManager.getProducts());
                break;
            case "Μη διαθέσιμα":
//                updateProductGrid(productManager.getUnavailableProducts());
                productGridPanel.updateProductGridPanel(productManager.getUnavailableProducts());
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
        productGridPanel.updateProductGridPanel(filteredProducts);
    }

    public void handleRemoveProduct(Product product) {
        System.out.println("Product removed: " + product.getName());
        productManager.removeProduct(product);
        productGridPanel.updateProductGridPanel(productManager.getProducts());
//        updateProductGrid(productManager.getProducts());
    }

    private void handleEditProduct(Product product) {
        System.out.println("Editing product: " + product.getName());
        parentFrame.switchToEditProductPanel(product);
    }


    @Override
    public void onSearchButtonClick(String searchText) {
        String selectedFilter = (String) topBar.getFilterDropdown().getSelectedItem();
        List<Product> filteredProducts = productManager.filterAndSearchProducts(selectedFilter, searchText);
//        updateProductGrid(filteredProducts);
        productGridPanel.updateProductGridPanel(filteredProducts);
    }

    @Override
    public void onFilterButtonClick(String filter) {
        switch (filter) {
            case "Όλα τα προϊόντα":
//                updateProductGrid(productManager.getProducts());
                productGridPanel.updateProductGridPanel(productManager.getProducts());
                break;
            case "Μη διαθέσιμα":
//                updateProductGrid(productManager.getUnavailableProducts());
                productGridPanel.updateProductGridPanel(productManager.getUnavailableProducts());
                break;
            case "Πιο δημοφιλή":
//                updateProductGrid(productManager.getMostPopularProducts());
                break;
            default:
                break;
        }
    }

    @Override
    public void goToCartPanel() {

    }
}
