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

        public void removeEdge(Edge<E> edge){
            edges.remove(edge);
        }

        public Iterable<Edge<E>> getEdges(){
            return edges;
        }

        public V getElement() {
            return element;
        }

        public void setElement(V element) {
            this.element = element;
        }
    }

    private static class InnerEdge<V,E> implements Edge<E>{
        private E weight;
        private final ArrayList<Vertex<V>> endpoints;

        public InnerEdge(E weight, Vertex<V> lhs, Vertex<V> rhs) {
            this.weight = weight;
            endpoints = new ArrayList<>(2);
            endpoints.add(lhs);
            endpoints.add(rhs);
        }

        public Iterable<Vertex<V>> getEndpoints() {
            return endpoints;
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
    private final Vertex<V> sentinelVertx = new InnerVertex<V,E>(null, -1);

    public AdjacentListGraph(){}

    @Override
    public Edge<E> addVertex(Vertex<V> v, V vertexVal) {
        return addVertex(v, vertexVal, null);
    }

    @Override
    public Edge<E> addVertex(Vertex<V> v, V vertexVal, E edgeVal) {
        InnerVertex<V,E> existVertex = validateVertex(v);
        InnerVertex<V,E> newVertex = new InnerVertex<>(vertexVal, vertices.size());
        Edge<E> newEdge = new InnerEdge<V,E>(edgeVal, existVertex, newVertex);
        vertices.add(newVertex);
        existVertex.addEdge(newEdge);
        newVertex.addEdge((newEdge));
        return newEdge;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> lhs, Vertex<V> rhs) {
        InnerVertex<V,E> lhsVertex = validateVertex(lhs);

        for(Edge<E> edge : lhsVertex.getEdges()){
            for(Vertex<V> vert : ((InnerEdge<V,E>)edge).getEndpoints()){
                if(vert==rhs){
                    return edge;
                }
            }
        }

        return null;
    }

    @Override
    public Vertex<V>[] getVertices(Edge<E> edge) {
        Vertex<V>[] result = new Vertex[2];

        for(Vertex<V> vertex : vertices){
            InnerVertex<V,E> innerVertex = validateVertex(vertex);
            for(Edge<E> existEdge : innerVertex.getEdges()){
                if(existEdge==edge){
                    int i = 0;
                    for(Vertex<V> resVert : ((InnerEdge<V,E>)existEdge).getEndpoints()){
                        result[i++] = resVert;
                    }

                    return result;
                }
            }
        }

        return result;
    }

    @Override
    public void setVertex(Vertex<V> vertex, V vertexVal) {
        InnerVertex<V,E> innerVertex = validateVertex(vertex);
        innerVertex.setElement(vertexVal);
    }

    @Override
    public void setEdge(Edge<E> edge, E edgeVal) {
        InnerEdge<V,E> innerEdge = validateEdge(edge);
        innerEdge.setWeight(edgeVal);
    }

    @Override
    public void removeVertex(Vertex<V> vertex) {
        InnerVertex<V,E> innerVertex = validateVertex(vertex);

        for(Edge<E> edge : innerVertex.getEdges()){
            for(Vertex<V> adjacent : ((InnerEdge<V,E>)edge).getEndpoints()){
                if(adjacent!=innerVertex){
                    ((InnerVertex<V,E>)adjacent).removeEdge(edge);
                }
            }
        }

        vertices.set(sentinelVertx, innerVertex.getIndex());
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        for(Vertex<V> endpoint : ((InnerEdge<V,E>)edge).getEndpoints()){
            ((InnerVertex<V,E>)endpoint).removeEdge(edge);
        }
    }

    private InnerVertex<V,E> validateVertex(Vertex<V> vertex){
        InnerVertex<V,E> innerVertex = null;

        try{
            innerVertex = (InnerVertex<V, E>) vertex;
        }catch(ClassCastException e){
            throw new IllegalArgumentException("no match vertex type");
        }

        return innerVertex;
    }

    private InnerEdge<V,E> validateEdge(Edge<E> edge){
        InnerEdge<V,E> innerEdge = null;

        try{
            innerEdge = (InnerEdge<V,E>) edge;
        }catch(ClassCastException e){
            throw new IllegalArgumentException("no match edge type");
        }

        return innerEdge;
    }
}
