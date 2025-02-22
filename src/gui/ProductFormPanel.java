package gui;

import javax.swing.*;
import java.awt.*;

public abstract class ProductFormPanel extends JPanel {
    private final MainFrame parentFrame;
    protected JTextField nameField, categoryField, priceField, quantityField;
    private JButton saveButton, backButton;
    protected JLabel statusLabel, titleLabel;

    public ProductFormPanel(MainFrame parentFrame){
        this.parentFrame = parentFrame; // Store reference to MainFrame
        setLayout(new BorderLayout());

        addTitle();
        addFormFields();
        addListeners();
    }

    private void addTitle() {
        titleLabel = new JLabel(getTitleLabel(), SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setPreferredSize(new Dimension(400, 40));
        add(titleLabel, BorderLayout.NORTH);
        System.out.println("Title is: " + titleLabel.getText());
    }
    protected abstract void setTitleLabel(String title);
    protected abstract String getTitleLabel();
    protected abstract String getSaveButtonText();
    protected abstract JTextField getNameField();
    protected abstract JTextField getCategoryField();
    protected abstract JTextField getPriceField();
    protected abstract JTextField getQuantityField();

    private void addFormFields() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = getNameField();
        categoryField = getCategoryField();
        priceField = getPriceField();
        quantityField = getQuantityField();

        addLabeledField("Όνομα προϊόντος:", nameField, 0, gbc, formPanel);
        addLabeledField("Κατηγορία:", categoryField, 1, gbc, formPanel);
        addLabeledField("Τιμή:", priceField, 2, gbc, formPanel);
        addLabeledField("Ποσότητα:", quantityField, 3, gbc, formPanel);

        saveButton = new JButton(getSaveButtonText());
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 1;
        gbc.gridy = 4;
        formPanel.add(saveButton, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        saveButton.addActionListener(e -> handleSave());
        parentFrame.getBackItem().addActionListener(e-> {clearForm(); parentFrame.switchPanel("Admin");});
    }

    public void clearForm() {
        nameField.setText("");
        categoryField.setText("");
        priceField.setText("");
        quantityField.setText("");
        statusLabel.setText("");
    }

    private void addLabeledField(String labelText, JComponent field, int row, GridBagConstraints gbc, JPanel formPanel) {
        // Add label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel(labelText), gbc);

        // Add field
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new Dimension(200, 30));
        formPanel.add(field, gbc);
    }

    private void handleSave() {
        try {
            String name = nameField.getText();
            String category = categoryField.getText();
            double price = Double.parseDouble(priceField.getText());
            int quantity = Integer.parseInt(quantityField.getText());

            statusLabel.setText("");
            statusLabel.setForeground(Color.GREEN);

            System.out.println("Product saved: " + name + ", Category: " + category + ", Price: " + price + ", Quantity: " + quantity);
            statusLabel.setText("Το προϊόν αποθηκεύτηκε επιτυχώς!");
        } catch (NumberFormatException e) {
            statusLabel.setText("Παρακαλώ εισάγετε έγκυρη τιμή και ποσότητα.");
            statusLabel.setForeground(Color.RED);
        }
    }
}
