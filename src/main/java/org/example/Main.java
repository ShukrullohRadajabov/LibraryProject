package org.example;

import org.example.controller.AuthController;
import org.example.db.DataBase;

public class Main {
    public static void main(String[] args) {
        DataBase.initTable();
        AuthController authController = new AuthController();
        authController.start();
    }
}