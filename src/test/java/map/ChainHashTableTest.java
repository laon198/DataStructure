package map;

import common.Entry;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Iterator;

import static org.junit.jupiter.api.Assertions.*;

class ChainHashTableTest {
    private Map<Integer, Character> map;

    @BeforeEach
    void setUp() {
        map = new ChainHashTable<>();
    }

    @Test
    @DisplayName("put 테스트")
    public void putTest(){
        int putSize = 10;

        fillMap(putSize);

        assertEquals(putSize, map.size());
    }

    @Test
    @DisplayName("get 테스트")
    public void getTest(){
        int putSize = 10;
        fillMap(putSize);

        for(int i=0; i<putSize; i++){
            assertEquals((char)('a'+i), map.get(i));
        }
    }

    @Test
    @DisplayName("set 테스트")
    public void setTest(){
        int putSize = 10;
        fillMap(putSize);

        for(int i=putSize-1, j=0; i>=0; i--,j++){
            map.set(j, (char)('a'+i));
        }

        for(int i=0, j=putSize-1; i<putSize; i++,j--){
            assertEquals((char)('a'+j), map.get(i));
        }
    }

    @Test
    @DisplayName("remove 테스트")
    public void removeTest(){
        int putSize = 10;
        fillMap(putSize);

        for(int i=0; i<putSize/2; i++){
            map.remove(i);
        }

        for(int i=putSize/2; i<putSize; i++){
            assertEquals((char)('a'+i), map.get(i));
        }

        assertEquals(putSize/2, map.size());
    }

    @Test
    @DisplayName("entrySet iterator test")
    public void entrySetTest(){
        int putSize = 10;
        fillMap(putSize);

        for(Entry<Integer,Character> entry : map.entrySet()){
            System.out.print("entry.getKey() = " + entry.getKey());
            System.out.println(" entry.getValue() = " + entry.getValue());
        }
    }

    @Test
    @DisplayName("keySet iterator test")
    public void keySetTest(){
        int putSize = 10;
        fillMap(putSize);

        for(Integer key : map.keySet()){
            System.out.println("key = " + key);
        }
    }

    @Test
    @DisplayName("valueSet iterator test")
    public void valueSetTest(){
        int putSize = 10;
        fillMap(putSize);

        for(Character value : map){
            System.out.println("value = " + value);
        }
    }
    
    private void fillMap(int size){
        for(int i=0; i<size; i++){
            map.put(i, (char)('a'+i));
        }
    }

}