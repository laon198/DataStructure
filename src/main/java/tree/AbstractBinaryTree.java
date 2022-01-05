package tree;

import common.Position;
import list.ArrayList;
import list.List;

abstract public class AbstractBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E>{
    @Override
    public Iterable<Position<E>> getChildren(Position<E> pos) {
        List<Position<E>> list = new ArrayList<>();

        if(getLeft(pos)!=null){
            list.add(getLeft(pos));
        }

        if(getRight(pos)!=null){
            list.add(getRight(pos));
        }

        return list;
    }

    @Override
    public int getChildrenNum(Position<E> pos) {
        int num = 0;

        if(getLeft(pos)!=null){
            num++;
        }

        if(getRight(pos)!=null){
            num++;
        }

        return num;
    }

    @Override
    public Position<E> getSibling(Position<E> pos) throws IllegalArgumentException {
        Position<E> parent = getParent(pos);

        if(getRight(parent)==pos){
            return getLeft(parent);
        }else if(getLeft(parent)==pos){
            return getRight(parent);
        }

        return null;
    }
}
