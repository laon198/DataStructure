package list;

import common.Position;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class DoublyPositionalList<E> implements Iterable<E> {

    private static class Node<E> implements Position<E> {
        private E data;
        private Node<E> prev;
        private Node<E> next;

        Node(E data, Node<E> prev, Node<E> next){
            this.data = data;
            this.prev = prev;
            this.next = next;
        }

        public E getData(){return data;}
        public void setData(E data){this.data = data;}
        public Node<E> getNext(){return next;}
        public void setNext(Node<E> next){this.next = next;}
        public Node<E> getPrev(){return prev;}
        public void setPrev(Node<E> prev){this.prev = prev;}
    }

    private Node<E> head;
    private Node<E> tail;
    private int size;

    public DoublyPositionalList(){
        head = new Node<E>(null, null, null);
        tail = new Node<E>(null, head, null);
        head.setNext(tail);
        size = 0;
    }

    public int size(){return size;}
    public boolean isEmpty(){return size==0;}

    public Position<E> add(E data){
        return addBetween(tail.getPrev(), tail, data);
    }

    public Position<E> addNext(Position<E> position, E data){
        Node<E> prev = (Node<E>) position;
        Node<E> next = prev.getNext();

        return addBetween(prev, next, data);
    }

    public Position<E> addPrev(Position<E> position, E data){
        Node<E> next = (Node<E>) position;
        Node<E> prev = next.getPrev();

        return addBetween(prev, next, data);
    }

    private Position<E> addBetween(Node<E> prev, Node<E> next, E data){
        Node<E> newNode = new Node<E>(data,null, null);
        chain(prev, next, newNode);
        size++;
        return newNode;
    }

    public Position<E> getFirst() throws NoSuchElementException{
        checkEmpty();
        return head.getNext();
    }

    public Position<E> getLast() throws NoSuchElementException{
        checkEmpty();
        return tail.getPrev();
    }

    public Position<E> getNextOf(Position<E> position){
        checkEmpty();

        if(position==tail){
            throw new IllegalArgumentException("position is last position");
        }

        Node<E> next = ((Node<E>) position).getNext();
        return next;
    }

    public Position<E> getPrevOf(Position<E> position){
        checkEmpty();

        if(position==head){
            throw new IllegalArgumentException("position is first position");
        }

        Node<E> prev = ((Node<E>) position).getPrev();
        return prev;
    }

    public void set(Position<E> position, E data){
        Node<E> targetNode = (Node<E>)position;
        targetNode.setData(data);
    }

    public void remove(Position<E> position){
        Node<E> targetNode = (Node<E>) position;
        Node<E> prev = targetNode.getPrev();
        Node<E> next = targetNode.getNext();
        prev.setNext(next);
        next.setPrev(prev);
        size--;
    }

    private void chain(Node<E> prev, Node<E> next, Node<E> newNode){
        newNode.setPrev(prev);
        newNode.setNext(next);
        prev.setNext(newNode);
        next.setPrev(newNode);
    }

    private void checkEmpty() throws NoSuchElementException {
        if(isEmpty()){
            throw new NoSuchElementException("list is empty");
        }
    }

    private class PositionIterable implements Iterable<Position<E>>{
        @Override
        public Iterator<Position<E>> iterator() {
            return new PositionIterator();
        }
    }

    private class PositionIterator implements Iterator<Position<E>>{
        private Position<E> cursor;
        private Position<E> recent;

        private PositionIterator() throws IllegalStateException{
            cursor = DoublyPositionalList.this.getFirst();
        }

        @Override
        public boolean hasNext() {
            return cursor!=DoublyPositionalList.this.tail;
        }

        @Override
        public Position<E> next() {
            if(!hasNext()){
                throw new NoSuchElementException();
            }

            recent = cursor;
            cursor = DoublyPositionalList.this.getNextOf(cursor);
            return recent;
        }
    }

    private class ListIterator implements Iterator<E>{
        private PositionIterator posIter;

        private ListIterator(){
            posIter = new PositionIterator();
        }

        @Override
        public boolean hasNext() {
            return posIter.hasNext();
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException("no next element");
            }

            return posIter.next().getData();
        }
    }

    @Override
    public Iterator<E> iterator() {
        return new ListIterator();
    }

    public Iterable<Position<E>> positions(){
        return new PositionIterable();
    }
}