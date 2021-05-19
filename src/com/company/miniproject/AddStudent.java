package com.company.miniproject;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

public class AddStudent extends Container {
    int id = 0;
    private MainFrame parent;
    protected Students student;
    protected JLabel name;
    protected JLabel surname;
    protected JLabel age;
    protected JButton confirm;
    protected JButton back;
    protected JTextField textFieldName;
    protected JTextField textFieldSurname;
    protected JTextField textFieldAge;
    protected PackageData pd;

    public AddStudent(MainFrame parent) {

        this.parent = parent;
        setSize(500,500);
        setLayout(null);

        name = new JLabel();
        name.setSize(300,30);
        name.setLocation(100,100);
        name.setText("Name: ");
        add(name);

        surname = new JLabel();
        surname.setSize(300,30);
        surname.setLocation(100,140);
        surname.setText("Surname: ");
        add(surname);

        age = new JLabel();
        age.setSize(300,30);
        age.setLocation(100, 180);
        age.setText("Age: ");
        add(age);

        textFieldName = new JTextField();
        textFieldName.setSize(200, 30);
        textFieldName.setLocation(200, 100);
        add(textFieldName);

        textFieldSurname = new JTextField();
        textFieldSurname.setSize(200, 30);
        textFieldSurname.setLocation(200, 140);
        add(textFieldSurname);

        textFieldAge = new JTextField();
        textFieldAge.setSize(200,30);
        textFieldAge.setLocation(200,180);
        add(textFieldAge);

        confirm = new JButton();
        confirm.setSize(150,30);
        confirm.setLocation(100,260);
        confirm.setText("CONFIRM");
        confirm.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = (String) textFieldName.getText();
                String surname = (String) textFieldSurname.getText();
                int age = Integer.parseInt(textFieldAge.getText());
                if(!name.equals("") && !surname.equals("") && age>0) {
                    student = new Students(null, name, surname, age);
                    pd = new PackageData("add", student);
                    try {
                        Client.ServerConnection.getHandler().getOutputStream().writeObject(pd);
                    } catch (IOException ioException) {
                        ioException.printStackTrace();
                    }
                    textFieldName.setText("");
                    textFieldSurname.setText("");
                    textFieldAge.setText("");
                }
            }
        });
        add(confirm);

        back = new JButton("BACK");
        back.setLocation(250, 260);
        back.setSize(150, 30);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenuPage().setVisible(true);
                parent.getFirstPage().setVisible(false);
            }
        });
        add(back);
    }
}
