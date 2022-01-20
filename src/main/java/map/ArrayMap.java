package map;

import common.DefaultComparator;
import common.Entry;
import list.ArrayList;

import java.util.Comparator;

public class ArrayMap<K,V> extends AbstractMap<K,V> {
    private ArrayList<Entry<K,V>> table;
    private Comparator<K> comp;

    public ArrayMap(){
        table = new ArrayList<>();
        comp = new DefaultComparator<>();
    }

    public ArrayMap(Comparator<K> comp){
        table = new ArrayList<>();
        this.comp = comp;
    }

    public ArrayMap(int capacity){
        table = new ArrayList<>(capacity);
        comp = new DefaultComparator<>();
    }

    public ArrayMap(int capacity, Comparator<K> comp){
        table = new ArrayList<>(capacity);
        this.comp = comp;
    }

    @Override
    public int size() {
        return table.size();
    }

    @Override
    public void put(K key, V value) {
        int index = findIndex(key);
        MapEntry<K,V> newest = new MapEntry<>(key, value);

        if(index>=0){
            table.set(newest, index);
            return;
        }

        table.add(newest, -(index+1));
    }

    @Override
    public V get(K key) {
        int index = findIndex(key);

        if(index<0){
            throw new IllegalArgumentException("no matching key");
        }

        return table.get(index).getValue();
    }

    @Override
    public void set(K key, V value) {
        int index = findIndex(key);

        if(index<0){
            throw new IllegalArgumentException("no matching key");
        }

        ((MapEntry<K,V>)table.get(index)).setValue(value);
    }

    @Override
    public void remove(K key) {
        int index = findIndex(key);

        if(index<0){
            throw new IllegalArgumentException("no matching key");
        }

        table.remove(index);
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        return table;
    }

    private int findIndex(K key){
        int high = size()-1;
        int low = 0;
        int mid = (high+low)/2;

        while(high>=low){
            K midKey = table.get(mid).getKey();
            if(comp.compare(key, midKey)==0){
                return mid;
            }else if(comp.compare(key, midKey)>0){
                low = mid+1;
            }else if(comp.compare(key, midKey)<0){
                high = mid-1;
            }

            mid = (high+low)/2;
        }

        return -(low+1);
    }
}
