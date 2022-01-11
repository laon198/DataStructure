package list;

public class ArrayList<E> extends AbstractList<E> {
    private E[] elements;
    private final int defaultCapacity = 4;

    public ArrayList(){
        elements = (E[])new Object[defaultCapacity];
    }

    public ArrayList(int capacity){
        elements = (E[])new Object[capacity];
    }

    private int maxIdx(){ return size-1; }

    public void add(E data){
        if(size>=elements.length){
            resize();
        }

        elements[size++] = data;
    }

    public void add(E data, int idx){
        validAddingIndex(idx);

        if(size>=elements.length){
            resize();
        }

        shiftRight(idx);
        elements[idx] = data;
        size++;
    }

    public E get(int idx){
        validExistingIndex(idx);

        return elements[idx];
    }

    public void set(E data, int idx) {
        validExistingIndex(idx);

        elements[idx] = data;
    }

    public void remove(int idx) {
        validExistingIndex(idx);
        shiftLeft(idx);
        elements[idx]=null;
        size--;
    }

    private void shiftLeft(int beginIdx) {
        for(int i=beginIdx; i<maxIdx(); i++){
            elements[i] = elements[i+1];
        }
    }

    private void shiftRight(int beginIdx) {
        for(int i=maxIdx(); i>=beginIdx; i--){
            elements[i+1] = elements[i];
        }
    }

    private void resize() {
        E[] tmp = (E[])new Object[elements.length*2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }
}
