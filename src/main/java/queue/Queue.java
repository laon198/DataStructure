package queue;

public interface Queue<E> {
    int size();
    boolean isEmpty();
    void enqueue(E data);
    E getFirst();
    E dequeue();
}
