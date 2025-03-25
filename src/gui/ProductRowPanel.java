package gui;

import models.Product;

import javax.swing.*;
import java.awt.*;

public class ProductRowPanel extends JPanel {
        private Product product;

        public ProductRowPanel(Product product) {
            this.product = product;
            setBorder(BorderFactory.createLineBorder(Color.GRAY, 1));
            setBackground(Color.WHITE);
            setLayout(new BorderLayout());

            JLabel titleLabel = new JLabel(product.getName());
            titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
            titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
            add(titleLabel, BorderLayout.NORTH);

            JPanel detailsPanel = new JPanel();
            detailsPanel.setLayout(new BoxLayout(detailsPanel, BoxLayout.Y_AXIS));
            detailsPanel.setOpaque(false);
            detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Padding

            detailsPanel.add(createProductLabel("Περιγραφή: " + product.getDescription()));
            detailsPanel.add(createProductLabel("Κατηγορία: " + product.getCategory().getTitle()));
            detailsPanel.add(createProductLabel("Υποκατηγορία: " + product.getSubcategory()));
            detailsPanel.add(createProductLabel("Τιμή: " + product.getPrice() + "€"));
            detailsPanel.add(createProductLabel("Ποσότητα: " + product.getQuantity() + " " + product.getUnit()));


            add(detailsPanel,BorderLayout.CENTER);
        }
        public Product getProduct(){
            return product;
        }
        private JLabel createProductLabel(String text) {
            JLabel label = new JLabel(text);
            label.setHorizontalAlignment(SwingConstants.CENTER);
            label.setAlignmentX(Component.CENTER_ALIGNMENT);
            label.setFont(new Font("Roboto", Font.PLAIN, 14));
            return label;
        }
}
