package tree;

import common.Position;
import list.ArrayList;
import list.List;

public class LinkedBinaryTree<E> extends AbstractTree<E> implements BinaryTree<E> {
    private static class Node<E> implements Position<E>{
        private E data;
        private Node<E> parent;
        private Node<E> left;
        private Node<E> right;

        private Node(E data, Node<E> parent, Node<E> left, Node<E> right) {
            this.data = data;
            this.parent = parent;
            this.left = left;
            this.right = right;
        }

        @Override
        public E getData() {
            return null;
        }

        public Node<E> getParent() {
            return parent;
        }

        public Node<E> getLeft() {
            return left;
        }

        public Node<E> getRight() {
            return right;
        }

        public void setData(E data) {
            this.data = data;
        }

        public void setParent(Node<E> parent) {
            this.parent = parent;
        }

        public void setLeft(Node<E> left) {
            this.left = left;
        }

        public void setRight(Node<E> right) {
            this.right = right;
        }
    }

    private Node<E> root;

    public LinkedBinaryTree(){
        root = null;
    }

    @Override
    public boolean isRoot(Position<E> pos) {
        return pos==root;
    }

    @Override
    public Position<E> getRoot() {
        return root;
    }

    @Override
    public Position<E> getLeft(Position<E> pos) {
        Node<E> node = (Node<E>) pos;
        return node.getLeft();
    }

    @Override
    public Position<E> getRight(Position<E> pos) {
        Node<E> node = (Node<E>) pos;
        return node.getRight();
    }

    @Override
    public Position<E> getSibling(Position<E> pos) throws IllegalArgumentException{
        Node<E> node = (Node<E>)pos;
        Node<E> parent = (Node<E>) getParent(pos);

        if(parent.getRight()==node){
            return parent.getLeft();
        }else if(parent.getLeft()==node){
            return parent.getRight();
        }

        return null;
    }

    @Override
    public Position<E> getParent(Position<E> pos) throws IllegalArgumentException {
        if(isRoot(pos)){
            throw new IllegalArgumentException("position is root");
        }

        Node<E> node = (Node<E>)pos;
        return node.getParent();
    }

    @Override
    public Iterable<Position<E>> getChildren(Position<E> pos) {
        List<Position<E>> list = new ArrayList<>();

        Node<E> node = (Node<E>) pos;

        if(node.getLeft()!=null){
            list.add(node.getLeft());
        }

        if(node.getRight()!=null){
            list.add(node.getRight());
        }

        return list;
    }

    @Override
    public int getChildrenNum(Position<E> pos) {
        Node<E> node = (Node<E>) pos;
        int num = 0;

        if(node.getLeft()!=null){
            num++;
        }

        if(node.getRight()!=null){
            num++;
        }

        return num;
    }
}
