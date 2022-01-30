package program.workers;

import program.Container;
import program.Main;

public class Consumer implements Runnable {

    private final String name;
    private final Container container;

    public Consumer(String name, Container container) {
        this.name = name;
        this.container = container;
    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            synchronized (container) {
                // Если контейнер пуст, нужно ждать пока его заполнит производитель.
                if (container.isEmpty()) {
                    int toWait = Main.random.nextInt(1000, 3000);
                    System.out.println("Я - " + this + " не могу пока ничего делать, тк нет задач!");
                    System.out.println("Буду ждать " + toWait + " мс.");
                    System.out.println();

                    try {
                        Thread.sleep(toWait);
                    } catch (InterruptedException ex) {
                        Thread.currentThread().interrupt();
                    }
                    // Разгружаем контейнер.
                } else {
                    System.out.println("Я - " + this + " достал из контейнера " + container.decreaseData());
                    System.out.println();
                }
            }
        }
    }

    public String toString() {
        return "Потребитель " + name;
    }
}
