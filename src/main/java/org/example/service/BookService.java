package org.example.service;

import org.example.db.DataBase;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BookService {
    public void bookList() {

    }
    public int adminAddBook(String title, String author, Integer publisherYear, String amount) {
            Connection connection = DataBase.getConnection();
            try {
                String sql = "insert into student (title, author, phone, published_year, amount, visable) " + " values ('%s','%s','%s','%s','%s','%s')";
                sql = String.format(sql, title, author, publisherYear, amount, "true");

                Statement statement = connection.createStatement();
                return statement.executeUpdate(sql);

            } catch (SQLException sqlException) {
                sqlException.printStackTrace();
            } finally {
                try {
                    if (connection != null) {
                        connection.close();
                    }
                } catch (SQLException e) {
                    throw new RuntimeException(e);
                }
            }
            return 0;
    }

    public void studentList() {

    }

    public void addStudent(String name, String surname, String phone) {

    }

    public void adminDeleteStudent(String studentId) {


    }

    public void studentTakenBook() {

    }

    public void studentTakenHistory() {

    }

    public void adminDeleteBook(String bookId) {

    }
}
