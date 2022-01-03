package queue;

import list.List;
import list.SinglyLinkedList;

import java.util.NoSuchElementException;

public class LinkedQueue<E> implements Queue<E> {
    private List<E> list;

    public LinkedQueue(){
        list = new SinglyLinkedList<E>();
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public void enqueue(E data) {
        list.add(data);
    }

    @Override
    public E getFirst() {
        checkEmpty();
        return list.get(0);
    }

    @Override
    public E dequeue() {
        E data = getFirst();
        list.remove(0);
        return data;
    }

    private void checkEmpty() {
        if(list.isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
    }
}
