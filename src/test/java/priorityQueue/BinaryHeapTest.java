package priorityQueue;

import common.Entry;
import list.ArrayList;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BinaryHeapTest {
    private PriorityQueue<Integer, Character> pQueue;

    @BeforeEach
    void setUp() {
        pQueue = new BinaryHeap<>();
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

    @Test
    @DisplayName("heapify Test")
    public void heapifyTest(){
        pQueue = new BinaryHeap<>();
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