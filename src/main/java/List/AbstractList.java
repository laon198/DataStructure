package List;

import java.util.Iterator;
import java.util.NoSuchElementException;

abstract public class AbstractList<E> implements List<E> {
    protected int size = 0;

    public int size(){ return size;}
    public boolean isEmpty(){ return size==0;}

    protected void validAddingIndex(int idx){
        if(idx<0 || idx>size){
            throw new IndexOutOfBoundsException();
        }
    }

    protected void validExistingIndex(int idx) {
        if(idx<0 || idx>size-1){
            throw new IndexOutOfBoundsException();
        }
    }

    private static class ListIterator<E> implements Iterator<E>{
        private E[] elements;
        private int size;
        private int cursor;

        private ListIterator(E[] elements){
            this.elements = elements;
            size = elements.length;
            cursor = 0;
        }

        @Override
        public boolean hasNext() {
            if(size==cursor){
                return false;
            }
            return true;
        }

        @Override
        public E next() {
            if(!hasNext()){
                throw new NoSuchElementException("No next element");
            }

            return elements[cursor++];
        }
    }

    public Iterator<E> iterator(){
        E[] tmp = (E[])new Object[size()];

        for(int i=0; i<size(); i++){
            tmp[i] = get(i);
        }

        return new ListIterator<E>(tmp);
    }
}
