package com.company.miniproject;

import java.io.Serializable;
import java.util.ArrayList;

public class PackageData implements Serializable {
    String operationType;
    ArrayList<Students> students = new ArrayList<>();
    Students student;

    public PackageData(String operationType, ArrayList<Students> students, Students student) {
        this.operationType = operationType;
        this.students = students;
        this.student = student;
    }

    public void addStudent(Students student){
        students.add(student);
    }

    public PackageData(String operationType, Students student){
        this.operationType = operationType;
        this.student = student;
    }

    public PackageData() {
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public ArrayList<Students> getStudents() {
        return students;
    }

    public void setStudents(ArrayList<Students> students) {
        this.students = students;
    }

    public Students getStudent() {
        return student;
    }

    public void setStudent(Students student) {
        this.student = student;
    }


}
