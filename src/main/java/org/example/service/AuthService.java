package org.example.service;

import org.example.container.ComponentContainer;
import org.example.controller.AdminController;
import org.example.controller.StudentController;
import org.example.dto.Student;
import org.example.enums.Role;
import org.example.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AuthService {
    @Autowired
    StudentController studentController;
    @Autowired
    StudentRepository studentRepository;
    @Autowired
    AdminController adminController;

    public void login(String phone) {
        Student student = studentRepository.getUserByPhone(phone);

        if (student == null) {
            System.out.println("Phone or Password incorrect");
            return;
        }

        if (!student.isVisible()) {
            System.out.println("You not allowed.MF");
            return;
        }

        ComponentContainer.currentStudent = student;
        if (student.getRole().equals(Role.ADMIN)) {
            adminController.start();
        } else if (student.getRole().equals(Role.STUDENT)) {
            studentController.start();
        } else {
            System.out.println("You don't have any role.");
        }
    }
}
