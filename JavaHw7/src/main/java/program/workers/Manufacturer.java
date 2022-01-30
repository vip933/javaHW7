package program.workers;

import program.Container;
import program.Main;

public class Manufacturer implements Runnable {

    private final String name;
    private final Container container;

    public Manufacturer(String name, Container container) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (container) {
                // Если контейнер и так заполнен, нужно ждать, пока потребитель разгрузит его.
                if (container.isFull()) {
                    int toWait = Main.random.nextInt(1000, 3000);
                    System.out.println("Я - " + this + " не могу пока ничего делать, тк контейнер переполнен!");
                    System.out.println("Буду ждать " + toWait + " мс.");
                    System.out.println();

                    try {
                        Thread.sleep(toWait);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    // Добавляем числа в контейнер.
                } else {
                    int generatedNumber = Main.random.nextInt(1, 1000);
                    container.addData(generatedNumber);
                    System.out.println("Я - " + this + " добавил в контейнер число " + generatedNumber);
                    System.out.println();
                }
            }
        }
    }

    public String toString() {
        return "Производитель " + name;
    }
}
