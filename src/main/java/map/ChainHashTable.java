package map;

public class ChainHashTable<K,V> extends AbstractHashTable<K,V>{
    private Map<K,V>[] table;

    public ChainHashTable(){
        super();
    }

    public ChainHashTable(int capacity){
        super(capacity);
    }

    public ChainHashTable(int capacity, int prime){
        super(capacity, prime);
    }

    @Override
    public void put(K key, V value) {
        int hashValue = hashing(key);
        Map<K,V> bucket = table[hashValue];
        int oldSize = bucket.size();
        bucket.put(key,value);
        size += (bucket.size()-oldSize);
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

    @Override
    protected void createTable() {
        table = new Map[capacity];
        for(int i=0; i<capacity; i++){
            table[i] = new UnsortedMap<>();
        }
    }


}