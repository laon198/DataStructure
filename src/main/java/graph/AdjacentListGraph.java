package graph;

import list.ArrayList;

import java.util.LinkedList;

public class AdjacentListGraph<V,E> implements Graph<V,E> {
    private static class InnerVertex<V, E> implements Vertex<V>{
        private V element;
        private final int index;
        private final LinkedList<Edge<E>> edges;

        public InnerVertex(V element, int index) {
            this.element = element;
            this.index = index;
            edges = new LinkedList<>();
        }

        public int getIndex() {
            return index;
        }

        public void addEdge(Edge<E> edge){
            edges.add(edge);
        }

        public V getElement() {
            return element;
        }

        public void setElement(V element) {
            this.element = element;
        }
    }

    private static class InnerEdge<E> implements Edge<E>{
        private E weight;
        private int endpoint;

        public InnerEdge(E weight, int endpoint) {
            this.weight = weight;
            this.endpoint = endpoint;
        }

        public int getEndpoint() {
            return endpoint;
        }

        public void setEndpoint(int endpoint) {
            this.endpoint = endpoint;
        }

        @Override
        public E getWeight() {
            return weight;
        }

        public void setWeight(E weight) {
            this.weight = weight;
        }
    }

    private ArrayList<Vertex<V>> vertices;

    @Override
    public Edge<E> addVertex(Vertex<V> v, V vertexVal) {
        return addVertex(v, vertexVal, null);
    }

    @Override
    public Edge<E> addVertex(Vertex<V> v, V vertexVal, E edgeVal) {
        InnerVertex<V,E> newVertex = new InnerVertex<>(vertexVal, vertices.size());
        Edge<E> newEdge = new InnerEdge<E>(edgeVal, newVertex.getIndex());
        ((InnerVertex<V,E>)v).addEdge(newEdge);
        return newEdge;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> lhs, Vertex<V> rhs) {
        return null;
    }

    @Override
    public Vertex<V>[] getVertices(Edge<E> edge) {
        return new Vertex[0];
    }

    @Override
    public void setVertex(Vertex<V> vertex) {

    }

    @Override
    public void setEdge(Edge<E> edge) {

    }

    @Override
    public void removeVertex(Vertex<V> vertex) {

    }

    @Override
    public void removeEdge(Edge<E> edge) {

    }
}
