package List;

public interface List<E> extends Iterable<E> {
    void add(E data);
    void add(E data, int idx);
    E get(int idx);
    void set(E data, int idx);
    void remove(int idx);
}
