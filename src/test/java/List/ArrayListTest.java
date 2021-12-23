package List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ArrayListTest {
    private ArrayList<Integer> arr;

    @BeforeEach
    void setUp() {
        arr = new ArrayList<>();
    }

    @Test
    @DisplayName("4개 데이터 - 리사이징x")
    public void addLastTest(){
        int addSize = 4;
        fillArr(addSize);
        assertEquals(addSize, arr.size());

        checkElements(addSize);
    }

    @Test
    @DisplayName("12개 데이터 - 리사이징o")
    public void addLastMoreTest(){
        int addSize = 12;
        fillArr(addSize);
        assertEquals(addSize, arr.size());

        checkElements(addSize);
    }

    @Test
    @DisplayName("중간에 삽입")
    public void addByIndex(){
        int addSize = 5;
        fillArr(5);

        arr.add(-1, 2);
        arr.add(-2, 2);
        arr.add(-3, 2);

        assertEquals(-1, arr.get(4));
        assertEquals(-2, arr.get(3));
        assertEquals(-3, arr.get(2));
        assertEquals(8, arr.size());
    }

    @Test
    public void getTest(){
        int addSize = 4;
        fillArr(addSize);
        checkElements(addSize);
    }

    @Test
    @DisplayName("업데이트 테스트")
    public void setTest(){
        int addSize = 10;
        fillArr(10);

        arr.set(-1, 5);
        arr.set(-2, 6);

        assertEquals(-1, arr.get(5));
        assertEquals(-2, arr.get(6));
        assertEquals(addSize, arr.size());
    }


    @Test
    @DisplayName("삭제 테스트 - 전부삭제")
    public void removeTest(){
        int addSize = 5;
        fillArr(addSize);

        for(int i=addSize-1; i>=0; i--){
            arr.remove(i);
        }

        assertEquals(0, arr.size());
    }

    @Test
    @DisplayName("삭제 테스트 - 중간삭제")
    public void removeByIdxTest(){
        int addSize = 10;
        fillArr(addSize);

        arr.remove(5);
        assertEquals(6, arr.get(5));
        arr.remove(6);
        assertEquals(8, arr.get(6));
        assertEquals(8, arr.size());
    }

    private void fillArr(int addSize){
        for(int i=0; i<addSize; i++){
            arr.add(i);
        }
    }

    private void checkElements(int addSize){
        for(int i=0; i<addSize; i++){
            assertEquals(i, arr.get(i));
        }
    }
}