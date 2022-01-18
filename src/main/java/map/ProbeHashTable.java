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
        if(size>capacity/2){
            resize();
        }

        int index = findIndex(key);

        if(index>=0){
            table[index].setValue(value);
            return;
        }

        table[-(index+1)] = new MapEntry<>(key, value);
        size++;
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        int index = findIndex(key);

        if(index<0){
            throw new IllegalArgumentException("No matching key");
        }

        return table[index].getValue();
    }

    @Override
    public void set(K key, V value) {
        int index = findIndex(key);

        if(index<0){
            throw new IllegalArgumentException("No matching key");
        }

        table[index].setValue(value);
    }

    @Override
    public void remove(K key) {
        int index = findIndex(key);

        if(index<0){
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

    private boolean isAvailable(int index){
        return table[index]==DEFUNCT || table[index]==null;
    }

    private int findIndex(K key) throws IllegalStateException{
        int index = hashing(key);
        int beginIndex = index;
        int avail = -1;

        do{
            if(isAvailable(index)){
                if(avail==-1){
                    avail = index;
                }
                if(table[index]==null){
                    break;
                }
            }else if(table[index].getKey().equals(key)){
                return index;
            }
            index = (index+1)%capacity;
        }while(index!=beginIndex);

        return -(avail+1);
    }

}
