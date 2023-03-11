package org.example.controller;

import org.example.dto.Student;
import org.example.service.AuthService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component

public class AuthController {
    AuthService authService = new AuthService();
    public void start() {
        boolean game = true;
        while (game) {
            menu();
            int action = ScannerUtil.getAction();
            switch (action) {
                case 1:
                    login();
                    break;
                case 0:
                    game = false;
                default:
                    System.out.println("Mazgi nima bu");
            }
        }
    }

    public void menu() {
        System.out.println("********************    Menu    ***********************");
        System.out.println("1. Login > ");
        System.out.println("0. Exit > ");
    }
    public void login() {
        System.out.print("Enter phone:");
        Scanner scanner = new Scanner(System.in);
        String phone = scanner.nextLine();
        authService.login(phone);
    }
}


