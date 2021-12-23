package List;

public class ArrayList<E> {
    private E[] elements;
    private int size = 0;
    private final int defaultCapacity = 4;

    public ArrayList(){
        elements = (E[])new Object[defaultCapacity];
    }

    public ArrayList(int capacity){
        elements = (E[])new Object[capacity];
    }

    public int size(){ return size;}
    public boolean isEmpty(){ return size==0;}
    private int maxIdx(){ return size-1; }

    public void add(E data){
        if(size>=elements.length){
            resize();
        }

        elements[size++] = data;
    }

    public void add(E data, int idx){
        validIdx(idx);

        if(size>=elements.length){
            resize();
        }

        shiftRight(idx);
        elements[idx] = data;
        size++;
    }

    public E get(int idx){
        validIdx(idx);

        return elements[idx];
    }

    public void set(E data, int idx) {
        validIdx(idx);

        elements[idx] = data;
    }

    public void remove(int idx) {
        validIdx(idx);
        shiftLeft(idx);
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

    private void validIdx(int idx) {
        if(idx<0 || idx>maxIdx()){
            throw new IndexOutOfBoundsException();
        }
    }

    private void resize() {
        E[] tmp = (E[])new Object[elements.length*2];
        System.arraycopy(elements, 0, tmp, 0, elements.length);
        elements = tmp;
    }

}
