package main.java.app;

import main.java.app.controller.Controller;

public class App {
    static Controller controller;
    public static void main(String[] args) {
        controller = new Controller();
        App app = new App();
        app.controller.iniciarApp();
    }
}
