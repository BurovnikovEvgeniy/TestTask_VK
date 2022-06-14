package Utils;

import java.util.List;
import java.util.Random;

public class RandomValueGenerator<T> {
    private static final Random random = new Random();
    private final List<T> listValue;
    private T randomValue;

    public RandomValueGenerator(List<T> listValue) {
        if (listValue == null || listValue.isEmpty()) {
            throw new IllegalArgumentException("The list of possible options is empty or null");
        }
        this.listValue = listValue;
    }

    public T generate() {
        randomValue = listValue.get(random.nextInt(listValue.size()));
        return randomValue;
    }

    public T get() {
        return randomValue;
    }

    public boolean contains(T value) {
        return listValue.contains(value);
    }
}
