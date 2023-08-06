/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package edu.handong.csee.java.hw5;

/**
 * App class represents a simple application that prints a greeting message.
 */
public class App {

    /**
     * Get the greeting message.
     *
     * @return the greeting message.
     */
    public String getGreeting() {
        return "Hello World!";
    }

    /**
     * The main method of the application.
     *
     * @param args the command-line arguments.
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
