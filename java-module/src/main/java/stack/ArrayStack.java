package stack;

public class ArrayStack<T> implements Stack<T> {
    private T[] arrayStack;
    private int top;
    private int capacity;

    @SuppressWarnings("unchecked")
    public ArrayStack(int capacity) {
        this.capacity = capacity;
        this.arrayStack = (T[]) new Object[capacity];
        this.top = -1;
    }

    @Override
    public void push(T t) {
        if (isFull()) {
            resize();
        }
        arrayStack[++top] = t;
    }

    private boolean isFull() {
        return top == capacity - 1;
    }

    @SuppressWarnings("unchecked")
    private void resize() {
        int newCapacity = capacity * 2;
        T[] newArray = (T[]) new Object[newCapacity];
        for (int i = 0; i <= top; i++) {
            newArray[i] = arrayStack[i];
        }
        arrayStack = newArray;
        capacity = newCapacity;
    }

    @Override
    public void pop() {
        if (isEmpty()) {
            throw new IllegalStateException("stack.Stack underflow: Cannot remove an element from an empty stack.");
        }
        top--;
    }

    @Override
    public T peek() {
        return arrayStack[top];
    }

    @Override
    public boolean isEmpty() {
        return top == -1;
    }

    @Override
    public int size() {
        return top + 1;
    }

    @Override
    public void print() {
        for (int i = 0; i <= top; i++) {
            System.out.print(arrayStack[i] + " ");
        }
        System.out.println();
    }
}
