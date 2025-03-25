package gui;
import api.CategoryManager;
import api.ProductManager;
import models.Category;
import models.Product;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public abstract class ProductFormPanel extends JPanel {
    private final MainFrame parentFrame;
    protected JTextField nameField, categoryField, priceField, quantityField, typeField,description;
    protected JComboBox<String> categoryComboBox, subCategoryComboBox;
    private JButton saveButton, backButton;
    protected JLabel statusLabel, titleLabel;
    protected CategoryManager categoryManager;
    protected ProductManager productManager;
    protected AdminPanel adminPanel;

    public ProductFormPanel(MainFrame parentFrame, CategoryManager categoryManager, ProductManager productManager, AdminPanel adminPanel){
        this.parentFrame = parentFrame; // Store reference to MainFrame
        this.categoryManager = categoryManager;
        this.productManager = productManager;
        this.adminPanel = adminPanel;
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
    protected abstract JTextField getDescriptionField();
    protected abstract String getSaveButtonText();
    protected abstract JTextField getNameField();
    protected abstract JTextField getPriceField();
    protected abstract JTextField getQuantityField();
    protected abstract String performSave(String name, String description, Category category, String subcategory, double price, int quantity);

    private void addFormFields() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        nameField = getNameField();
        description = getDescriptionField();
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
        addLabeledField("Περιγραφή:", description, 1, gbc, formPanel);
        addLabeledField("Κατηγορία:", categoryComboBox, 2, gbc, formPanel);
        addLabeledField("Υπό-Κατηγορία",subCategoryComboBox,3,gbc,formPanel);
        addLabeledField("Τιμή:", priceField, 4, gbc, formPanel);
        addLabeledField("Ποσότητα:", quantityField, 5, gbc, formPanel);
        addLabeledField("Μονάδα μέτρησης:",typeField,6,gbc,formPanel);

        saveButton = new JButton(getSaveButtonText());
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);

        gbc.gridx = 1;
        gbc.gridy = 7;
        formPanel.add(saveButton, gbc);

        gbc.gridy = 8;
        gbc.gridwidth = 2;
        formPanel.add(statusLabel, gbc);

        add(formPanel, BorderLayout.CENTER);
    }

    private void addListeners() {
        saveButton.addActionListener(e -> handleSave());
        categoryComboBox.addActionListener(e -> loadSubCategories());
        subCategoryComboBox.addActionListener(e -> displaySubcategoryType());
    }

    public void clearForm() {
        nameField.setText("");
        description.setText("");
        priceField.setText("");
        quantityField.setText("");
        statusLabel.setText("");
    }

    private void addLabeledField(String labelText, JComponent field, int row, GridBagConstraints gbc, JPanel formPanel) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        formPanel.add(new JLabel(labelText), gbc);

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

    protected String validateInputs(String name, String descriptionText, String priceText, String quantityText) {
        if (name.isEmpty() || descriptionText.isEmpty() || priceText.isEmpty() || quantityText.isEmpty()) {
            return "Παρακαλώ συμπληρώστε όλα τα πεδία.";
        }
        try {
            double price = Double.parseDouble(priceText.trim());
            int quantity = Integer.parseInt(quantityText.trim());
            if (price < 0) {
                return "Η τιμή δεν μπορεί να είναι αρνητική.";
            }
            if (quantity < 0) {
                return "Η ποσότητα δεν μπορεί να είναι αρνητική.";
            }
        } catch (NumberFormatException e) {
            return "Παρακαλώ εισάγετε έγκυρη τιμή και ποσότητα.";
        }
        return "Όλα τα πεδία σωστά.";
    }
    protected void handleSave() {
        String name = nameField.getText().trim();
        String descriptionText = description.getText().trim();
        String priceText = priceField.getText().trim();
        String quantityText = quantityField.getText().trim();

        String resultValidate = validateInputs(name, descriptionText, priceText, quantityText);
        if (!resultValidate.equals("Όλα τα πεδία σωστά.")) {
            statusLabel.setText(resultValidate);
            statusLabel.setForeground(Color.RED);
            return;
        }
        Category currentCategory = categoryManager.getCategoryByTitle((String) categoryComboBox.getSelectedItem());
        String subcategory = (String) subCategoryComboBox.getSelectedItem();
        double price = Double.parseDouble(priceText);
        int quantity = Integer.parseInt(quantityText);

        // Call the specific method in the subclass for add or edit
        String resultMessage = performSave(name, descriptionText, currentCategory, subcategory, price, quantity);

        // Update UI based on the result message
        if (resultMessage.equals("Το προϊόν αποθηκεύτηκε επιτυχώς!") || resultMessage.equals("Το προϊόν επεξεργάστηκε επιτυχώς!")) {
            clearForm();
            statusLabel.setText(resultMessage);
//            adminPanel.updateProductGrid(productManager.getProducts());
            adminPanel.getProductGridPanel().updateProductGridPanel(productManager.getProducts());
            statusLabel.setForeground(Color.GREEN);
        } else {
            statusLabel.setText(resultMessage);
            statusLabel.setForeground(Color.RED);
        }
    }
}
