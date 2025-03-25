package gui;

import javax.swing.*;
import java.awt.*;
import java.util.List;

import api.CategoryManager;
import api.ProductManager;
import api.UserManager;
import interfaces.CartActionListener;
import models.Cart;
import models.Category;
import models.Product;
import models.User;

public class MainFrame extends JFrame {
    private UserManager userManager;
    private LoginPanel loginPanel;
    private JMenuBar menuBar;
    private CardLayout cardLayout;
    private AdminPanel adminPanel;
    private CustomerPanel customerPanel;
    private JPanel contentPanel;
    private ProductRegistrationPanel productRegistrationPanel;
    private ProductEditPanel productEditPanel;
    private CartPanel cartPanel;
    private ProductManager productManager;
    private JMenuItem backItem;
    private CategoryManager categoryManager;

    public String getCurrentPanelName() {
        return currentPanelName;
    }

    public String currentPanelName;

    public MainFrame(UserManager userManager, List<Product> products, CategoryManager categoryManager,ProductManager productManager){
        setTitle("Virtual-Supermarket");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        this.cardLayout = new CardLayout();
        this.contentPanel = new JPanel(cardLayout);
        this.backItem = new JMenuItem("Back");
        this.productManager = productManager;
        this.categoryManager = categoryManager;

        adminPanel = new AdminPanel(this, productManager); // Pass MainFrame to allow screen switching
        loginPanel = new LoginPanel(userManager, this);
        productRegistrationPanel = new ProductRegistrationPanel(this, categoryManager, productManager,adminPanel);
        productEditPanel = new ProductEditPanel(this, null, categoryManager,productManager,adminPanel);
        cartPanel = new CartPanel(new Cart(),null);
        customerPanel = new CustomerPanel(productManager,this,cartPanel);
        // Add all panels to CardLayout
        contentPanel.add(adminPanel, "Admin");
        contentPanel.add(customerPanel,"Customer");
        contentPanel.add(loginPanel, "Login");
        contentPanel.add(productEditPanel, "ProductEdit");
        contentPanel.add(productRegistrationPanel,"ProductRegistration");
        contentPanel.add(cartPanel,"Cart");

        menuBar = createMenuBar();
        setLayout(new BorderLayout());
        setJMenuBar(menuBar);
        add(contentPanel, BorderLayout.CENTER);

        setLocationRelativeTo(null);

        switchPanel("Customer");
        setVisible(true);
    }
    public JMenuItem getBackItem(){
        return backItem;
    }
    public void switchPanel(String panelName) {
        this.currentPanelName = panelName;
        cardLayout.show(contentPanel, panelName);
        backItem.setVisible(!"Login".equals(panelName));
        System.out.println("Switching to "+panelName);
    }

    public void switchToCartPanel(CartActionListener cartActionListener){
        this.currentPanelName = "Cart";
        cartPanel.setCartActionListener(cartActionListener);
        cardLayout.show(contentPanel,"Cart");
    }
    public void switchToEditProductPanel(Product product){
        this.currentPanelName = "ProductEdit";
        productEditPanel.updateProductData(product);
        productEditPanel.revalidate();
        productEditPanel.repaint();
        cardLayout.show(contentPanel, "ProductEdit");
        System.out.println("Product name: " + productEditPanel.getNameField().getText());
    }

    public void connectedUser(User user, JLabel jLabel) {

    }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);

        backItem = new JMenuItem("Back");
        backItem.addActionListener(e -> handleBackNavigation());
        menu.add(backItem);

        JMenuItem backToLogin = new JMenuItem("Back to Login");
        backToLogin.addActionListener(e -> switchPanel("Login"));
        menu.add(backToLogin);

        menuBar.add(menu);
        return menuBar;
    }

    private void handleBackNavigation() {
        System.out.println("I am in "+currentPanelName);
        switch (currentPanelName) {
            case "Cart":
                switchPanel("Customer");
                customerPanel.getProductGridPanel().refreshUI(null);
                break;
            case "ProductEdit", "ProductRegistration":
                switchPanel("Admin");
                break;
            default:
                switchPanel("Login");
                break;
        }
    }
}
