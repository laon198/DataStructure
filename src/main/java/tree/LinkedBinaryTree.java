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

    public LinkedBinaryTree(){}

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

}
