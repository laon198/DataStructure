package deque;

import list.DoublyPositionalList;
import list.Position;

public class LinkedDeque<E> implements Deque<E> {
    private DoublyPositionalList<E> list;

    public LinkedDeque(){
        list = new DoublyPositionalList<>();
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
    public void addFirst(E data) {
        if(isEmpty()){
            list.add(data);
        }else{
            Position<E> first = list.getFirst();
            list.addPrev(first, data);
        }
    }

    @Override
    public void addLast(E data) {
        list.add(data);
    }

    @Override
    public E removeFirst() {
        Position<E> first = list.getFirst();
        E data = first.getData();
        list.remove(first);
        return data;
    }

    @Override
    public E removeLast() {
        Position<E> last = list.getLast();
        E data = last.getData();
        list.remove(last);
        return data;
    }

    @Override
    public E getFirst() {
        return list.getFirst().getData();
    }

    @Override
    public E getLast() {
        return list.getLast().getData();
    }

}
