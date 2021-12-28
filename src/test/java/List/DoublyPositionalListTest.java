package List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class DoublyPositionalListTest {
    DoublyPositionalList<Integer> list;

    @BeforeEach
    public void setUp() {
        list = new DoublyPositionalList<Integer>();
    }

    @Test
    @DisplayName("맨뒤에 삽입")
    public void addLastTest(){
        int addSize = 10;
        fillList(addSize);
        assertEquals(addSize, list.size());

        Position<Integer> p = list.getFirst();
        for(int i=0; i<addSize; i++){
            assertEquals(i, p.getData());
            p = list.getNextOf(p);
        }
    }

    @Test
    @DisplayName("특정 Position뒤에 삽입")
    public void addNextTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> first = list.getFirst();
        list.addNext(first, -1);
        Position<Integer> second = list.getNextOf(first);
        assertEquals(-1, second.getData());
    }

    @Test
    @DisplayName("특정 Position앞에 삽입")
    public void addPrevTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> last = list.getFirst();
        list.addPrev(last, -1);
        Position<Integer> lastPrev = list.getPrevOf(last);
        assertEquals(-1, lastPrev.getData());
    }

    @Test
    @DisplayName("첫번째 원소 조회 테스트")
    public void getFirstTest(){
        int addSize = 10;
        fillList(addSize);
        assertEquals(0, list.getFirst().getData());
    }

    @Test
    @DisplayName("마지막 원소 조회 테스트")
    public void getLastTest(){
        int addSize = 10;
        fillList(addSize);
        assertEquals(9, list.getLast().getData());
    }

    @Test
    @DisplayName("특정 Position의 Next Position조회")
    public void getNextOfTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> first = list.getFirst();
        Position<Integer> second = list.getNextOf(first);
        assertEquals(1, second.getData());
    }

    @Test
    @DisplayName("특정 Position의 Prev Position조회")
    public void getPrevOfTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> last = list.getLast();
        Position<Integer> lastPrev = list.getPrevOf(last);
        assertEquals(8, lastPrev.getData());
    }

    @Test
    @DisplayName("특정 Position의 원소 변경")
    public void setTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> p = list.getFirst();
        p = list.getNextOf(p); //1
        p = list.getNextOf(p); //2
        p = list.getNextOf(p); //3

        assertEquals(3, p.getData());
        list.set(p, -1);
        assertEquals(-1, p.getData());
    }

    @Test
    @DisplayName("특정 position(Node) 삭제")
    public void removeTest(){
        int addSize = 10;
        fillList(addSize);
        Position<Integer> p = list.getLast();
        list.remove(p);
        assertEquals(8, list.getLast().getData());
    }

    private void fillList(int addSize){
        for(int i=0; i<addSize; i++){
            list.add(i);
        }
    }
}