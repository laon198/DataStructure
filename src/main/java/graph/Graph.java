package graph;

public interface Graph<V,E> {
    Vertex<V> addVertex(V vertexVal);
    Vertex<V> addVertex(Vertex<V> v, V vertexVal, E edgeVal);
    Edge<E> linkVertex(Vertex<V> lhs, Vertex<V> rhs, E edgeVal);
    Edge<E> getEdge(Vertex<V> lhs, Vertex<V> rhs);
    Vertex<V>[] getVertices(Edge<E> edge);
    void setVertex(Vertex<V> vertex, V vertexVal);
    void setEdge(Edge<E> edge, E edgeVal);
    void removeVertex(Vertex<V> vertex);
    void removeEdge(Edge<E> edge);
}