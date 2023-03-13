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
}
