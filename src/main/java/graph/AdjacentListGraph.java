package graph;

import common.Position;
import list.ArrayList;
import list.DoublyPositionalList;

import java.util.LinkedList;

public class AdjacentListGraph<V,E> implements Graph<V,E> {
    private static class InnerVertex<V, E> implements Vertex<V>{
        private V element;
        private final int index;
        private final LinkedList<Edge<E>> outgoing, incoming;

        public InnerVertex(V element, int index, boolean isDirected) {
            this.element = element;
            this.index = index;
            outgoing = new LinkedList<>();
            if(isDirected){
                incoming = new LinkedList<>();
            }else{
                incoming = outgoing;
            }
        }

        public int getIndex() {
            return index;
        }

        public LinkedList<Edge<E>> getOutgoing(){
            return outgoing;
        }

        public LinkedList<Edge<E>> getIncoming(){
            return incoming;
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

        public ArrayList<Vertex<V>> getEndpoints() {
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

    private final boolean isDirected;
    private final ArrayList<Vertex<V>> vertices;
    private final Vertex<V> sentinelVertx;

    public AdjacentListGraph(){
        this(false);
    }

    public AdjacentListGraph(boolean isDirected){
        this.isDirected = isDirected;
        vertices = new ArrayList<>();
        sentinelVertx = new InnerVertex<>(null, -1, isDirected);
    }

    @Override
    public Vertex<V> addVertex(V vertexVal) {
        InnerVertex<V,E> newVertex = new InnerVertex<>(vertexVal, vertices.size(), isDirected);
        vertices.add(newVertex);
        return newVertex;
    }

    @Override
    public Vertex<V> addVertex(Vertex<V> v, V vertexVal, E edgeVal) {
        InnerVertex<V,E> existVertex = validateVertex(v);
        InnerVertex<V,E> newVertex = (InnerVertex<V, E>) addVertex(vertexVal);

        linkVertex(existVertex, newVertex, edgeVal);
        return newVertex;
    }

    @Override
    public Edge<E> linkVertex(Vertex<V> lhs, Vertex<V> rhs, E edgeVal) {
        InnerVertex<V,E> lhsVert = validateVertex(lhs);
        InnerVertex<V,E> rhsVert = validateVertex(rhs);

        Edge<E> newEdge = new InnerEdge<V,E>(edgeVal, lhsVert, rhsVert);
        lhsVert.getOutgoing().add(newEdge);
        rhsVert.getIncoming().add(newEdge);

        return newEdge;
    }

    @Override
    public Edge<E> getEdge(Vertex<V> lhs, Vertex<V> rhs) {
        InnerVertex<V,E> lhsVertex = validateVertex(lhs);

        for(Edge<E> edge : lhsVertex.getOutgoing()){
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
            for(Edge<E> existEdge : innerVertex.getOutgoing()){
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

        for(Edge<E> edge : innerVertex.getOutgoing()){
            removeEdge(edge);
        }

        for(Edge<E> edge : innerVertex.getIncoming()){
            removeEdge(edge);
        }

        vertices.set(sentinelVertx, innerVertex.getIndex());
    }

    @Override
    public void removeEdge(Edge<E> edge) {
        for(Vertex<V> endpoint : ((InnerEdge<V,E>)edge).getEndpoints()){
            ((InnerVertex<V,E>)endpoint).getOutgoing().remove(edge);
            if(isDirected){
                ((InnerVertex<V,E>)endpoint).getIncoming().remove(edge);
            }
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
