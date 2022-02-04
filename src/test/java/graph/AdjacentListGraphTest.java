package graph;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AdjacentListGraphTest {
    private Graph<Integer, Integer> graph;

    @BeforeEach
    void setUp() {
        graph = new AdjacentListGraph<>();
    }

    @Test
    public void addVertexTest(){
        Vertex<Integer> v0 = graph.addVertex(0);
        Vertex<Integer> v1 = graph.addVertex(v0, 1, 0);
        Vertex<Integer> v2 = graph.addVertex(v0, 2, 0);
        Vertex<Integer> v3 = graph.addVertex(v2, 3, 0);
        Vertex<Integer> v4 = graph.addVertex(4);
        Vertex<Integer> v5 = graph.addVertex(v4, 5, 0);
        Vertex<Integer> v6 = graph.addVertex(v5, 6, 0);
        Vertex<Integer> v7 = graph.addVertex(v6, 7, 0);
        graph.linkVertex(v6, v7, 0);
        graph.linkVertex(v1, v3, 0);
        Vertex<Integer> v8 = graph.addVertex(v3, 8, 0);
        Vertex<Integer> v9 = graph.addVertex(v3, 9, 0);

    }
}