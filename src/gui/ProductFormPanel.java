package gui;
import api.CategoryManager;
import models.Category;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ProductFormPanel extends JPanel {
    private final MainFrame parentFrame;
    protected JTextField nameField, categoryField, priceField, quantityField, typeField;
    protected JComboBox<String> categoryComboBox, subCategoryComboBox;
    private JButton saveButton, backButton;
    protected JLabel statusLabel, titleLabel;
    protected CategoryManager categoryManager;

    public ProductFormPanel(MainFrame parentFrame, CategoryManager categoryManager){
        this.parentFrame = parentFrame; // Store reference to MainFrame
        this.categoryManager = categoryManager;
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
    protected abstract String getTitleLabel();
    protected abstract String getSaveButtonText();
    protected abstract JTextField getNameField();
    protected abstract JTextField getPriceField();
    protected abstract JTextField getQuantityField();

    private void addFormFields() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = getNameField();
        priceField = getPriceField();
        quantityField = getQuantityField();
        typeField = new JTextField();
        typeField.setEditable(false);

        categoryComboBox = new JComboBox<>();
        subCategoryComboBox = new JComboBox<>();
        loadCategories();
        loadSubCategories();
        displaySubcategoryType();

        addLabeledField("Όνομα προϊόντος:", nameField, 0, gbc, formPanel);
        addLabeledField("Κατηγορία:", categoryComboBox, 1, gbc, formPanel);
        addLabeledField("Υπό-Κατηγορία",subCategoryComboBox,2,gbc,formPanel);
        addLabeledField("Τιμή:", priceField, 3, gbc, formPanel);
        addLabeledField("Ποσότητα:", quantityField, 4, gbc, formPanel);
        addLabeledField("Μονάδα μέτρησης:",typeField,5,gbc,formPanel);

        saveButton = new JButton(getSaveButtonText());
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 1;
        gbc.gridy = 6;
        formPanel.add(saveButton, gbc);

        gbc.gridy = 5;
        gbc.gridwidth = 2;
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        saveButton.addActionListener(e -> handleSave());
        parentFrame.getBackItem().addActionListener(e-> {clearForm(); parentFrame.switchPanel("Admin");});
        categoryComboBox.addActionListener(e -> loadSubCategories());
        subCategoryComboBox.addActionListener(e -> displaySubcategoryType());
    }

    public void clearForm() {
        nameField.setText("");
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

    private void loadCategories() {
        categoryComboBox.removeAllItems();
        List<Category> categories = categoryManager.getCategories();
        for (Category category : categories) {
            categoryComboBox.addItem(category.getTitle());
        }
    }

    private void loadSubCategories(){
        subCategoryComboBox.removeAllItems();
        Category currentCategory = categoryManager.getCategoryByTitle((String) categoryComboBox.getSelectedItem());
        System.out.println("Current category: "+currentCategory.getTitle());
        for (String subCategory: currentCategory.getSubcategories()){
            subCategoryComboBox.addItem(subCategory);
        }
    }

    public void displaySubcategoryType(){
        Category currentCategory = categoryManager.getCategoryByTitle((String) categoryComboBox.getSelectedItem());
        String currentSubcategory = (String) subCategoryComboBox.getSelectedItem();
        String subCategoryType = currentCategory.getSubcategoryType(currentSubcategory);
        typeField.setText(subCategoryType);
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
