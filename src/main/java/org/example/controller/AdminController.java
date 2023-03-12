package org.example.controller;

import org.example.service.BookCardService;
import org.example.service.BookService;
import org.example.service.StudentService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;

import java.util.Scanner;
@Controller
public class AdminController {
    @Autowired
    private StudentService studentService = new StudentService();
    @Autowired
    private BookService bookService = new BookService();
    @Autowired
    private BookCardService cardService = new BookCardService();

    public void start() {
        boolean b = true;
        while (b) {
            adminMenu();
            int operation = ScannerUtil.getAction();
            switch (operation) {
                case 1 :{
                    bookList();
                    break;
                }
                case 2 :{
                    addBook();
                    break;

                }
                case 3 :{
                    studentList();
                    break;
                }
                case 4 :{
                    deleteBook();
                    break;
                }
                case 5 :{
                    addStudent();
                    break;
                }
                case 6 :{
                    deleteStudent();
                    break;
                }
                case 7 :{
                    studentTakenBook();
                    break;
                }
                case 8 :{
                    bookTakenHistory();
                    break;
                }
                default:
                    b = false;
                    System.out.println("Wrong operation number");
            }
        }
    }

    public void adminMenu() {
        System.out.println("1. Book List");
        System.out.println("2. Add book ");
        System.out.println("3. Delete book");
        System.out.println("4. Student list");
        System.out.println("5. Add student");
        System.out.println("6. Delete Student");
        System.out.println("7. Student Taken Book");
        System.out.println("8. Book Taken history");
        System.out.println("0. Back");
    }


    private void addBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter book author: ");
        String author = scanner.nextLine();
        System.out.print("Enter published year: ");
        Integer publishedYear = scanner.nextInt();
        System.out.print("Enter book amount: ");
        String amount = scanner.nextLine();
        int i = bookService.adminAddBook(title, author, publishedYear, amount);
        if(i==0){
            System.err.println("Wrong!");
        }
        else System.out.println("Add successfully");
    }

    private void bookList() {
        bookService.bookList();
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book id: ");
        String bookId = scanner.nextLine();
        bookService.adminDeleteBook(bookId);
    }

    private void studentList() {
        bookService.studentList();
    }

    private void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        bookService.addStudent(name, surname, phone);
    }

    private void deleteStudent() {
        System.out.print("Enter student id: ");
        Scanner scanner = new Scanner(System.in);
        String studentId = scanner.nextLine();

        bookService.adminDeleteStudent(studentId);
    }

    private void studentTakenBook() {
        bookService.studentTakenBook();
    }

    private void bookTakenHistory() {
        bookService.studentTakenHistory();
    }
}
