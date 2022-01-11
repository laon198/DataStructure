package priorityQueue;

import common.Entry;
import common.Position;
import list.DoublyPositionalList;

import java.util.Comparator;

public class LinkedPriorityQueue<K,V> extends AbstractPriorityQueue<K,V> {
    private DoublyPositionalList<Entry<K,V>> list = new DoublyPositionalList<>();

    public LinkedPriorityQueue(){}

    public LinkedPriorityQueue(Comparator<K> comparator){
        super(comparator);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(K key, V value) {
        checkKey(key);

        Entry<K,V> entry = new PQEntry<>(key, value);

        if(isEmpty()){
            list.add(entry);
            return;
        }

        Position<Entry<K,V>> nextPos = null;

        for(Position<Entry<K,V>> pos : list.positions()){
            if(compare(pos.getData(), entry)>0){
                nextPos = pos;
                list.addPrev(nextPos, entry);
                return;
            }
        }

        list.add(entry);
    }

    @Override
    public Entry<K, V> getMin() {
        return list.getFirst().getData();
    }

    @Override
    public Entry<K, V> removeMin() {
        Position<Entry<K,V>> minPos = list.getFirst();
        list.remove(minPos);
        return minPos.getData();
    }
}
