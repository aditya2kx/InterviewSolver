package dataStructures;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by adi2ky on 8/27/17.
 *
 * This data structure represents a directed graph
 * supporting loops but not multiple edges.
 *
 * Storage Capacity : O(Vertex + Edge)
 */
public class AdjacencyListGraph<T> implements Graph<T> {
    private Map<T, LinkedList<T>> vertexMap;

    public AdjacencyListGraph() {
        vertexMap = new HashMap<>();
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public AdjacencyListGraph<T> addEdge(T vertex1, T vertex2) {
        if (vertexMap.containsKey(vertex1) && vertexMap.containsKey(vertex2)) {
            vertexMap.get(vertex1).add(vertex2);
        }
        return this;
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public AdjacencyListGraph<T> addVertex(T vertex) {
        vertexMap.put(vertex, new LinkedList<T>());
        return this;
    }

    /*
     * Time Complexity : O(1)
     */
    @Override
    public List<T> getNeighbours(T vertex) {
        return vertexMap.get(vertex);
    }

    @Override
    public Set<T> getVertices() {
        return vertexMap.keySet();
    }
}
