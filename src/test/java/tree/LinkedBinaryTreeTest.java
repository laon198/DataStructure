package tree;

import common.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class LinkedBinaryTreeTest {
    private LinkedBinaryTree<Integer> tree;

    @BeforeEach
    void setUp() {
        tree = new LinkedBinaryTree<>();
    }

    @Test
    @DisplayName("addRoot 테스트")
    public void addRootTest(){
        int rootData = 0;
        tree.addRoot(rootData);
        assertEquals(rootData, tree.getRoot().getData());
        assertEquals(1, tree.size());

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                ()->{
                    tree.addRoot(rootData);
                }
        );

        assertEquals("root is already exist", e.getMessage());
    }

    @Test
    @DisplayName("addLeft 테스트")
    public void addLeftTest(){
        //빈곳에 넣기
        int data = 0;
        tree.addRoot(data);
        data++;
        Position<Integer> root = tree.getRoot();
        tree.addLeft(root, data);
        Position<Integer> left = tree.getLeft(root);
        assertEquals(data, left.getData());

        //있는 곳에 넣기
        data++;
        tree.addLeft(root, data);
        left = tree.getLeft(root);
        Position<Integer> leftLeft = tree.getLeft(left);
        assertEquals(data, left.getData());
        assertEquals(data-1, leftLeft.getData());
        assertEquals(data+1, tree.size());
    }

    @Test
    @DisplayName("addRight 테스트")
    public void addRightTest(){
        //빈곳에 넣기
        int data = 0;
        tree.addRoot(data);
        data++;
        Position<Integer> root = tree.getRoot();
        tree.addRight(root, data);
        Position<Integer> right = tree.getRight(root);
        assertEquals(data, right.getData());

        //있는 곳에 넣기
        data++;
        tree.addRight(root, data);
        right = tree.getRight(root);
        Position<Integer> rightRight = tree.getRight(right);
        assertEquals(data, right.getData());
        assertEquals(data-1, rightRight.getData());
        assertEquals(data+1, tree.size());
    }

    @Test
    @DisplayName("set 테스트")
    public void setTest(){
        int data = 0;
        tree.addRoot(data);
        Position<Integer> root = tree.getRoot();
        data++;
        tree.addLeft(root, data);
        Position<Integer> left = tree.getLeft(root);
        int changedData = -1;
        tree.set(left, changedData);
        assertEquals(changedData, left.getData());
    }

    @Test
    @DisplayName("remove 테스트-자식 1개일때")
    public void removeTest_oneChild(){
        //빈곳에 넣기
        int data = 0;
        tree.addRoot(data);
        data++;
        Position<Integer> root = tree.getRoot();
        tree.addLeft(root, data);
        Position<Integer> left = tree.getLeft(root);
        assertEquals(data, left.getData());

        //있는 곳에 넣기
        data++;
        tree.addLeft(root, data);
        left = tree.getLeft(root);
        Position<Integer> leftLeft = tree.getLeft(left);
        assertEquals(data, left.getData());
        assertEquals(data-1, leftLeft.getData());

        tree.remove(left);
        left = tree.getLeft(root);
        assertEquals(--data, left.getData());
        assertEquals(data+1, tree.size());
    }

    @Test
    @DisplayName("remove 테스트 - 자식 없을때")
    public void removeTest_noChild(){
        //빈곳에 넣기
        int data = 0;
        tree.addRoot(data);
        data++;
        Position<Integer> root = tree.getRoot();
        tree.addLeft(root, data);
        Position<Integer> left = tree.getLeft(root);
        assertEquals(data, left.getData());

        tree.remove(left);
        left = tree.getLeft(root);
        assertEquals(null, left);
        assertEquals(1, tree.size());
    }

    @Test
    @DisplayName("remove 테스트 - 자식 2개일때")
    public void removeTest_twoChildren(){
        //빈곳에 넣기
        int data = 0;
        tree.addRoot(data);
        data++;
        Position<Integer> root = tree.getRoot();
        tree.addLeft(root, data);
        Position<Integer> left = tree.getLeft(root);
        assertEquals(data, left.getData());

        data++;
        tree.addLeft(left, data);
        data++;
        tree.addRight(left, data);

        IllegalStateException e = assertThrows(
                IllegalStateException.class,
                ()->{
                    tree.remove(left);
                }
        );

        assertEquals(
                "this node has two children",
                e.getMessage()
        );
    }
}