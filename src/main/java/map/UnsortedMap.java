package map;

import common.Entry;
import common.Position;
import list.DoublyPositionalList;

import java.util.Iterator;

public class UnsortedMap<K,V> extends AbstractMap<K,V>{
    private final DoublyPositionalList<Entry<K,V>> list;

    public UnsortedMap() {
        this.list = new DoublyPositionalList<>();
    }

    public int size(){ return list.size();}

    public void put(K key, V value){
        Position<Entry<K,V>> pos = findPosByKey(key);

        if(pos==null){
            Entry<K,V> newest = new MapEntry<>(key, value);
            list.add(newest);
        }else{
            ((MapEntry<K,V>)pos.getData()).setValue(value);
        }
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

        if(list.isEmpty()){
            return null;
        }

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

    private class EntryIterable implements Iterable<Entry<K,V>>{
        @Override
        public Iterator<Entry<K, V>> iterator() {
            return list.iterator();
        }
    }

    private class KeyIterable implements Iterable<K>{
        @Override
        public Iterator<K> iterator() {
            return new KeyIterator();
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