package gui;

import api.ProductManager;
import interfaces.CartActionListener;
import models.Cart;
import models.OrderItem;

import javax.swing.*;
import java.awt.*;

public class CartPanel extends JPanel {
    private Cart cart;
    private JLabel noProductsLabel;
    private JLabel totalPriceLabel;
    private CartActionListener cartActionListener;

    public CartPanel(Cart cart, CartActionListener cartActionListener) {
        this.cart = cart;
        this.cartActionListener = cartActionListener;
        setLayout(new BorderLayout());
        buildCartPanelUI();
    }

    public void buildCartPanelUI(){
        removeAll();
        int numProducts = cart.getItems().size();
        if (cart.getItems().isEmpty()){
            this.noProductsLabel = new JLabel("Το καλάθι είναι άδειο.");
            noProductsLabel.setFont(new Font("Arial", Font.BOLD, 14));
            noProductsLabel.setHorizontalAlignment(SwingConstants.CENTER);
            this.add(noProductsLabel, BorderLayout.CENTER);
        } else{
        JPanel gridPanel = new JPanel(new GridLayout(numProducts, 1, 10, 10));

        for (OrderItem orderItem : cart.getItems()) {
///             Create a panel for each product using a GridLayout with 5 rows:
///             1: product name, 2: price, 3: quantity, 4: total, 5: button panel.
            JPanel productPanel = new JPanel(new GridLayout(5, 1, 5, 5));
            productPanel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));

            String productName = orderItem.getProduct().getName();
            double price = orderItem.getProduct().getPrice();
            int quantity = orderItem.getQuantity();
            double totalPrice = orderItem.getTotalPrice();

            JLabel nameLabel = new JLabel(productName);
            nameLabel.setFont(new Font("Arial", Font.BOLD, 14));
            JLabel priceLabel = new JLabel(String.format("Τιμή: €%.2f", price));
            JLabel totalLabel = new JLabel(String.format("Σύνολο: €%.2f", totalPrice));
            JPanel quantityPanel = createQuantityPanel(orderItem,totalLabel);

            // Create a button panel for actions.
            JPanel buttonPanel = createRemoveButtonPanel(orderItem.getProduct().getId());

            // Add all components to the product panel.
            productPanel.add(nameLabel);
            productPanel.add(priceLabel);
            productPanel.add(quantityPanel);
            productPanel.add(totalLabel);
            productPanel.add(buttonPanel);

            // Add this product cell to the grid panel.
            gridPanel.add(productPanel);
        }


        JScrollPane scrollPane = new JScrollPane(gridPanel);
        add(scrollPane, BorderLayout.CENTER);


        JPanel bottomPanel = createBottomPanel(cart.getTotalCartValue());
        add(bottomPanel, BorderLayout.SOUTH);


        JLabel label = createTopLabel();
        add(label, BorderLayout.NORTH);
        }
        revalidate();
        repaint();
    }

    public JPanel createQuantityPanel(OrderItem orderItem,JLabel totalLabel){
        JPanel quantityPanel = new JPanel();
        quantityPanel.setLayout(new BoxLayout(quantityPanel,BoxLayout.X_AXIS));

        JLabel quantityLabel = new JLabel("Ποσότητα: ");

        JLabel quantityValue = orderItem.getQuantityLabel();
        JButton minusButton = new JButton("-");
        JButton plusButton = new JButton("+");

        plusButton.addActionListener(e -> {
            cartActionListener.increaseProductQuantity(orderItem.getProduct());
            updateTotalPriceLabel(cart.getTotalCartValue());
            updateOrderItemPrice(totalLabel, orderItem.getTotalPrice());
        });

        minusButton.addActionListener(e->{
            cartActionListener.decreaseProductQuantity(null, orderItem.getProduct(), null, true);
            updateTotalPriceLabel(cart.getTotalCartValue());
            updateOrderItemPrice(totalLabel,orderItem.getTotalPrice());
        });
        minusButton.setFocusable(false);
        plusButton.setFocusable(false);

        quantityPanel.add(quantityLabel);
        quantityPanel.add(minusButton);
        quantityPanel.add(quantityValue);
        quantityPanel.add(plusButton);

        return quantityPanel;
    }

    public JPanel createBottomPanel(double totalCartPrice) {
        JPanel totalPanel = new JPanel(new BorderLayout());

        totalPriceLabel = new JLabel(String.format("Συνολικό Ποσό: €%.2f", totalCartPrice), SwingConstants.LEFT);
        totalPriceLabel.setFont(new Font("Arial", Font.BOLD, 16));
        totalPanel.add(totalPriceLabel, BorderLayout.CENTER);

        JButton finalizeButton = new JButton("Ολοκλήρωση Παραγγελίας");
        finalizeButton.setFont(new Font("Arial", Font.BOLD, 14));
        finalizeButton.setBackground(new Color(46, 204, 130));
        finalizeButton.setForeground(Color.WHITE);
        finalizeButton.setFocusPainted(false);
        finalizeButton.setCursor(new Cursor(Cursor.HAND_CURSOR));

        totalPanel.add(finalizeButton, BorderLayout.SOUTH);
        return totalPanel;
    }

    public JPanel createRemoveButtonPanel(int productId){
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel,BoxLayout.X_AXIS));
        JButton removeButton = new JButton("Αφαίρεση");
        removeButton.addActionListener(e-> cartActionListener.removeProduct(productId));
        removeButton.setFont(new Font("Arial", Font.BOLD, 12));
        buttonPanel.add(removeButton);
        return buttonPanel;
    }

    public JLabel createTopLabel(){
        JLabel label = new JLabel("Καλάθι", SwingConstants.CENTER);
        label.setFont(new Font("Arial", Font.BOLD, 18));
        label.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        return label;
    }

    public void updateCartPanel(Cart newCart){
        this.cart = newCart;
        buildCartPanelUI();
    }

    public void setCartActionListener(CartActionListener cartActionListener){
        this.cartActionListener = cartActionListener;
    }
    public void updateTotalPriceLabel(double totalCartPrice) {
        totalPriceLabel.setText(String.format("Συνολικό Ποσό: €%.2f", totalCartPrice));
        revalidate();
        repaint();
    }
    public void updateOrderItemPrice(JLabel orderItemPriceLabel,double orderItemPrice){
        orderItemPriceLabel.setText(String.format("Σύνολο: €%.2f", orderItemPrice));
        revalidate();
        repaint();
    }
}
