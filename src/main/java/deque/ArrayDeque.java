package deque;

import java.util.NoSuchElementException;

public class ArrayDeque<E> implements Deque<E> {
    private final static int defaultCapacity = 4;
    private E[] elements;
    private int front;
    private int rear;
    private int size;

    public ArrayDeque(){
        this(defaultCapacity);
    }

    public ArrayDeque(int capacity){
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
    public void addFirst(E data) {
        checkSize();
        elements[front] = data;
        front = (front-1+elements.length)%elements.length;
        size++;
    }

    @Override
    public void addLast(E data) {
        checkSize();
        rear = (rear+1)%elements.length;
        elements[rear] = data;
        size++;
    }

    @Override
    public E removeFirst() throws NoSuchElementException {
        E data = getFirst();
        elements[(front+1)%elements.length] = null;
        front = (front+1)%elements.length;
        size--;
        return data;
    }

    @Override
    public E removeLast() {
        E data = getLast();
        elements[rear] = null;
        rear = (rear-1+elements.length)%elements.length;
        size--;
        return data;
    }

    @Override
    public E getFirst() {
        checkEmpty();
        E data = elements[(front+1)% elements.length];
        return data;
    }

    @Override
    public E getLast() {
        checkEmpty();
        return elements[rear];
    }

    private void checkSize() {
        if(size==elements.length-1){
            resize();
        }
    }

    private void resize() {
        E[] tmp = (E[])new Object[elements.length*2];

        for(int srcCursor=front, targetCursor=0; targetCursor<elements.length;
                    srcCursor=(srcCursor+1)%elements.length, targetCursor++){
            tmp[targetCursor] = elements[srcCursor];
        }

        front = 0;
        rear = size;
        elements = tmp;
    }

    private void checkEmpty() throws NoSuchElementException{
        if(isEmpty()){
            throw new NoSuchElementException("deque is empty");
        }
    }
}
