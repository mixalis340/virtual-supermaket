package gui;

import api.UserManager;

import javax.swing.*;

public class LoginPanel extends JPanel {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private MainFrame mainFrame;

    public LoginPanel(UserManager userManager, MainFrame mainFrame){
        this.userManager = userManager;
        this.mainFrame = mainFrame;



    }
}
