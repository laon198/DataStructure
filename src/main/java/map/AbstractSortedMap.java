package map;

import common.DefaultComparator;

import java.util.Comparator;

public abstract class AbstractSortedMap<K,V> extends AbstractMap<K,V> {
    protected Comparator<K> comp;

    protected AbstractSortedMap(){
        comp = new DefaultComparator<>();
    }

    protected AbstractSortedMap(Comparator<K> comp){
        this.comp = comp;
    }
}
