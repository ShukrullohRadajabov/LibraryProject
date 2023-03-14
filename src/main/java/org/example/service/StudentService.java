package org.example.service;

import org.example.dto.Student;
import org.example.dto.StudentBook;
import org.example.repository.BookStudentRepository;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class StudentService {
    @Autowired
    private StudentRepository studentRepository;
    @Autowired
    private BookStudentRepository bookStudentRepository;

    public List<Student> studentList() {
        List<Student> studentList = studentRepository.getStudentList();
        if(studentList.isEmpty()){
            return null;
        }
        return studentList;
    }

    public String addStudent(String name, String surname, String phone) {
        int i = studentRepository.addStudent(name, surname, phone);
        if(i==0){
            return "Something is wrong";
        }
        return "New student successfully added";
    }


    public String adminDeleteStudent(String studentId) {
        int n = studentRepository.deleteStudent(studentId);
        if (n==0) {
            return null;
        }
        return "Student successfully deleted";
    }

    public List<StudentBook> studentTakenBook(int studentId) {
        List<StudentBook> studentBookList = bookStudentRepository.studentTakenBook(studentId);
        return studentBookList;
    }


    public List<StudentBook> bookTakenHistory(Integer bookId) {
        List<StudentBook> studentBookList = bookStudentRepository.bookTakenHistory(bookId);
        return studentBookList;
    }
}
