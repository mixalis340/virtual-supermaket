package gui;
import api.CategoryManager;
import models.Product;
import javax.swing.*;

public class ProductEditPanel extends ProductFormPanel {
    private Product product;

    public ProductEditPanel(MainFrame parentFrame, Product product, CategoryManager categoryManager) {
        super(parentFrame, categoryManager);
        this.product = product;
    }


    public void setTitleLabel(String title){
       titleLabel.setText(title);
    }
    @Override
    protected String getTitleLabel() {
        return "Επεξεργασία προϊόντος: \"" + (product != null ? product.getName() : "Νέο προϊόν") + "\"";
    }

    @Override
    protected String getSaveButtonText() {
        return "Αποθήκευση";
    }

    @Override
    protected JTextField getNameField() {
        if (nameField == null) {
            nameField = new JTextField(product != null ? product.getName() : "");
        }
        return nameField;
    }

    @Override
    protected JTextField getPriceField() {
        if (priceField == null) {
            priceField = new JTextField(product != null ? String.valueOf(product.getPrice()) : "");
        }
        return priceField;
    }

    @Override
    protected JTextField getQuantityField() {
        if (quantityField == null) { // Only initialize once
            quantityField = new JTextField(product != null ? String.valueOf(product.getQuantity()) : "");
        }
        return quantityField;
    }


    private void setSelectedCategory() {
        if (product != null && product.getCategory() != null) {
            String productCategory = product.getCategory().getTitle();
            for (int i = 0; i < categoryComboBox.getItemCount(); i++) {
                if (categoryComboBox.getItemAt(i).equals(productCategory)) {
                    categoryComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    private void setSelectedSubCategory() {
        if (product != null && product.getCategory() != null) {
            String productSubCategory = product.getSubcategory();
            for (int i = 0; i < subCategoryComboBox.getItemCount(); i++) {
                if (subCategoryComboBox.getItemAt(i).equals(productSubCategory)) {
                    subCategoryComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }

    //Update product data when switching to the panel
    public void updateProductData(Product product) {
        this.product = product;
        setTitleLabel(getTitleLabel());
        getNameField().setText(product != null ? product.getName() : "");
        getPriceField().setText(product != null ? String.valueOf(product.getPrice()) : "");
        getQuantityField().setText(product != null ? String.valueOf(product.getQuantity()) : "");
        setSelectedCategory();
        setSelectedSubCategory();
    }
}
