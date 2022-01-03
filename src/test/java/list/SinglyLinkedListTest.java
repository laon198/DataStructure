package list;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class SinglyLinkedListTest {
    private SinglyLinkedList<Integer> list;

    @BeforeEach
    void setUp() {
        list = new SinglyLinkedList<Integer>();
    }

    @Test
    @DisplayName("맨앞에 원소 삽입")
    public void addFirstTest(){
        fillList(6);
        list.add(-1, 0);
        assertEquals(7, list.size());
        assertEquals(-1, list.get(0));
    }

    @Test
    @DisplayName("중간에 원소 삽입")
    public void addMiddleTest(){
        fillList(6);
        list.add(-1, 3);
        list.add(-2, 3);
        assertEquals(8, list.size());
        assertEquals(-1, list.get(4));
        assertEquals(-2, list.get(3));
    }

    @Test
    @DisplayName("맨뒤에 원소 삽입")
    public void addLastTest(){
        fillList(6);
        list.add(-1);
        list.add(-2);
        assertEquals(-1, list.get(6));
        assertEquals(-2, list.get(7));
        assertEquals(1, list.get(0));
        assertEquals(2, list.get(1));
    }

    @Test
    @DisplayName("원소 얻어오는 테스트")
    public void getTest(){
        fillList(6);

        for(int i=0; i<6; i++){
            assertEquals(i+1, list.get(i));
        }
    }

    @Test
    @DisplayName("원소 얻기 테스트 실패")
    public void getFailTest(){
        IndexOutOfBoundsException e = assertThrows(
                IndexOutOfBoundsException.class, ()->{
                    list.get(4);
                }
        );

        fillList(3);

        IndexOutOfBoundsException e2 = assertThrows(
                IndexOutOfBoundsException.class, ()->{
                    list.get(5);
                }
        );
    }

    @Test
    @DisplayName("원소 수정 테스트")
    public void setTest(){
        fillList(8);
        list.set(-1, 4);
        assertEquals(-1, list.get(4));
        list.set(-2, 4);
        assertEquals(-2, list.get(4));
        assertEquals(8, list.size());
    }

    @Test
    @DisplayName("맨앞 원소 삭제 테스트")
    public void removeFirstTest(){
        fillList(8);
        list.remove(0);
        assertEquals(2, list.get(0));
        list.remove(0);
        assertEquals(3, list.get(0));
        assertEquals(6, list.size());
    }

    @Test
    @DisplayName("중간 원소 삭제 테스트")
    public void removeMiddleTest(){
        fillList(8);
        list.remove(4);
        assertEquals(6, list.get(4));
        list.remove(4);
        assertEquals(7, list.get(4));
        assertEquals(6, list.size());
    }

    @Test
    @DisplayName("맨 끝 원소 삭제 테스트")
    public void removeLastTest(){
        fillList(8);
        list.remove(7);
        IndexOutOfBoundsException e1 = assertThrows(
                IndexOutOfBoundsException.class,
                ()->{
                    list.get(7);
                }
        );

        list.remove(6);
        IndexOutOfBoundsException e2 = assertThrows(
                IndexOutOfBoundsException.class,
                ()->{
                    list.get(6);
                }
        );

        assertEquals(6, list.size());
    }

    @Test
    @DisplayName("snapshot Iterator 테스트")
    public void iteratorTest(){
        int addSize = 10;
        fillList(addSize);

        Iterator<Integer> iter = list.iterator();
        list.set(-1, 3);

        for(int i=1; i<=addSize; i++){
            assertEquals(i, iter.next());
        }
    }

    private void fillList(int addSize){
        for(int i=1; i<=addSize; i++){
            list.add(i);
        }
    }
}