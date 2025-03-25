package gui;

import api.ProductManager;
import interfaces.ButtonActionListener;
import interfaces.CartActionListener;
import models.Cart;
import models.OrderItem;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class ProductGridPanel extends JPanel {
    private ProductManager productManager;
    private boolean isAdmin;
    private  Cart cart;
    private JLabel noProductsLabel;
    private ButtonActionListener removeButtonListener;
    private ButtonActionListener editButtonListener;
    private ButtonActionListener addToCartButtonListener;
    private CartActionListener cartActionListener;

    public ProductGridPanel(ProductManager productManager, boolean isAdmin, Cart cart, ButtonActionListener removeButtonListener, ButtonActionListener editButtonListener, ButtonActionListener addToCartButtonListener, CartActionListener cartActionListener){
        this.noProductsLabel = new JLabel("Δεν υπάρχουν σχετικά προϊόντα");
        this.isAdmin = isAdmin;
        this.productManager = productManager;
        this.removeButtonListener = removeButtonListener;
        this.editButtonListener = editButtonListener;
        this.addToCartButtonListener = addToCartButtonListener;
        this.cartActionListener = cartActionListener;

        if (isAdmin) {
            initializeProductGrid(productManager.getProducts());
        } else {
            initializeOrderItemGrid(productManager.getProducts());
            this.cart = cart;
        }

    }

    public void initializeProductGrid(List<Product> products) {
        setLayout(new GridLayout(0, 3, 10, 10));
        for (Product product : products) {
            ProductRowPanel productRowPanel = new ProductRowPanel(product);
            add(productRowPanel);
            addAdminActionButtons(productRowPanel, product);
        }
    }
    public void initializeOrderItemGrid(List<Product> products){
        setLayout(new GridLayout(0, 3, 10, 10));
        for (Product product : products) {
            ProductRowPanel productRowPanel = new ProductRowPanel(product);
            add(productRowPanel);
            addCustomerActionButtons(productRowPanel,product);
        }
    }
    public void updateProductGridPanel(List<Product> filteredProducts) {
        this.removeAll();

        if (filteredProducts == null || filteredProducts.isEmpty()) {
            showNoProductsLabel();
        } else {
            this.setLayout(new GridLayout(0, 3, 10, 10));
            for (Product product : filteredProducts) {
                ProductRowPanel productRowPanel = new ProductRowPanel(product);
                this.add(productRowPanel);
                addAdminActionButtons(productRowPanel, product);
            }
        }
        this.revalidate();
        this.repaint();
    }

    public void addAdminActionButtons(JPanel productRowPanel, Product product){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton removeButton = new Button("Αφαίρεση", e -> removeButtonListener.onButtonClick(product), new Dimension(110, 30));
        JButton editButton = new Button("Επεξεργασία", e -> editButtonListener.onButtonClick(product), new Dimension(110, 30));

        removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
        editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);

        buttonPanel.add(removeButton);
        buttonPanel.add(editButton);
        buttonPanel.setBackground(Color.white);
        productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    public void addCustomerActionButtons(JPanel productRowPanel, Product product){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

        JButton addToCartButton = new JButton("+ Προσθήκη");
        addToCartButton.setFocusPainted(false);
        addToCartButton.setPreferredSize(new Dimension(140, 30));

        addToCartButton.addActionListener(e -> displayQuantityCounter(buttonPanel, product,addToCartButton));

        buttonPanel.add(addToCartButton);
        buttonPanel.setBackground(Color.white);
        productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
//    private void addActionButtons(JPanel productRowPanel, Product product) {
//        JPanel buttonPanel = new JPanel();
//        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
//        if (isAdmin) {
//            JButton removeButton = new Button("Αφαίρεση", e -> removeButtonListener.onButtonClick(product), new Dimension(110, 30));
//            JButton editButton = new Button("Επεξεργασία", e -> editButtonListener.onButtonClick(product), new Dimension(110, 30));
//
//            removeButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//            editButton.setAlignmentX(Component.RIGHT_ALIGNMENT);
//
//            buttonPanel.add(removeButton);
//            buttonPanel.add(editButton);
//        } else {
//            JButton addToCartButton = new JButton("+ Προσθήκη");
//            addToCartButton.setFocusPainted(false);
//            addToCartButton.setPreferredSize(new Dimension(140, 30));
//            addToCartButton.addActionListener(e -> displayQuantityCounter(buttonPanel, product,addToCartButton));
//            buttonPanel.add(addToCartButton);
//            }
//        buttonPanel.setBackground(Color.white);
//        productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
//    }

    public void displayQuantityCounter(JPanel buttonPanel,Product product,JButton addToCartButton){
        cartActionListener.addProductToCart(product);
        OrderItem orderItem = cart.findOrderItem(product);
        buttonPanel.removeAll();

        JButton plusButton = new Button("+",e-> cartActionListener.increaseProductQuantity(product),new Dimension(50, 30));
        JButton minusButton = new Button("-",e-> cartActionListener.decreaseProductQuantity(buttonPanel,product,addToCartButton,false),new Dimension(50, 30));

        buttonPanel.add(minusButton);
        buttonPanel.add(orderItem.getQuantityLabel());
        buttonPanel.add(plusButton);

        addToCartButton.setVisible(false);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    private void showNoProductsLabel() {
        this.setLayout(new GridBagLayout());
        noProductsLabel.setFont(new Font("Arial", Font.BOLD, 14));
        noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(noProductsLabel);
    }
    public void refreshUI(List<Product> selectedProducts) {
        if (selectedProducts==null)
            selectedProducts = productManager.getProducts();

        removeAll();

        if (selectedProducts.isEmpty())
            showNoProductsLabel();
        else{
            setLayout(new GridLayout(0, 3, 10, 10));
            for (Product product : selectedProducts) {
                ProductRowPanel productRowPanel = new ProductRowPanel(product);

                OrderItem orderItem = cart.findOrderItem(product);
                JButton addToCartButton = new JButton("+ Προσθήκη");
                if (orderItem != null) {
                    JLabel quantityLabel = orderItem.getQuantityLabel();
                    JPanel buttonPanel = new JPanel();
                    buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));

                    JButton plusButton = new Button("+", e -> cartActionListener.increaseProductQuantity(product), new Dimension(50, 30));
                    JButton minusButton = new Button("-",e-> cartActionListener.decreaseProductQuantity(buttonPanel,product,addToCartButton,false),new Dimension(50, 30));

                    buttonPanel.add(minusButton);
                    buttonPanel.add(quantityLabel);
                    buttonPanel.add(plusButton);
                    buttonPanel.setBackground(Color.white);
                    productRowPanel.add(buttonPanel, BorderLayout.SOUTH);
                } else {
                    addCustomerActionButtons(productRowPanel, product);
                }

                add(productRowPanel);
            }
        }
        revalidate();
        repaint();
    }

}

