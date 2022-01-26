package tree;

import common.Position;

abstract public class AbstractTree<E> implements Tree<E> {
    protected int size=0;

    @Override
    public int size(){
        return size;
    }

    @Override
    public boolean isEmpty(){
        return size==0;
    }

    @Override
    public boolean isRoot(Position<E> pos){
        return pos==getRoot();
    }

    public int getHeight(Position<E> pos){
        if(pos==null){
            return 0;
        }

        int height = 1;

        for(Position<E> child : getChildren(pos)){
            height = Math.max(height, 1+getHeight(child));
        }

        return height;
    }

    @Override
    public int getDepth(Position<E> pos){
        int depth = 0;

        if(isRoot(pos)){
            return 0;
        }

        depth = 1 + getDepth(pos);

        return depth;
    }

    @Override
    public boolean isExternal(Position<E> pos){
        return getChildrenNum(pos)==0;
    }

    @Override
    public boolean isInternal(Position<E> pos){
        return getChildrenNum(pos)>0;
    }
}
