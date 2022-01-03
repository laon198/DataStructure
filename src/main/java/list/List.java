package list;

public interface List<E> extends Iterable<E> {
    int size();
    boolean isEmpty();
    void add(E data);
    void add(E data, int idx);
    E get(int idx);
    void set(E data, int idx);
    void remove(int idx);
}
