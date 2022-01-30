package program;

import program.workers.Consumer;
import program.workers.Manufacturer;

import java.util.ArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.List;

public class Executor {

    private final ExecutorService threadPoolExecutor;

    public Executor() {
        final int numberOfTasks = 2;
        this.threadPoolExecutor = Executors.newFixedThreadPool(numberOfTasks);
    }

    /**
     * Здесь создаются и направляются на работу работник.
     * @throws InterruptedException
     */
    public void processWork() throws InterruptedException {
        // Создаем работников.
        List<Runnable> processes = configureTasks();
        // Заставляем их работать.
        processes.forEach(threadPoolExecutor::submit);

        // Программа работает 10 секунд.
        for (int i = 1; i <= 10; ++i) {
            Thread.sleep(1000);
            System.out.println("Прогресс: " + i * 10 + "%");
        }

        // Выключаем.
        threadPoolExecutor.shutdownNow();
        threadPoolExecutor.awaitTermination(10, TimeUnit.SECONDS);
        System.out.println("Завершаю работу!");
    }

    /**
     * Настраиваем задачи.
     * @return Список задач на выполнение.
     */
    private List<Runnable> configureTasks() {
        final int capacity = 10;
        final Container container = new Container(capacity);

        Manufacturer manufacturer = new Manufacturer("Александр Кучук", container);
        Consumer consumer = new Consumer("Максим Zappy", container);

        List<Runnable> processes = new ArrayList<>();
        processes.add(manufacturer);
        processes.add(consumer);
        return processes;
    }
}
