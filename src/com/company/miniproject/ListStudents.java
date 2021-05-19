package com.company.miniproject;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ListStudents extends Container {
    private MainFrame parent;
    protected Students student;
    protected JTable table;
    protected JScrollPane scrollPane;
    protected String[] header = {"id", "name", "surname", "age"};
    protected JButton back;
    public ListStudents(MainFrame parent) {

        this.parent = parent;
        setSize(500, 500);
        setLayout(null);

        back = new JButton("BACK");
        back.setSize(300,30);
        back.setLocation(100, 330);
        back.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parent.getMainMenuPage().setVisible(true);
                parent.getSecondPage().setVisible(false);
            }
        });
        add(back);

        table = new JTable();
        table.setRowHeight(30);
        table.setEnabled(false);

        scrollPane =  new JScrollPane(table);
        scrollPane.setSize(350, 200);
        scrollPane.setLocation(80, 80);
        add(scrollPane);

    }



    public void generateTable(ArrayList<Students> students) {
        Object data[][] =  new Object[students.size()][5];
        for (int i = 0; i < students.size(); i++) {
            if(students.get(i) != null){
                data[i][0] = students.get(i).getId();
                data[i][1] = students.get(i).getName();
                data[i][2] = students.get(i).getSurname();
                data[i][3] = students.get(i).getAge();
            }
        }
        DefaultTableModel model = new DefaultTableModel(data,header);
        table.setModel(model);
    }
}
