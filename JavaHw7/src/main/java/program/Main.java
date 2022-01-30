package program;

import java.util.Random;

public class Main {

    public static final Random random = new Random();

    public static void main(String[] args) {
        Executor executor = new Executor();
        try {
            executor.processWork();
        } catch (InterruptedException ex) {
            System.out.println("Error:" + ex.getMessage());
        }
    }
}
