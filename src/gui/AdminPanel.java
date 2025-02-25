package gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
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

    public AdminPanel(MainFrame parentFrame,List<Product> products) {
        this.parentFrame = parentFrame;
        this.products = products;
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
        searchButton = createButton("Αναζήτηση",null, new Dimension(100,25));

        filterDropdown = new JComboBox<>(new String[]{"Όλα τα προϊόντα", "Μη διαθέσιμα", "Πιο δημοφιλή"});
        filterDropdown.addActionListener(null);

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
    private void updateProductGrid(List<Product> filteredProducts) {
        gridPanel.removeAll();
        for (Product product : filteredProducts) {
            ProductRowPanel productRowPanel = new ProductRowPanel(product);
            gridPanel.add(productRowPanel);
            addActionButtons(productRowPanel, product);
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


    private void handleRemoveProduct(Product product) {
        System.out.println("Product removed: " + product.getName());
    }

    private void handleEditProduct(Product product) {
        System.out.println("Editing product: " + product.getName());
        parentFrame.switchToEditProductPanel(product);
    }
}
