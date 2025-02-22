package gui;
import api.UserManager;
import models.User;

import javax.swing.*;
import java.awt.*;

public class LoginPanel extends JPanel {
    private UserManager userManager;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private MainFrame mainFrame;
    private JLabel statusLabel;

    public LoginPanel(UserManager userManager, MainFrame mainFrame){
        this.userManager = userManager;
        this.mainFrame = mainFrame;

        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;


        usernameField = new JTextField();
        addLabeledField("Username:", usernameField, 0, gbc);


        passwordField = new JPasswordField();
        addLabeledField("Password:", passwordField, 1, gbc);

        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        add(loginButton, gbc);


        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        statusLabel = new JLabel("", SwingConstants.CENTER);
        statusLabel.setForeground(Color.RED);
        add(statusLabel, gbc);

        loginButton.addActionListener(e -> handleLogin());
    }

    private void addLabeledField(String labelText, JComponent field, int row, GridBagConstraints gbc) {
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel(labelText), gbc);


        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        field.setPreferredSize(new Dimension(200, 30));
        add(field, gbc);
    }

    private void handleLogin(){
        String username = usernameField.getText();
        String password = new String(passwordField.getPassword());

        User user;
        user = userManager.login(username, password);
        if (user == null){
            statusLabel.setText("Invalid username or password.");
            statusLabel.setForeground(Color.RED);
        } else{
            statusLabel.setText("");
            mainFrame.connectedUser(user,statusLabel);
        }
    }
}
