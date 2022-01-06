package tree;

import common.Position;
import list.ArrayList;
import list.List;
import queue.LinkedQueue;
import queue.Queue;

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

    @Override
    public Iterable<Position<E>> preorder(){
        List<Position<E>> list = new ArrayList<>();

        fillListByPreorder(list, getRoot());

        return list;
    }

    @Override
    public Iterable<Position<E>> inorder(){
        List<Position<E>> list = new ArrayList<>();

        fillListByInorder(list, getRoot());

        return list;
    }

    @Override
    public Iterable<Position<E>> postorder(){
        List<Position<E>> list = new ArrayList<>();

        fillListByPostorder(list, getRoot());

        return list;
    }

    @Override
    public Iterable<Position<E>> levelorder(){
        List<Position<E>> list = new ArrayList<>();
        Queue<Position<E>> queue = new LinkedQueue<>();

        queue.enqueue(getRoot());

        while(!queue.isEmpty()){
            Position<E> pos = queue.dequeue();
            list.add(pos);

            Position<E> left = getLeft(pos);
            Position<E> right = getRight(pos);

            if(left!=null){
                queue.enqueue(left);
            }

            if(right!=null){
                queue.enqueue(right);
            }
        }

        return list;
    }


    private void fillListByPreorder(List<Position<E>> list, Position<E> pos){
        if(pos==null){
            return;
        }

        list.add(pos);
        fillListByPreorder(list, getLeft(pos));
        fillListByPreorder(list, getRight(pos));
    }

    private void fillListByInorder(List<Position<E>> list, Position<E> pos){
        if(pos==null){
            return;
        }

        fillListByInorder(list, getLeft(pos));
        list.add(pos);
        fillListByInorder(list, getRight(pos));
    }

    private void fillListByPostorder(List<Position<E>> list, Position<E> pos){
        if(pos==null){
            return;
        }

        fillListByPostorder(list, getLeft(pos));
        fillListByPostorder(list, getRight(pos));
        list.add(pos);
    }
}
