package queue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayQueueTest {
    private Queue<Integer> queue;

    @BeforeEach
    public void setUp(){
        queue = new ArrayQueue<Integer>();
    }

    @Test
    @DisplayName("enqueue 테스트")
    public void enqueueTest(){
        int addSize = 10;
        fillQueue(addSize);

        assertEquals(addSize, queue.size());
    }

    @Test
    @DisplayName("dequeue 테스트")
    public void dequeueTest(){
        int addSize = 10;
        fillQueue(addSize);

        for(int i=0; i<addSize; i++){
            assertEquals(i, queue.dequeue());
        }

        assertEquals(0, queue.size());
    }

    @Test
    @DisplayName("dequeue 실패 테스트")
    public void dequeueFailTest(){
        NoSuchElementException e = assertThrows(
                NoSuchElementException.class,
                ()->{
                    queue.dequeue();
                }
        );
    }

    private void fillQueue(int addSize){
        for(int i=0; i<addSize; i++){
            queue.enqueue(i);
        }
    }
}