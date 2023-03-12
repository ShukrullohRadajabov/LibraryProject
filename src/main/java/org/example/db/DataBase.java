package org.example.db;



import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

@Component
public class DataBase {
    public static Connection getConnection() {
        try {
            Connection con = DriverManager.getConnection("jdbc:postgresql://localhost:5432/libraryProject", "postgres", "3312425bD");
            return con;
        } catch (SQLException e) {
            System.out.println(e.getSQLState());
            e.printStackTrace();
            System.exit(-1);
        }
        return null;

    }

    public static void initTable() {
        String student = "create table if not exists student ( \n" +
                "             id serial primary key,\n" +
                "             name varchar(20) not null,\n" +
                "             surname varchar(20) not null,\n" +
                "             phone varchar(15),\n" +
                "             created_date timestamp default now(),\n" +
                "             visible boolean default true, \n" +
                "             role varchar (10) not null \n"+ ");";


        String book = "create table if not exists book(\n" +
                "id serial primary key,\n" +
                "title varchar(30),\n" +
                "author varchar(50),\n" +
                "published_year int ,\n" +
                "amount int ,\n" +
                "visible int\n" + ");";

        String studentBook = "create table if not exists student_book(" +
                "id serial primary key, " +
                "student_id int ,\n" +
                "book_id int ,\n" +
                "created_date timestamp default now(), "+
                "status varchar(30)," +
                "returned_date timestamp, "+
                "duration integer\n" + ");";
        execute(student);
        execute(book);
        execute(studentBook);
    }

    private static void execute(String sql) {
        try (Connection connection = getConnection()) {
            Statement statement = connection.createStatement();
            statement.executeUpdate(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


}
