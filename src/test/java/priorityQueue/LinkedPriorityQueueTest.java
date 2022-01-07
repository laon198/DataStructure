package priorityQueue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedPriorityQueueTest {
    private PriorityQueue<Integer, Character> pQueue;

    @BeforeEach
    void setUp() {
        pQueue = new LinkedPriorityQueue<>();
    }

    @Test
    @DisplayName("addTest")
    public void addTest(){
        fillQueue();
        assertEquals(6, pQueue.size());
    }

    @Test
    @DisplayName("removeMin Test")
    public void removeMinTest(){
        fillQueue();
        for(int i=0; i<6; i++){
            assertEquals(i+1, pQueue.getMin().getKey());
            assertEquals((char)('a'+i), pQueue.removeMin().getValue());
        }
    }

    private void fillQueue(){
        pQueue.add(5, 'e');
        pQueue.add(4, 'd');
        pQueue.add(1, 'a');
        pQueue.add(2, 'b');
        pQueue.add(3, 'c');
        pQueue.add(6, 'f');
    }
}