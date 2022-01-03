package stack;

import list.List;
import list.ArrayList;

import java.util.NoSuchElementException;

public class ArrayStack<E> implements Stack<E> {
    private List<E> list;

    public ArrayStack(){
        list = new ArrayList<E>();
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

        return list.get(list.size()-1);
    }

    @Override
    public void push(E data) {
        list.add(data);
    }

    @Override
    public E pop() throws NoSuchElementException {
        checkEmpty();

        int lastIndex = list.size()-1;
        E data = list.get(lastIndex);
        list.remove(lastIndex);

        return data;
    }

    private void checkEmpty() throws NoSuchElementException{
        if(list.isEmpty()){
            throw new NoSuchElementException("stack is empty");
        }
    }
}