package map.searchTree;

import common.Entry;
import common.Position;
import tree.LinkedBinaryTree;

public class BalanceableTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {
    public void restruct(Position<Entry<K,V>> childPos){
        Node<Entry<K,V>> child = (Node<Entry<K,V>>) childPos;
        Node<Entry<K,V>> target = child.getParent();
        Node<Entry<K,V>> parent = target.getParent();

        if((parent.getLeft()==target) == (target.getLeft()==child)){
            rotate(target);
        }else{
            rotate(childPos);
            rotate(childPos);
        }

    }

    public void rotate(Position<Entry<K,V>> childPos){
        if(childPos==null || childPos==getRoot()){ //자기가 루트일때, 부모가 루트일때 생각 필요
            return;
        }

        Node<Entry<K,V>> child = (Node<Entry<K,V>>) childPos;
        Node<Entry<K,V>> target = child.getParent();
        Node<Entry<K,V>> parent = target.getParent();

        chain(parent, child, parent.getLeft()==target);

        if(target.getLeft()==child){
            chain(target, child.getRight(), true);
            chain(child, target, false);
        }else{
            chain(target, child.getLeft(), false);
            chain(child, target, true);
        }
    }

    private void chain(
            Node<Entry<K,V>> parent,
            Node<Entry<K,V>> child,
            boolean isLeftChild
    ){

        if(parent!=null){
            if(isLeftChild){
                parent.setLeft(child);
            }else{
                parent.setRight(child);
            }
        }

        if(child!=null){
            child.setParent(parent);
        }
    }

}
