package stack;

import list.SinglyLinkedList;
import list.List;
import java.util.NoSuchElementException;

public class LinkedStack<E> implements Stack<E> {
    private List<E> list;

    public LinkedStack(){
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
    public E top() throws NoSuchElementException {
        checkEmpty();
        return list.get(0);
    }

    @Override
    public E pop() throws NoSuchElementException {
        checkEmpty();

        E data = list.get(0);
        list.remove(0);
        return data;
    }

    @Override
    public void push(E data) {
        list.add(data, 0);
    }

    private void checkEmpty(){
        if(list.isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
    }
}
