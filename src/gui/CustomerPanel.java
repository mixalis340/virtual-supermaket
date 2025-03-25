package gui;

import api.ProductManager;
import interfaces.BottomBarListener;
import interfaces.CartActionListener;
import interfaces.TopBarListener;
import models.Cart;
import models.Product;
import javax.swing.*;
import java.awt.*;
import java.util.List;
import java.util.Objects;

public class CustomerPanel extends JPanel implements CartActionListener, BottomBarListener, TopBarListener {
    private ProductGridPanel productGridPanel;
    private ProductManager productManager;
    private MainFrame parentFrame;
    private Cart cart;
    private TopBar topBar;
    private BottomBar bottomBar;
    private CartPanel cartPanel;

    public CustomerPanel(ProductManager productManager,MainFrame parentFrame,CartPanel cartPanel){
        setLayout(new BorderLayout());
        this.cart = new Cart();
        this.cartPanel = cartPanel;
        this.parentFrame = parentFrame;
        this.productManager = productManager;
        this.topBar = new TopBar(new FlowLayout(FlowLayout.LEFT, 10, 10),this,this);
        this.productGridPanel = new ProductGridPanel(productManager,false,cart,null,null,null,this);
        JScrollPane jScrollPane = new JScrollPane(productGridPanel);
        add(jScrollPane, BorderLayout.CENTER);
        add(topBar, BorderLayout.NORTH);
        this.bottomBar = new BottomBar(
                String.format("Καλάθι αξίας: €%.2f", cart.getTotalCartValue()),
                this
        );
        add(this.bottomBar,BorderLayout.SOUTH);
    }

    @Override
    public void increaseProductQuantity(Product product) {
        cart.increaseProductQuantity(product);
        cart.findOrderItem(product).setQuantityLabel(cart.findOrderItem(product).getQuantity());
        updateBottomBar();
        System.out.println(String.format("Καλάθι αξίας: €%.2f", cart.getTotalCartValue()));
    }

    @Override
    public void addProductToCart(Product product) {
        cart.addProductToCart(product);
        cart.findOrderItem(product).setQuantityLabel(1);
        updateBottomBar();
    }

    @Override
    public void decreaseProductQuantity(JPanel buttonPanel, Product product, JButton addToCartButton, boolean isFromCart) {
        String message = cart.decreaseQuantity(product);
        if (!isFromCart){
            if (Objects.equals(message, "product removed")){
                handleQuantityZero(buttonPanel, product, addToCartButton);
            } else {
                cart.findOrderItem(product).setQuantityLabel(cart.findOrderItem(product).getQuantity());
            }
        }
        else {
            if (Objects.equals(message, "product removed")){
               removeProduct(product.getId());
            }
            else {
                cart.findOrderItem(product).setQuantityLabel(cart.findOrderItem(product).getQuantity());
                cartPanel.updateCartPanel(cart);
            }
        }
        updateBottomBar();
    }

    @Override
    public void handleQuantityZero(JPanel buttonPanel, Product product, JButton addToCartButton) {
        buttonPanel.removeAll();
        addToCartButton.setVisible(true);
        buttonPanel.add(addToCartButton);
        buttonPanel.revalidate();
        buttonPanel.repaint();
    }

    @Override
    public void removeProduct(int productId) {
        cart.removeItem(productId);
        cartPanel.updateCartPanel(cart);
    }


    @Override
    public void goToCartPanel() {
        cartPanel.updateCartPanel(cart);
        parentFrame.switchToCartPanel(this);
    }

    private void updateBottomBar() {
        bottomBar.setButtonLabel(String.format("Καλάθι αξίας: €%.2f", cart.getTotalCartValue()));
    }

    public ProductGridPanel getProductGridPanel(){
        return productGridPanel;
    }

    /// Below are the topbar actions (for simplicity for now, I assume they are the same with admin)
    @Override
    public void onSearchButtonClick(String searchText) {
        String selectedFilter = (String) topBar.getFilterDropdown().getSelectedItem();
        List<Product> filteredProducts = productManager.filterAndSearchProducts(selectedFilter, searchText);
        productGridPanel.refreshUI(filteredProducts);
    }

    @Override
    public void onFilterButtonClick(String filter) {
        switch (filter) {
            case "Όλα τα προϊόντα":
                productGridPanel.refreshUI(productManager.getProducts());
                break;
            case "Μη διαθέσιμα":
                productGridPanel.refreshUI(productManager.getUnavailableProducts());
                break;
            case "Πιο δημοφιλή":
//                updateProductGrid(productManager.getMostPopularProducts());
                break;
            default:
                break;
        }
    }
}
