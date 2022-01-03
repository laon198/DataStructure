package queue;

import java.util.NoSuchElementException;

public class ArrayQueue<E> implements Queue<E> {
    private E[] elements;
    private int front;
    private int rear;
    private int size;
    private static final int defaultCapacity = 4;

    public ArrayQueue(){
        this(defaultCapacity);
    }

    public ArrayQueue(int capacity){
        elements = (E[])new Object[capacity];
        front = 0;
        rear = 0;
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return size==0;
    }

    @Override
    public void enqueue(E data) {
        if(size==elements.length-1){
            resize();
        }

        rear = (rear+1) % elements.length;
        elements[rear] = data;
        size++;
    }

    @Override
    public E getFirst() throws NoSuchElementException {
        checkEmpty();
        return elements[(front+1)%elements.length];
    }

    @Override
    public E dequeue() throws NoSuchElementException {
        E data = getFirst();
        elements[(front+1)% elements.length] = null;
        front = (front+1)% elements.length;
        size--;
        return data;
    }

    private void checkEmpty() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("queue is empty");
        }
    }

    private void resize() {
        E[] tmp = (E[]) new Object[elements.length*2];

        for(int srcCursor=front, targetCursor=0;
                targetCursor< elements.length;
                    srcCursor=(srcCursor+1)% elements.length, targetCursor++){
            tmp[targetCursor] = elements[srcCursor];
        }

        front = 0;
        rear = size;
        elements = tmp;
    }
}
