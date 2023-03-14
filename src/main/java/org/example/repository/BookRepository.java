package org.example.repository;

import org.example.dto.Book;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookRepository {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int addBook(String title, String author, Integer publisherYear, String amount){
        String sql = "insert into book(title, author, publisher_year, amount) values('%s', '%s', '%s', '%s')";
        sql = String.format(sql, title, author, publisherYear, amount);
        int n = jdbcTemplate.update(sql);
        return n;
    }

    public List<Book> getBookList() {
        String sql = "select * from book where visible = true";
        List<Book> list = jdbcTemplate.query(sql, new BeanPropertyRowMapper<>(Book.class));
        return list;
    }

    public int deleteBook(String bookId) {
        String sql = "update book set visible = false where id = '"+bookId+"';";
        int n = jdbcTemplate.update(sql);
        return n;
    }
    public Book getBookName(Integer bookId) {
        String sql = "select * from book where id = '"+bookId+"'";
        Book book = jdbcTemplate.queryForObject(sql, new BeanPropertyRowMapper<>(Book.class));
        return book;
    }
}
