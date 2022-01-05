package tree;

import common.Position;

public class LinkedBinaryTree<E> extends AbstractBinaryTree<E> {
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
            return data;
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

    public LinkedBinaryTree(){}

    public void addRoot(E data) throws IllegalStateException{
        if(!isEmpty()){
            throw new IllegalStateException("root is already exist");
        }

        Node<E> newNode = new Node<>(data, null, null, null);
        root = newNode;
        size++;
    }

    public void addLeft(Position<E> pos, E data){
        Node<E> parent = (Node<E>)pos;

        Node<E> newNode = new Node<>(data, parent, null, null);

        Node<E> leftNode = parent.getLeft();
        if(leftNode!=null){
            newNode.setLeft(leftNode);
            leftNode.setParent(newNode);
        }

        parent.setLeft(newNode);

        size++;
    }

    public void addRight(Position<E> pos, E data){
        Node<E> parent = (Node<E>)pos;

        Node<E> newNode = new Node<>(data, parent, null, null);

        Node<E> rightNode = parent.getRight();
        if(rightNode!=null){
            newNode.setRight(rightNode);
            rightNode.setParent(newNode);
        }

        parent.setRight(newNode);

        size++;
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
    public Position<E> getParent(Position<E> pos) throws IllegalArgumentException {
        if(isRoot(pos)){
            throw new IllegalArgumentException("position is root");
        }

        Node<E> node = (Node<E>)pos;
        return node.getParent();
    }

    public void set(Position<E> pos, E data){
        Node<E> node = (Node<E>)pos;
        node.setData(data);
    }

    public void remove(Position<E> pos) throws IllegalStateException{
        Node<E> node = (Node<E>) pos;
        Node<E> parent = node.getParent();

        int childNum = getChildrenNum(pos);
        if(childNum==2){
            throw new IllegalStateException("this node has two children");
        }

        if(isRoot(pos)){
            root = node;
            return;
        }

        Node<E> child = null;

        if(node.getLeft()!=null){
            child = node.getLeft();
        }else if(node.getRight()!=null){
            child = node.getRight();
        }

        if(parent.getLeft()==node){
            parent.setLeft(child);
        }else if(parent.getRight()==node){
            parent.setRight(child);
        }

        if(child!=null){
            child.setParent(parent);
        }

        size--;
    }
}
