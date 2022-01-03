package deque;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedDequeTest {
    private Deque<Integer> deque;

    @BeforeEach
    void setUp() {
        deque = new LinkedDeque<>();
    }

    @Test
    public void addFirstTest(){
        int addSize = 10;
        fillFirst(addSize);
        assertEquals(addSize, deque.size());
    }

    @Test
    public void addLastTest(){
        int addSize = 10;
        fillLast(addSize);
        assertEquals(addSize, deque.size());
    }

    @Test
    public void removeFirstTest(){
        int addSize = 10;
        fillFirst(addSize);

        for(int i=addSize-1; i>=0; i--){
            assertEquals(i, deque.removeFirst());
        }

        assertEquals(0, deque.size());
    }

    @Test
    public void removeLastTest(){
        int addSize = 10;
        fillLast(addSize);

        for(int i=addSize-1; i>=0; i--){
            assertEquals(i, deque.removeLast());
        }

        assertEquals(0, deque.size());
    }

    private void fillFirst(int addSize){
        for(int i=0; i<addSize; i++){
            deque.addFirst(i);
        }
    }

    private void fillLast(int addSize) {
        for(int i=0; i<addSize; i++){
            deque.addLast(i);
        }
    }
}