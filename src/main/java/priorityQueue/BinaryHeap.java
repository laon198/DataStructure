package priorityQueue;

import common.Entry;
import list.ArrayList;

import java.util.Comparator;

public class BinaryHeap<K,V> extends AbstractPriorityQueue<K,V> {
    private final ArrayList<Entry<K,V>> list;

    public BinaryHeap(){
        list = new ArrayList<>();
        list.add(new PQEntry<>(null, null));
    }

    public BinaryHeap(Comparator<K> comparator){
        super(comparator);
        list = new ArrayList<>();
        list.add(new PQEntry<>(null, null));
    }

    @Override
    public int size() {
        return list.size()-1;
    }

    @Override
    public void add(K key, V value) {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);

        if(isEmpty()){
            list.add(newest);
        }else{
            list.add(newest);
            upheap();
        }
    }

    @Override
    public Entry<K, V> getMin() {
        return list.get(1);
    }

    @Override
    public Entry<K, V> removeMin() {
        Entry<K,V> min = list.get(1);
        Entry<K,V> last = list.get(list.size()-1);
        list.set(last, 1);
        list.remove(list.size()-1);
        downheap(1);
        return min;
    }

    private void downheap(int beginIdx){
        int curIndex = beginIdx;
        int minChildIndex = getMinChildIndex(curIndex);

        while(curIndex<=(list.size()-1)/2){
            Entry<K,V> curEntry = list.get(curIndex);
            Entry<K,V> childEntry = list.get(minChildIndex);

            if(comp.compare(childEntry.getKey(), curEntry.getKey())<0){
                list.set(childEntry, curIndex);
                list.set(curEntry, minChildIndex);
            }else{
                break;
            }

            curIndex = minChildIndex;
            minChildIndex = getMinChildIndex(curIndex);
        }
    }

    private int getMinChildIndex(int parentIdx){
        int leftChildIndex;
        int rightChildIndex;

        if(parentIdx*2<=list.size()-1){
            leftChildIndex=parentIdx*2;
            if(parentIdx*2+1<=list.size()-1){
                rightChildIndex=parentIdx*2+1;
            }else{
                return leftChildIndex;
            }
        }else{
            return 0;
        }

        if(comp.compare(
                list.get(leftChildIndex).getKey(),
                    list.get(rightChildIndex).getKey())<0){
            return leftChildIndex;
        }else{
            return rightChildIndex;
        }
    }

    private void upheap(){
        int curIndex = list.size()-1;
        Entry<K,V> curEntry = list.get(curIndex);

        for(Entry<K,V> parent=list.get(curIndex/2);
                curIndex!=1 && comp.compare(curEntry.getKey(), parent.getKey())<0;
                    curIndex=curIndex/2, parent=list.get(curIndex/2)){
            list.set(parent, curIndex);
        }

        list.set(curEntry, curIndex);
    }
}
