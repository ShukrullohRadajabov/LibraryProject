package org.example.controller;

import org.example.dto.Book;
import org.example.dto.Student;
import org.example.service.BookCardService;
import org.example.service.BookService;
import org.example.service.StudentService;
import org.example.util.ScannerUtil;
import org.example.util.ScannerUtil2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.List;
import java.util.Scanner;
@Controller
public class AdminController {

    Scanner scannerInt = ScannerUtil2.SCANNER_INT;
    Scanner scannerStr = ScannerUtil2.SCANNER_STR;
    @Autowired
    private StudentService studentService;
    @Autowired
    private BookService bookService;
    @Autowired
    private BookCardService cardService;

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
                    deleteBook();
                    break;
                }
                case 4 :{
                    studentList();
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
        System.out.print("Enter book title: ");
        String title = scannerStr.nextLine();
        System.out.print("Enter book author: ");
        String author = scannerStr.nextLine();
        System.out.print("Enter published year: ");
        Integer publishedYear = scannerInt.nextInt();
        System.out.print("Enter book amount: ");
        String amount = scannerStr.nextLine();
        int i = bookService.adminAddBook(title, author, publishedYear, amount);
        if(i==0){
            System.err.println("Wrong!");
        }
        else System.out.println("Add successfully");
    }
    private void bookList(){
        List<Book> bookList = bookService.getBookList1();
        if(bookList == null){
            System.out.println("Book List yet empty");
        }else{
            bookList.forEach(System.out::println);
        }
    }

    private void deleteBook() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter book id: ");
        String bookId = scanner.nextLine();
        String result = bookService.adminDeleteBook(bookId);
        if (result==null) {
            System.err.println("Something is wrong!");
        }
        else System.out.println(result);
    }

    private void studentList() {
        List<Student> studentList = studentService.studentList();
        studentList.forEach(System.out::println);
    }

    private void addStudent() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter name: ");
        String name = scanner.nextLine();

        System.out.print("Enter surname: ");
        String surname = scanner.nextLine();

        System.out.print("Enter phone: ");
        String phone = scanner.nextLine();

        String result = studentService.addStudent(name, surname, phone);
        System.out.println(result);
    }

    private void deleteStudent() {
        System.out.print("Enter student id: ");
        String studentId = scannerStr.nextLine();
        String result = studentService.adminDeleteStudent(studentId);
        System.out.println(result);
    }

    private void studentTakenBook() {
        studentService.studentTakenBook();
    }

    private void bookTakenHistory() {
        studentService.studentTakenHistory();
    }
}
