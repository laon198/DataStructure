package tree;

import common.Position;

abstract public class AbstractTree<E> implements Tree<E> {
    private int size=0;

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

    @Override
    public int getHeight(){
        return getHeight(getRoot());
    }

    private int getHeight(Position<E> pos){
        int height = 0;

        for(Position<E> child : getChildren(getRoot())){
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
