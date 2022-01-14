package map;

import common.Entry;

public interface Map<K,V> extends Iterable<V> {
    int size();
    boolean isEmpty();
    void put(K key, V value);
    V get(K key);
    void set(K key, V value);
    void remove(K key);
    Iterable<Entry<K,V>> entrySet();
    Iterable<K> keySet();
}
