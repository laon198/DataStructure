package map;

import common.Entry;
import list.ArrayList;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class ProbeHashTable<K,V> extends AbstractHashTable<K,V>{
    private MapEntry<K,V>[] table;
    private final MapEntry<K,V> DEFUNCT = new MapEntry<>(null, null);

    public ProbeHashTable(){
        super();
    }

    public ProbeHashTable(int capacity, int prime){
        super(capacity, prime);
    }

    public ProbeHashTable(int capacity){
        super(capacity);
    }

    @Override
    protected void createTable() {
        table = (MapEntry<K, V>[]) new MapEntry[capacity];
    }

    @Override
    public void put(K key, V value) {
        if(size>=capacity){
            resize();
        }

        int index = findIndex(key);

        if(table[index]!=null){
            table[index].setValue(value);
            return;
        }

        table[index] = new MapEntry<>(key, value);
        size++;
    }

    private int findIndex(K key) throws IllegalStateException{
        int index = hashing(key);

        int i=0;
        while(table[index]!=null){
            if(i>capacity){
                throw new IllegalStateException("map is full!");
            }

            if(table[index]!=DEFUNCT && table[index].getKey().equals(key)){
                return index;
            }

            i++;
            index = (index+i)%capacity;
        }

        return index;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        int index = findIndex(key);
        MapEntry<K,V> entry = table[index];

        if(entry==null){
            throw new IllegalArgumentException("No matching key");
        }

        return entry.getValue();
    }

    @Override
    public void set(K key, V value) {
        int index = findIndex(key);
        MapEntry<K,V> entry = table[index];

        if(entry==null){
            throw new IllegalArgumentException("No matching key");
        }

        entry.setValue(value);
    }

    @Override
    public void remove(K key) {
        int index = findIndex(key);
        MapEntry<K,V> entry = table[index];

        if(entry==null){
            throw new IllegalArgumentException("No matching key");
        }

        table[index] = DEFUNCT;
        size--;
    }

    private void resize() {
        ArrayList<Entry<K,V>> list = (ArrayList<Entry<K,V>>) entrySet();

        capacity = capacity*2-1;
        size = 0;
        createTable();

        for(Entry<K,V> entry : list){
            put(entry.getKey(), entry.getValue());
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> list = new ArrayList<>();

        for(int i=0; i<capacity; i++){
            if(table[i]!=DEFUNCT && table[i]!=null){
                list.add(table[i]);
            }
        }

        return list;
    }
}
