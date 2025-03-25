package gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Button extends JButton {
    public Button(String text, ActionListener actionListener, Dimension dimension){
        this.setText(text);
        this.addActionListener(actionListener);
        this.setPreferredSize(dimension);
        this.setFocusPainted(false);
    }
}
