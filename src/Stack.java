

import java.util.EmptyStackException;

public class Stack {
    private String[] elements;
    private int top;
    private int capacity;

    public Stack(int capacity) {
        this.capacity = capacity;
        this.elements = new String[capacity];
        this.top = -1;
    }

    public void push(String value) {
        if (top == capacity - 1) {
            throw new StackOverflowError("Stack capacity reached. Cannot push: " + value);
        }
        elements[++top] = value;
    }

    public String pop() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top--];
    }

    public String peek() {
        if (isEmpty()) {
            throw new EmptyStackException();
        }
        return elements[top];
    }

    public boolean isEmpty() {
        return top == -1;
    }
}







