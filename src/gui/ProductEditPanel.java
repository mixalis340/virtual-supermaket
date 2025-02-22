package gui;
import models.Product;
import javax.swing.*;

public class ProductEditPanel extends ProductFormPanel {
    private Product product;

    public ProductEditPanel(MainFrame parentFrame, Product product) {
        super(parentFrame);
        this.product = product;
    }

    @Override
    protected void setTitleLabel(String title){
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
    protected JTextField getCategoryField() {
        if (categoryField == null) {
            categoryField = new JTextField(String.valueOf(product != null ? product.getCategory() : ""));
        }
        return categoryField;
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

    //Update product data when switching to the panel
    public void updateProductData(Product product) {
        this.product = product;
        setTitleLabel(getTitleLabel());
        getNameField().setText(product != null ? product.getName() : "");
        getCategoryField().setText(product != null ? String.valueOf(product.getCategory().getTitle()) : "");
        getPriceField().setText(product != null ? String.valueOf(product.getPrice()) : "");
        getQuantityField().setText(product != null ? String.valueOf(product.getQuantity()) : "");
    }
}
