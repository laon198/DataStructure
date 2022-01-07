package priorityQueue;

import common.Entry;

import java.util.Comparator;

abstract public class AbstractPriorityQueue<K,V> implements PriorityQueue<K,V> {
    protected static class PQEntry<K,V> implements Entry<K,V>{
        private K key;
        private V value;

        protected PQEntry(K key, V value){
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

    protected Comparator<K> comp;

    protected AbstractPriorityQueue(){
        comp = new DefaultComparator<>();
    }

    protected AbstractPriorityQueue(Comparator<K> comparator){
        comp = comparator;
    }

    @Override
    public boolean isEmpty(){ return size()==0; }

    protected int compare(Entry<K,V> lhs, Entry<K,V> rhs) throws ClassCastException{
        return comp.compare(lhs.getKey(), rhs.getKey());
    }

    private boolean checkKey(K key){
        try{
            return 0==comp.compare(key, key);
        }catch(ClassCastException e){
            throw new IllegalArgumentException("key is not compatible");
        }
    }
}
