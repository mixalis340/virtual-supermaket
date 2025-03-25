package gui;

import interfaces.TopBarListener;

import javax.swing.*;
import java.awt.*;

public class TopBar extends JPanel {
    private JLabel searchLabel;
    private JTextField searchField;
    private Button searchButton;
    private JComboBox<String> filterDropdown;

    public TopBar(FlowLayout flowLayout, TopBarListener searchButtonListener, TopBarListener filterButtonListener){
        this.setLayout(flowLayout);
        this.searchLabel = new JLabel("Αναζήτηση προϊόντος: ");
        this.searchField = new JTextField(20);
        this.searchButton = new Button("Αναζήτηση",e->searchButtonListener.onSearchButtonClick(searchField.getText()), new Dimension(100,25));
        this.filterDropdown = new JComboBox<>(new String[]{"Όλα τα προϊόντα", "Μη διαθέσιμα", "Πιο δημοφιλή"});
        filterDropdown.addActionListener(e->filterButtonListener.onFilterButtonClick(((String) filterDropdown.getSelectedItem())));

        this.add(searchLabel);
        this.add(searchField);
        this.add(searchButton);
        this.add(filterDropdown);
    }
    public JComboBox<String> getFilterDropdown(){
        return filterDropdown;
    }
}
