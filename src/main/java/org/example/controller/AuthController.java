package org.example.controller;

import org.example.service.AuthService;
import org.example.util.ScannerUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import java.util.Scanner;

@Controller

public class AuthController {
    @Autowired
    private AuthService authService;
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


