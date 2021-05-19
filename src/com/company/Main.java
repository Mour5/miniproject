package com.company;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static Connection connection;
    public static void main(String[] args) {
        ArrayList<User> users = new ArrayList<>();
        Scanner in = new Scanner(System.in);
        connectionToDb();
        while(true){
            System.out.println("[1] ADD USER");
            System.out.println("[2] LIST USERS");
            System.out.println("[3] EDIT USER");
            System.out.println("[4] DELETE USER");
            System.out.println("[0] EXIT");
            int choice = in.nextInt();
            if(choice == 1){
                System.out.println("Insert name: ");
                String name = in.next();
                System.out.println("Insert surname");
                String surname = in.next();
                System.out.println("Insert age");
                int age = in.nextInt();
                User u = new User(null, name, surname, age);
                addUser(u);
            }else if(choice ==2){
                users = listUsers();
                for(User u : users){
                    System.out.println(u);
                }
            }else if(choice == 3){
                System.out.println("Insert id");
                Long id = in.nextLong();
                System.out.println("Insert name: ");
                String name = in.next();
                System.out.println("Insert surname");
                String surname = in.next();
                System.out.println("Insert age");
                int age = in.nextInt();
                User u = new User(id, name, surname, age);
                updateUser(u);
            }else if(choice == 4){
                System.out.println("Insert id");
                int id = in.nextInt();
                deleteUser(id);
            }else if(choice == 0){
                System.exit(0);
            }
        }
}

    public static void connectionToDb(){
        try{
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/jdbc_test?useUnicode=true&serverTimezone=UTC","root","");
            System.out.println("CONNECTED");

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static ArrayList<User> listUsers(){
        ArrayList<User> users = new ArrayList<>();
        try{
            PreparedStatement st = connection.prepareStatement("select * from users");
            ResultSet rs = st.executeQuery();
            while(rs.next()){
                Long id = rs.getLong("id");
                String name = rs.getString("name");
                String surname = rs.getString("surname");
                int age = rs.getInt("age");
                users.add(new User(id, name, surname, age));
            }
        }catch (Exception e){
            e.printStackTrace();
        }
        return users;
    }

    public static void addUser(User u){
        try{
            PreparedStatement st = connection.prepareStatement("insert into users(name, surname, age) values(?,?,?)");
            st.setString(1, u.getName());
            st.setString(2, u.getSurname());
            st.setInt(3, u.getAge());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void updateUser(User u){
        try{
            PreparedStatement st = connection.prepareStatement("update users set name=?, surname=?, age=? where id=?");
            st.setString(1, u.getName());
            st.setString(2, u.getSurname());
            st.setInt(3, u.getAge());
            st.setLong(4, u.getId());
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public static void deleteUser(int id){
        try{
            PreparedStatement st = connection.prepareStatement("delete from users where id =?");
            st.setInt(1, id);
            st.executeUpdate();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
