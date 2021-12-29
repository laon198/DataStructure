package List;

import java.util.Iterator;

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


    public Iterator<E> iterator(){return null;}
}
