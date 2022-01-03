package stack;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

class ArrayStackTest {
    private ArrayStack<Integer> stack;

    @BeforeEach
    public void setUp(){
        stack = new ArrayStack<Integer>();
    }

    @Test
    @DisplayName("push 테스트")
    public void pushTest(){
        int addSize = 10;
        fillStack(addSize);
        assertEquals(addSize, stack.size());
    }

    @Test
    @DisplayName("pop 테스트")
    public void popTest(){
        int addSize = 10;
        fillStack(addSize);

        for(int i=addSize-1; i>=0; i--){
            assertEquals(i, stack.pop());
        }

        assertEquals(0, stack.size());
    }

    @Test
    @DisplayName("pop 테스트 실패 - empty stack")
    public void popEmptyFailTest(){
        NoSuchElementException e = assertThrows(
                NoSuchElementException.class,
                ()->{
                    stack.pop();
                }
        );
    }

    @Test
    @DisplayName("top 테스트")
    public void topTest(){
        int addSize = 10;
        fillStack(addSize);

        assertEquals(addSize-1, stack.top());
        assertEquals(addSize-1, stack.top());
        stack.pop();
        assertEquals(addSize-2, stack.top());
    }

    private void fillStack(int addSize){
        for(int i=0; i<addSize; i++){
            stack.push(i);
        }
    }
}