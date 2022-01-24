package map.searchTree;

import common.Entry;
import common.Position;
import tree.LinkedBinaryTree;

public class BalanceableTree<K,V> extends LinkedBinaryTree<Entry<K,V>> {
    public void rotateLeft(Position<Entry<K,V>> pos){
        Node<Entry<K,V>> target = (Node<Entry<K,V>>) pos;
        Node<Entry<K,V>> parent = target.getParent();
        Node<Entry<K,V>> right = target.getRight();
        Node<Entry<K,V>> rightLeft = right.getLeft();

        if(right==null){
            return;
        }

        target.setParent(right);
        target.setRight(rightLeft);
        rightLeft.setParent(target);
        right.setLeft(target);
        right.setParent(parent);

        if(parent.getRight()==target){
            parent.setRight(right);
        }else if(parent.getLeft()==target){
            parent.setLeft(right);
        }
    }

    public void rotateRight(Position<Entry<K,V>> pos){
        Node<Entry<K,V>> target = (Node<Entry<K,V>>) pos;
        Node<Entry<K,V>> parent = target.getParent();
        Node<Entry<K,V>> left = target.getLeft();
        Node<Entry<K,V>> leftRight = left.getRight();

        if(left==null){
            return;
        }

        target.setParent(left);
        target.setLeft(leftRight);
        leftRight.setParent(target);
        left.setRight(target);
        left.setParent(parent);

        if(parent.getRight()==target){
            parent.setRight(left);
        }else if(parent.getLeft()==target){
            parent.setLeft(left);
        }
    }

}
