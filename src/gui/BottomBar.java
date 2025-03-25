package gui;

import interfaces.BottomBarListener;

import javax.swing.*;
import java.awt.*;

public class BottomBar extends JPanel {
    private JButton bottomBarButton;
    private BottomBarListener bottomBarListener;

    public BottomBar(String buttonLabel,BottomBarListener bottomBarListener) {
        this.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
        this.bottomBarListener = bottomBarListener;

        bottomBarButton = new Button(buttonLabel,null ,new Dimension(160, 40));
        bottomBarButton.addActionListener(e->goToCartPanel());
        bottomBarButton.setPreferredSize(new Dimension(160, 40));

        this.add(bottomBarButton);
        this.setBackground(Color.LIGHT_GRAY);
    }

    public void setButtonLabel(String buttonLabel) {
        bottomBarButton.setText(buttonLabel);
        revalidate();
        repaint();
    }

    public void goToCartPanel(){
        bottomBarListener.goToCartPanel();
    }
}
