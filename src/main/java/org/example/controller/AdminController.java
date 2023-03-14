package org.example.controller;

import org.example.dto.Book;
import org.example.dto.Student;
import org.example.dto.StudentBook;
import org.example.repository.BookRepository;
import org.example.repository.StudentRepository;
import org.example.service.BookStudentService;
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
    private BookRepository bookRepository;
    @Autowired
    private StudentRepository studentRepository;
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
        String result = bookService.adminAddBook(title, author, publishedYear, amount);
        if(result==null){
            System.out.println("Successfully added!");
        }
        else System.out.println(result);
    }
    public void bookList(){
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
        studentList();
        System.out.println("Enter student id: ");
        Integer studentId = scannerInt.nextInt();
        List<StudentBook> studentBookList = studentService.studentTakenBook(studentId);
        if(studentId==null){
            System.out.println("Student id is free");
        }
        else if (studentBookList.isEmpty()) {
            System.out.println("Wrong student id or student list empty");
        }
        else {
            studentBookList.forEach(System.out::println);
        }
    }

    private void bookTakenHistory() {
        bookList();
        System.out.println("Enter book id: ");
        int bookId = scannerInt.nextInt();
        List<StudentBook> studentBookList = studentService.bookTakenHistory(bookId);
        if (studentBookList.isEmpty()) {
            System.out.println("This book history is empty");
        }
        else{
            for (StudentBook studentBook : studentBookList) {
                Book book = bookRepository.getBookName(studentBook.getBookId());
                Student student = studentRepository.getStudentName(studentBook.getStudentId());
                System.out.println("Id-> "+studentBook.getId()+
                        "\t\tStudent name-> "+student.getName()+
                        "\t\tStudent surname-> "+student.getSurname()+
                        "\t\tStudent phoneNumber-> "+student.getPhone()+
                        "\t\tBook Name-> "+book.getTitle()+
                        "\t\tBook Author-> "+book.getAuthor()+
                        "\t\tStatus-> "+studentBook.getStatus()+
                        "\t\tTaken time-> "+studentBook.getCreatedDate()+
                        "\t\tReturn time-> "+studentBook.getReturnedDate());
            }
        }
    }
}
