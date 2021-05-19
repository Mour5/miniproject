package com.company.miniproject;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.ArrayList;

public class MainMenu extends JPanel {

    private MainFrame parent;

    private JButton addStudentButton;
    private JButton listStudentsButton;
    private JButton exitButton;

    public MainMenu(MainFrame parent) {

        this.parent = parent;

        setSize(500,500);
        setLayout(null);

        addStudentButton = new JButton("ADD STUDENT");
        addStudentButton.setSize(300,30);
        addStudentButton.setLocation(100,100);
        add(addStudentButton);
        addStudentButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                parent.getMainMenuPage().setVisible(false);
                parent.getFirstPage().setVisible(true);
            }
        });

        listStudentsButton = new JButton("LIST STUDENTS");
        listStudentsButton.setSize(300,30);
        listStudentsButton.setLocation(100,150);
        add(listStudentsButton);
        listStudentsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                ArrayList<Students> students = new ArrayList<>();
                PackageData pd = new PackageData("list", null);
                try {
                    Client.ServerConnection.getHandler().outputStream.writeObject(pd);
                    students = (ArrayList<Students>) Client.ServerConnection.getHandler().inputStream.readObject();
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (ClassNotFoundException e) {
                    e.printStackTrace();
                }
                parent.getMainMenuPage().setVisible(false);
                parent.getSecondPage().setVisible(true);
                parent.getSecondPage().generateTable(students);
            }
        });

        exitButton = new JButton("EXIT");
        exitButton.setSize(300,30);
        exitButton.setLocation(100,200);
        add(exitButton);
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
    }
}
