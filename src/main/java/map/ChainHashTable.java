package map;

import common.Entry;

import java.util.Iterator;
import java.util.NoSuchElementException;

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
    public V get(K key) throws IllegalArgumentException {
        int hashValue = hashing(key);
        return table[hashValue].get(key);
    }

    @Override
    public void set(K key, V value) throws IllegalArgumentException {
        int hashValue = hashing(key);
        table[hashValue].set(key, value);
    }

    @Override
    public void remove(K key) throws IllegalArgumentException {
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

    private class EntryIterable implements Iterable<Entry<K,V>>{
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return new EntryIterator();
        }
    }

    private class EntryIterator implements Iterator<Entry<K,V>>{
        private int iterSize;
        private int tableIndex;
        private Iterator<Entry<K,V>> curIter;

        EntryIterator(){
            tableIndex = -1;
            iterSize = 0;
        }

        @Override
        public boolean hasNext() {
            if(iterSize>=size()){
                return false;
            }

            return true;
        }

        @Override
        public Entry<K, V> next() {
            if(!hasNext()){
                throw new NoSuchElementException("no next element");
            }

            if(curIter==null || !curIter.hasNext()){
                while(table[++tableIndex].isEmpty()){
                }

                curIter = table[tableIndex].entrySet().iterator();
            }

            iterSize++;
            return curIter.next();
        }
    }

    private class KeyIterable implements Iterable<K>{
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
        }
    }

    private class KeyIterator implements Iterator<K>{
        private Iterator<Entry<K,V>> entryIter;

        KeyIterator(){
            entryIter = new EntryIterator();
        }

        @Override
        public boolean hasNext() {
            return entryIter.hasNext();
        }

        @Override
        public K next() {
            return entryIter.next().getKey();
        }
    }

    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K,V>> entryIter;

        ValueIterator(){
            entryIter = new EntryIterator();
        }

        @Override
        public boolean hasNext() {
            return entryIter.hasNext();
        }

        @Override
        public V next() {
            return entryIter.next().getValue();
        }
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return new EntryIterable();
    }

    public Iterable<K> keySet() {
        return new KeyIterable();
    }

    public Iterator<V> iterator() {
        return new ValueIterator();
    }
}