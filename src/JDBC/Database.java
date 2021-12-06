package JDBC;

import java.sql.*;

public class Database {

    static Access env;

    public static void main(String[] args) {
        deleteRecord();
//        updateRecord();
//        addStudent(); //don't add without changing unique id
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", env.getUser(), env.getPassword());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from people");
            while (result.next()) {
                System.out.println(result.getString("firstname") + " " + result.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("=======");

//        roster();

    }

    public static void roster() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", env.getUser(), env.getPassword());
            Statement statement = connection.createStatement();
            ResultSet result = statement.executeQuery("select * from people where GRADE > 11");
            while (result.next()) {
                System.out.println(result.getString("firstname") + " " + result.getString("lastname"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void addStudent() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", env.getUser(), env.getPassword());
            Statement statement = connection.createStatement();
            statement.executeUpdate("INSERT INTO People " + "VALUES (2, 'Henry', 'Phillips', 10)");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void updateRecord() {
        String sql = "update people set firstname='Andrew' where id=1";
        String sql2 = "update people set lastname='Michaels' where id=1";
        try (Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", env.getUser(), env.getPassword());
             Statement statement = connection.createStatement();) {
            statement.executeUpdate(sql);
            statement.executeUpdate(sql2);
            System.out.println("Database updated successfully ");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    static void deleteRecord() {
        try {
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/mydatabase", env.getUser(), env.getPassword());
            Statement statement = connection.createStatement();
            String sql = "DELETE FROM People " +
                    "WHERE id in (16)";
            statement.executeUpdate(sql);
            System.out.println("Record has been deleted");
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("An error occurred");
        }
    }
}
