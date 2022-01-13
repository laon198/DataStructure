package map;

import common.Entry;

abstract public class AbstractMap<K,V> implements Map<K,V> {
    protected static class MapEntry<K,V> implements Entry<K,V> {
        private K key;
        private V value;

        protected MapEntry(K key, V value) {
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

    public boolean isEmpty(){ return size()==0;}

}
