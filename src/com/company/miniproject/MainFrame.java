package com.company.miniproject;

import javax.swing.*;

public class MainFrame extends JFrame {

    private MainMenu mainMenuPage;
    private AddStudent firstPage;
    private ListStudents secondPage;
    protected Students[] students = new Students[100];
    int id = 0;

    public MainFrame(){

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setTitle("BITLAB Application");
        setSize(500,500);
        setLayout(null);

        mainMenuPage = new MainMenu(this);
        mainMenuPage.setVisible(true);
        add(mainMenuPage);

        firstPage = new AddStudent(this);
        firstPage.setVisible(false);
        add(firstPage);

        secondPage = new ListStudents(this);
        secondPage.setVisible(false);
        add(secondPage);

    }

    public MainMenu getMainMenuPage() {
        return mainMenuPage;
    }

    public AddStudent getFirstPage() {
        return firstPage;
    }

    public ListStudents getSecondPage() {
        return secondPage;
    }

    public void setStudents(Students student){
        students[id] = student;
        id++;
    }

    public void setStudents(Students[] students) {
        this.students = students;
    }

    public Students[] getStudents() {
        return students;
    }
}
