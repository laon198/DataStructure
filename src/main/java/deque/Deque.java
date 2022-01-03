package deque;

public interface Deque<E> {
    int size();
    boolean isEmpty();
    void addFirst(E data);
    void addLast(E data);
    E removeFirst();
    E removeLast();
    E getFirst();
    E getLast();
}
