package map.searchTree;

import common.Entry;
import common.Position;
import map.AbstractSortedMap;

import java.util.ArrayList;
import java.util.Comparator;

public class TreeMap<K,V> extends AbstractSortedMap<K,V> {
    protected BalanceableTree<K,V> tree = new BalanceableTree<>();

    public TreeMap(){
        super();
    }

    public TreeMap(Comparator<K> comp){
        super(comp);
    }

    protected void balanceAfterPut(Position<Entry<K,V>> pos){}
    protected void balanceAfterRemove(Position<Entry<K,V>> pos){}

    @Override
    public int size() {
        return tree.size();
    }

    @Override
    public void put(K key, V value) {
        if(isEmpty()){
            tree.addRoot(new MapEntry<>(key, value));
        }

        Position<Entry<K,V>> pos = findPos(key);
        int compValue = comp.compare(key, pos.getData().getKey());

        if(compValue==0){
            MapEntry<K,V> entry = (MapEntry<K, V>) pos.getData();
            entry.setValue(value);
        }else{
            MapEntry<K,V> newest = new MapEntry<>(key, value);

            if(compValue>0){
                tree.addRight(pos, newest);
                pos = tree.getRight(pos);
            }else if(compValue<0){
                tree.addLeft(pos, newest);
                pos = tree.getLeft(pos);
            }
        }

        balanceAfterPut(tree.getParent(pos));
    }

    @Override
    public V get(K key) throws IllegalArgumentException {
        if(isEmpty()){
            throw new IllegalStateException("tree is empty");
        }

        Position<Entry<K,V>> pos = findPos(key);

        if (key.equals(pos.getData().getKey())) {
            return pos.getData().getValue();
        }else{
            throw new IllegalArgumentException("No matching key");
        }
    }

    @Override
    public void set(K key, V value) {
        if(isEmpty()){
            throw new IllegalStateException("tree is empty");
        }

        Position<Entry<K,V>> pos = findPos(key);

        if (key.equals(pos.getData().getKey())) {
            ((MapEntry<K,V>)pos.getData()).setValue(value);
            return;
        }else{
            throw new IllegalArgumentException("No matching key");
        }
    }

    @Override
    public void remove(K key) {
        if(isEmpty()){
            throw new IllegalStateException("tree is empty");
        }

        Position<Entry<K,V>> pos = findPos(key);
        Position<Entry<K,V>> parent;

        if (key.equals(pos.getData().getKey())) {
            if(tree.getChildrenNum(pos)==2){
                Position<Entry<K,V>> target = findMax(pos);
                parent = tree.getParent(target);
                tree.set(pos, target.getData());
                tree.remove(target);
            }else{
                parent = tree.getParent(pos);
                tree.remove(pos);
            }
        }else{
            throw new IllegalArgumentException("No matching key");
        }

        balanceAfterRemove(parent);
    }

    private Position<Entry<K,V>> findMax(Position<Entry<K,V>> pos){
        Position<Entry<K,V>> right = tree.getRight(pos);

        if(right==null){
            return pos;
        }

        return findMax(right);
    }

    private Position<Entry<K,V>> findPos(K key){
        if(isEmpty()) return null;

        return findPos(tree.getRoot(), key);
    }

    private Position<Entry<K,V>> findPos(Position<Entry<K,V>> pos, K key){
        Entry<K,V> entry = pos.getData();
        K curKey = entry.getKey();
        int compValue = comp.compare(key,curKey);

        if(compValue==0){
            return pos;
        }else if(compValue>0){
            if(tree.getRight(pos)==null){
                return pos;
            }else{
                return findPos(tree.getRight(pos), key);
            }
        }else if(compValue<0){
            if(tree.getLeft(pos)==null){
                return pos;
            }else{
                return findPos(tree.getLeft(pos), key);
            }
        }

        return null;
    }

    @Override
    public Iterable<Entry<K, V>> entrySet() {
        ArrayList<Entry<K,V>> list = new ArrayList<>();

        for(Position<Entry<K,V>> pos : tree.inorder()){
            list.add(pos.getData());
        }

        return list ;
    }
}
