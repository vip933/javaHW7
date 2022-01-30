package program;

import java.util.ArrayDeque;
import java.util.Queue;

public class Container {

    private final Queue<Integer> container;
    private final int containerCapacity;

    public Container(int containerCapacity) {
        this.containerCapacity = containerCapacity;
        this.container = new ArrayDeque<>(containerCapacity);
    }

    public Boolean isEmpty() {
        return container.isEmpty();
    }

    public Boolean isFull() {
        return container.size() == containerCapacity;
    }

    /**
     * Забираем какое-то число.
     * @return Число, которое забрали.
     */
    public Integer decreaseData() {
        return container.poll();
    }

    /**
     * Добавляем какое-то число.
     * @param number Число, которое хотим добавить.
     */
    public void addData(int number) {
        container.add(number);
    }
}
