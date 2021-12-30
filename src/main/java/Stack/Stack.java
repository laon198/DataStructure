package Stack;

import java.util.NoSuchElementException;

public interface Stack<E> {
    int size();
    boolean isEmpty();
    E top() throws NoSuchElementException;
    E pop() throws NoSuchElementException;
    void push(E data);
}
