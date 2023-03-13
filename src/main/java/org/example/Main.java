package org.example;

import org.example.controller.AuthController;
//import org.example.config.Config;
import org.example.db.Config;
import org.example.db.DataBase;
import org.example.db.InitDataBase;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {
    public static void main(String[] args) {
        DataBase.initTable();
        ApplicationContext applicationContext =new AnnotationConfigApplicationContext(Config.class);
//        InitDataBase initDataBase = (InitDataBase) applicationContext.getBean("InitDataBase");
        AuthController authController = (AuthController) applicationContext.getBean("authController");
        authController.start();
    }
}