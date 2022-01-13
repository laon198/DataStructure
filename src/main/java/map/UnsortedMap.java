package map;

import common.Entry;
import common.Position;
import list.DoublyPositionalList;

import java.util.Iterator;

public class UnsortedMap<K,V> implements Iterable<V> {
    private static class MapEntry<K,V> implements Entry<K,V>{
        private K key;
        private V value;

        public MapEntry(K key, V value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public K getKey() {
            return key;
        }

        public void setKey(K key) {
            this.key = key;
        }

        @Override
        public V getValue() {
            return value;
        }

        public void setValue(V value) {
            this.value = value;
        }
    }

    private final DoublyPositionalList<Entry<K,V>> list;

    public UnsortedMap() {
        this.list = new DoublyPositionalList<>();
    }

    public int size(){ return list.size();}
    public boolean isEmpty(){ return list.isEmpty();}

    public void put(K key, V value){
        Entry<K,V> newest = new MapEntry<>(key, value);
        list.add(newest);
    }

    public V get(K key) throws IllegalArgumentException{
        Position<Entry<K,V>> pos = findPosByKey(key);

        checkPos(pos);

        return pos.getData().getValue();
    }

    public void set(K key, V value){
        Position<Entry<K,V>> pos = findPosByKey(key);

        checkPos(pos);

        MapEntry<K,V> mapEntry = (MapEntry<K, V>) pos.getData();
        mapEntry.setValue(value);
    }

    public void remove(K key){
        Position<Entry<K,V>> pos = findPosByKey(key);

        checkPos(pos);

        list.remove(pos);
    }

    private void checkPos(Position<Entry<K,V>> pos){
        if(pos==null){
            throw new IllegalArgumentException("no matching key");
        }
    }

    private Position<Entry<K,V>> findPosByKey(K key){
        Position<Entry<K,V>> targetPos = null;

        for(Position<Entry<K,V>> pos : list.positions()){
            if(key.equals(pos.getData().getKey())){
                targetPos = pos;
                break;
            }
        }

        return targetPos;
    }

    private class ValueIterator implements Iterator<V>{
        private Iterator<Entry<K,V>> iter;

        ValueIterator(){
            iter = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public V next() {
            return iter.next().getValue();
        }
    }

    private class KeyIterator implements Iterator<K>{
        private Iterator<Entry<K,V>> iter;

        KeyIterator(){
            iter = list.iterator();
        }

        @Override
        public boolean hasNext() {
            return iter.hasNext();
        }

        @Override
        public K next() {
            return iter.next().getKey();
        }
    }

    @Override
    public Iterator<V> iterator() {
        return new ValueIterator();
    }

    public Iterator<K> keys(){
        return new KeyIterator();
    }

    public Iterator<Entry<K,V>> entries(){
        return list.iterator();
    }

}