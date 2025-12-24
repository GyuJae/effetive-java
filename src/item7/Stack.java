package item7;

import java.util.Arrays;
import java.util.EmptyStackException;

public class Stack {
    private static final int DEFAULT_INITIAL_CAPACITY = 16;

    private int size = 0;
    private Object[] elements;

    public Stack() {
        this.elements = new Object[DEFAULT_INITIAL_CAPACITY];
    }

    public void push(Object e) {
        this.ensureCapacity();
        this.elements[this.size++] = e;
    }

    public Object pop() {
        if (size == 0) throw new EmptyStackException();

        // 다 쓴 참조 해제
        this.elements[this.size] = null;

        return elements[--size];
    }

    private void ensureCapacity() {
        if (elements.length == size) {
            this.elements = Arrays.copyOf(this.elements, 2 * this.size + 1);
        }
    }
}
