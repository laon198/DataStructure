package List;

public class SinglyLinkedList<E> extends AbstractList<E>{
    private static class Node<E>{
        private E data;
        private Node<E> next;

        private Node(E data, Node<E> next){
            this.data = data;
            this.next = next;
        }

        public Node<E> getNext(){return next; }
        public void setNext(Node<E> node){ next = node; }
        public E getData(){return data;}
        public void setData(E data){ this.data = data; }
    }

    private Node<E> head;
    private Node<E> tail;

    public SinglyLinkedList(){
        head = new Node<>(null, null);
        tail = head;
        size = 0;
    }

    public void add(E data){
        Node<E> newNode = new Node<>(data, tail.getNext());
        tail.setNext(newNode);
        tail = newNode;
        size++;
    }

    public void add(E data, int idx) {
        validAddingIndex(idx);

        if(idx==size){
            add(data);
        }

        Node<E> prevNode = findNode(idx-1);
        Node<E> newNode = new Node<>(data, prevNode.getNext());
        prevNode.setNext(newNode);
        size++;
    }

    public E get(int idx){
        validExistingIndex(idx);

        return findNode(idx).getData();
    }

    public void set(E data, int idx){
        validExistingIndex(idx);

        Node<E> targetNode;
        if(idx==size){
            targetNode = tail;
        }else{
            targetNode = findNode(idx);
        }

        targetNode.setData(data);
    }

    public void remove(int idx){
        validExistingIndex(idx);

        Node<E> prevNode = findNode(idx-1);
        Node<E> targetNode = prevNode.getNext();
        prevNode.setNext(targetNode.getNext());
        size--;
    }

    private Node<E> findNode(int idx){
        int curIdx = -1;
        Node<E> curNode;
        for(curNode=head;
                curNode.getNext()!=null && curIdx<idx;
                    curNode=curNode.getNext(), curIdx++){}

        return curNode;
    }
}