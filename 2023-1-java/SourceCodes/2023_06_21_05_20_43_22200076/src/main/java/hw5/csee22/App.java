/*
 * This Java source file was generated by the Gradle 'init' task.
 */
package hw5.csee22;

/**
 * An app.
 */
public class App {
	/**
	 * Greets or says hello.
	 * 
	 * @return greeting
	 */
    public String getGreeting() {
        return "Hello World!";
    }

    /**
     * Executes the program.
     * 
     * @param args any input
     */
    public static void main(String[] args) {
        System.out.println(new App().getGreeting());
    }
}
