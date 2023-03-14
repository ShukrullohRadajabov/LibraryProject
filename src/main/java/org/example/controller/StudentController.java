package org.example.controller;

import org.example.container.ComponentContainer;
import org.example.dto.Book;
import org.example.dto.Student;
import org.example.dto.StudentBook;
import org.example.repository.BookRepository;
import org.example.service.BookService;
import org.example.service.BookStudentService;
import org.example.util.ScannerUtil;
import org.example.util.ScannerUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import java.util.List;
import java.util.Scanner;
@Controller
public class StudentController {
    @Autowired
    private BookStudentService bookStudentService;
    @Autowired
    private BookRepository bookRepository;
    Scanner scannerInt = ScannerUtil2.SCANNER_INT;
    Scanner scannerStr = ScannerUtil2.SCANNER_STR;
    @Autowired
    private BookService bookService;
    public void start() {
        boolean b = true;
        while (b) {
            userMenu();
            int operation = ScannerUtil.getAction();
            switch (operation) {
                case 1: {
                    bookList();
                    break;
                }
                case 2: {
                    takeBook();
                    break;
                }
                case 3: {
                    takenBook();
                    break;
                }
                case 4: {
                    returnBook();
                    break;
                }
                case 5: {
                    history();
                    break;
                }
                case 6: {
                    orderBook();
                    break;
                }
                default:
                    b = false;
                    System.out.println("Wrong operation number");
            }
        }
    }
    private void orderBook() {
        System.out.println("Enter book name: ");
        String bookName = scannerStr.nextLine();
        System.out.println("Your order has been accepted");
    }
    private void history() {
        int a=1;
        Student student = ComponentContainer.currentStudent;
        List<StudentBook> studentBookList = bookStudentService.history(student.getId());
        if(studentBookList==null){
            System.out.println("You haven't taken books yet");
        }
        else {
            for (StudentBook studentBook : studentBookList) {
                Book book = bookRepository.getBookName(studentBook.getBookId());
                System.out.println("Id-> "+ a++ +"\t\tBook Name-> "+book.getTitle()+"\t\tAuthor Name-> "+book.getAuthor()+"\t\tStatus-> "+studentBook.getStatus()+"\t\tTaken time-> "+studentBook.getCreatedDate()+"\t\tReturn time-> "+studentBook.getReturnedDate());
            }
        }
    }
    private void returnBook() {
        Student student = ComponentContainer.currentStudent;
        takenBook();
        System.out.println("Enter id: ");
        int bookId = scannerInt.nextInt();
        String returnBook = bookStudentService.returnBook(bookId, student);
        System.out.println(returnBook);
    }
    private void takenBook() {
       Student student = ComponentContainer.currentStudent;
       List<StudentBook> studentBookList = bookStudentService.takenBook(student.getId());
       if(studentBookList==null){
           System.out.println("You haven't taken books yet");
       }
       else {
           for (StudentBook studentBook : studentBookList) {
               Book book = bookRepository.getBookName(studentBook.getBookId());
               System.out.println("Id-> "+studentBook.getId()+"\t\tBook Name-> "+book.getTitle()+"\t\tTaken time-> "+studentBook.getCreatedDate());
           }
       }
    }
    private void takeBook() {
        Student student = ComponentContainer.currentStudent;
        bookList();
        System.out.println("Enter book id: ");
        int bookId = scannerInt.nextInt();
        System.out.println("Enter returned date: ");
        int date = scannerInt.nextInt();
        String result = bookStudentService.takeBook(bookId, student, date);
        System.out.println(result);
    }
    private void bookList() {
        List<Book> bookList = bookService.userBookList();
        if (bookList == null) {
            System.out.println("Book List empty yet");
        } else {
            for (Book book : bookList) {
                System.out.print("Id: " + book.getId() + "\t Name: " + book.getTitle() + "\t Author: " + book.getAuthor());
                System.out.println();
            }
        }
    }
    public void userMenu() {
        System.out.println("1. Book List");
        System.out.println("2. Take book ");
        System.out.println("3. Taken book");
        System.out.println("4. Return book");
        System.out.println("5. History");
        System.out.println("6. Order book");
        System.out.println("0. Back");
    }
}
