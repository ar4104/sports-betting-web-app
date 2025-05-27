package com.labpatterns2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainSwingApp {

    private JFrame frame;
    private DefaultListModel<String> listModel;
    private JList<String> itemList;

    public MainSwingApp() {
        initialize();
    }

    private void initialize() {
        frame = new JFrame("LabPatterns Swing UI");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        frame.setLayout(new BorderLayout());

        // Panel with buttons
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add Item");
        JButton removeButton = new JButton("Remove Selected");
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);

        // List model and JList
        listModel = new DefaultListModel<>();
        itemList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(itemList);

        // Add components to frame
        frame.add(buttonPanel, BorderLayout.NORTH);
        frame.add(scrollPane, BorderLayout.CENTER);

        // Button actions
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String newItem = JOptionPane.showInputDialog(frame, "Enter new item:");
                if (newItem != null && !newItem.trim().isEmpty()) {
                    listModel.addElement(newItem.trim());
                }
            }
        });

        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedIndex = itemList.getSelectedIndex();
                if (selectedIndex != -1) {
                    listModel.remove(selectedIndex);
                } else {
                    JOptionPane.showMessageDialog(frame, "No item selected", "Error", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }

    public void show() {
        SwingUtilities.invokeLater(() -> frame.setVisible(true));
    }

    public static void main(String[] args) {
        MainSwingApp app = new MainSwingApp();
        app.show();
    }
}
