package org.example.service;
import org.example.dto.Book;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    public String adminAddBook(String title, String author, Integer publisherYear, String amount) {
        int n = bookRepository.addBook(title, author, publisherYear, amount);
        if(n==0){
            return "Something is wrong!";
        }
        else {
            return null;
        }
    }
    public String adminDeleteBook(String bookId) {
        int n = bookRepository.deleteBook(bookId);
        if (n==0) {
            return null;
        }
        return "Successfully deleted";
    }

    public List<Book> userBookList() {
        List<Book> bookList = bookRepository.getBookList();
        if(bookList.isEmpty()){
            return null;
        }
        return bookList;
    }
}
