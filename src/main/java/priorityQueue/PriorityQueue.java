package priorityQueue;

import common.Entry;

public interface PriorityQueue<K, V>{
    int size();
    boolean isEmpty();
    void add(K key, V value);
    Entry<K,V> getMin();
    Entry<K,V> removeMin();
}
