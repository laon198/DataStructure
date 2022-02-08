package priorityQueue;

import common.Entry;
import list.ArrayList;

import java.util.Comparator;

public class BinaryHeap<K,V> extends AbstractPriorityQueue<K,V> {
    private ArrayList<Entry<K,V>> list;

    public BinaryHeap(ArrayList<Entry<K,V>> list){
        super();
        this.list = list;
    }

    public BinaryHeap(){
        super();
        list = new ArrayList<>();
    }

    public BinaryHeap(Comparator<K> comparator){
        super(comparator);
    }

    private int getParentIdx(int idx){ return (idx-1)/2;}
    private int getLeftIdx(int idx){ return 2*idx+1;}
    private int getRightIdx(int idx){ return 2*idx+2;}
    private boolean hasLeft(int idx){ return getLeftIdx(idx)<=size()-1;}
    private boolean hasRight(int idx){ return getRightIdx(idx)<=size()-1;}

    private void swap(int i, int j){
        Entry<K,V> tmp = list.get(i);
        list.set(list.get(j), i);
        list.set(tmp, j);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public void add(K key, V value) {
        checkKey(key);
        Entry<K,V> newest = new PQEntry<>(key, value);
        list.add(newest);
        upheap();
    }

    @Override
    public Entry<K, V> getMin() {
        return list.get(0);
    }

    @Override
    public Entry<K, V> removeMin() {
        Entry<K,V> min = list.get(0);
        swap(0, size()-1);
        list.remove(list.size()-1);
        downheap(0);
        return min;
    }

    private void heapify() {
        for(int i=(list.size()-1)/2; i>0; i--){
            downheap(i);
        }
    }

    private void downheap(int beginIdx){
        int curIndex = beginIdx;
        int minChildIndex;

        while(hasLeft(curIndex)){
            minChildIndex = getLeftIdx(curIndex);
            if(hasRight(curIndex) &&
                    comp.compare(
                            list.get(minChildIndex).getKey(),
                                list.get(getRightIdx(curIndex)).getKey())>0
            ){
                minChildIndex = getRightIdx(curIndex);
            }

            swap(curIndex, minChildIndex);
            curIndex = minChildIndex;
        }
    }

    private void upheap(){
        int curIdx = size()-1;
        int parentIdx = getParentIdx(curIdx);

        while(curIdx!=0 &&
                comp.compare(
                        list.get(curIdx).getKey(),
                            list.get(parentIdx).getKey())<0){
            swap(curIdx, parentIdx);
            curIdx = getParentIdx(curIdx);
            parentIdx = getParentIdx(curIdx);
        }
    }
}