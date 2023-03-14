package org.example;

import org.example.controller.AuthController;
import org.example.db.Config;
import org.example.db.DataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class Main {
    public static void main(String[] args) {
//        DataBase.initTable();
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Config.class);
        AuthController authController = (AuthController) applicationContext.getBean("authController");
        authController.start();
    }
}