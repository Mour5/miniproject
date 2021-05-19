package com.company.miniproject;

import com.company.User;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

public class ClientHandler extends Thread {
    private Socket socket;
    DBManager dbManager;
    public ClientHandler(Socket socket) {
        this.socket = socket;
    }

    public void run() {
        try {
            dbManager = new DBManager();
            dbManager.connectToDb();
            System.out.println("CONNECTED TO MYSQL");
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            PackageData pd = new PackageData();
            while((pd = (PackageData) inputStream.readObject())!=null){
                if(pd.getOperationType().equalsIgnoreCase("add")){
                    addStudent(pd.getStudent());
                }else if(pd.getOperationType().equals("list")){
                    outputStream.writeObject(listStudents());
                }
                System.out.println(pd.getStudent());
            }
            socket.close();
            inputStream.close();
            outputStream.close();
        } catch (Exception e) {
            System.out.println("Client has been disconnected");
        }
    }

    public void addStudent(Students s){
        try{
            PreparedStatement st = dbManager.getConnection().prepareStatement("insert into students(name, surname, age) values(?,?,?)");
            st.setString(1, s.getName());
            st.setString(2, s.getSurname());
            st.setInt(3, s.getAge());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public ArrayList<Students> listStudents(){
        ArrayList<Students> students = new ArrayList<>();
        try{
            PreparedStatement st = dbManager.getConnection().prepareStatement("select * from students");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                students.add(new Students(id, name, surname, age));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return students;
    }
}
