package gui;

import javax.swing.*;
import java.awt.*;
import api.UserManager;

public class MainFrame extends JFrame {
    private UserManager userManager;
    private LoginPanel loginPanel;
    private JMenuBar menuBar;

   public MainFrame(UserManager userManager){
       setTitle("Virtual-Supermarket");
       setSize(800, 600);
       setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
       loginPanel = new LoginPanel(userManager,this);
       menuBar = createMenuBar();

       setLayout(new BorderLayout());
       setJMenuBar(menuBar);
       add(loginPanel, BorderLayout.CENTER);
       setVisible(true);

   }

    private JMenuBar createMenuBar() {
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(e -> System.exit(0));
        menu.add(exitItem);
        menuBar.add(menu);
        return menuBar;
    }
}

