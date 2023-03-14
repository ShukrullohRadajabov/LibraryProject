package org.example.service;

import org.example.dto.Student;
import org.example.dto.StudentBook;
import org.example.repository.BookStudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class BookStudentService {
    @Autowired
    private BookStudentRepository bookStudentRepository;

    public String takeBook(Integer bookId, Student student, int date) {
        if (student.getBook_count() < 5) {
            if (bookId == null) {
                return "Book id empty";
            }
            if (bookId == 0) {
                return "Wrong book id";
            }
            int i = bookStudentRepository.takeBook(bookId, student, date);
            if (i == 0) {
                return "Error code 500";
            }
        } else {
            return "Sorry you can not take more than 5 books";
        }
        return "Successfully added";
    }

    public List<StudentBook> takenBook(Integer id) {
        List<StudentBook> studentBookList = bookStudentRepository.takenBook(id);
        return studentBookList;
    }
}
