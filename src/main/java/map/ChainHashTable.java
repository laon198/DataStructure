package map;

public class ChainHashTable<K,V> extends AbstractMap<K,V>{
    private static final int defaultCapacity = 16;
    private Map<K,V>[] table;
    private int size;

    public ChainHashTable(){
        this(defaultCapacity);
    }

    public ChainHashTable(int capacity){
        table = new Map[capacity];
        size = 0;

        for(int i=0; i<table.length; i++){
            table[i] = new UnsortedMap<>();
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void put(K key, V value) {
        int hashValue = hashing(key);
        table[hashValue].put(key,value);
        size++;
    }

    @Override
    public V get(K key) {
        int hashValue = hashing(key);
        return table[hashValue].get(key);
    }

    @Override
    public void set(K key, V value) {
        int hashValue = hashing(key);
        table[hashValue].set(key, value);
    }

    @Override
    public void remove(K key) {
        int hashValue = hashing(key);
        table[hashValue].remove(key);
        size--;
    }

    private int hashing(K key){
        return key.hashCode()%table.length;
    }

}