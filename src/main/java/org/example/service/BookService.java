package org.example.service;

import org.example.db.DataBase;
import org.example.db.Config;
import org.example.dto.Book;
import org.example.dto.Student;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;

    public List<Book> getBookList1() {
        List<Book> bookList = bookRepository.getBookList();
        if(bookList.isEmpty()){
            return null;
        }
        return bookList;
    }


    public int adminAddBook(String title, String author, Integer publisherYear, String amount) {
        Connection connection = DataBase.getConnection();
        try {
            String sql = "insert into book (title, author, published_year, amount, visible) " + " values ('%s','%s','%s','%s',true)";
            sql = String.format(sql, title, author, publisherYear, amount);

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

    public String adminDeleteBook(String bookId) {
        int n = bookRepository.deleteBook(bookId);
        if (n==0) {
            return null;
        }
        return "Successfully deleted";
    }
}
