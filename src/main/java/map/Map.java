package map;

public interface Map<K,V> {
    int size();
    boolean isEmpty();
    void put(K key, V value);
    V get(K key);
    void set(K key, V value);
    void remove(K key);
}
