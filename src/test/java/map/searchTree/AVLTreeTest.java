package map.searchTree;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AVLTreeTest {
    private TreeMap<Integer,String> map;

    @BeforeEach
    void setUp() {
        map = new AVLTree<>();
    }

    @Test
    public void AVLTest1(){
        fillMap();
    }

    @Test
    public void AVLRemoveTest1(){
        fillMap();
        map.remove(60);
    }

    private void fillMap(){
        map.put(30, "");
        map.put(40, "");
        map.put(100, "");
        map.put(20, "");
        map.put(10, "");
        map.put(60, "");
        map.put(70, "");
        map.put(120, "");
        map.put(110, "");
    }
}