package map.searchTree;

import common.Entry;
import common.Position;

public class AVLTree<K,V> extends TreeMap<K,V> {

    @Override
    protected void balanceAfterPut(Position<Entry<K, V>> pos) { //삽입한 부모노드
        reBalance(pos);
    }

    @Override
    protected void balanceAfterRemove(Position<Entry<K, V>> pos) {//삭제한 부모노드
        reBalance(pos);
    }

    private void reBalance(Position<Entry<K,V>> pos){
        Position<Entry<K,V>> parent;

        for(Position<Entry<K,V>> curPos=pos;
            curPos!=null; curPos=parent){
            parent = tree.getParent(curPos);

            Position<Entry<K,V>> left = tree.getLeft(curPos);
            Position<Entry<K,V>> right = tree.getRight(curPos);
            int leftHeight = tree.getHeight(left);
            int rightHeight = tree.getHeight(right);

            if(Math.abs(leftHeight-rightHeight)>1){
                tree.restruct(getTallerChild(getTallerChild(curPos)));
            }
        }
    }

    private Position<Entry<K,V>> getTallerChild(Position<Entry<K,V>> pos){
        Position<Entry<K,V>> left = tree.getLeft(pos);
        Position<Entry<K,V>> right = tree.getRight(pos);
        int leftHeight = tree.getHeight(left);
        int rightHeight = tree.getHeight(right);

        if(leftHeight>rightHeight){
            return left;
        }else{
            return right;
        }
    }
}
