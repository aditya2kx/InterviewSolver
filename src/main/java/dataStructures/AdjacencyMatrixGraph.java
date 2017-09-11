package dataStructures;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by adi2ky on 8/30/17.
 */
public class AdjacencyMatrixGraph<T> implements Graph<T> {
    /* Outer Map, <vertex, collection of incident edges to vertices>
     * Inner Map, <vertex incident from source(outer map) vertex, weight of edge>
     */
    private Map<T, Map<T, Integer>> vertexMatrixMap;

    public AdjacencyMatrixGraph() {
        this.vertexMatrixMap = new HashMap<>();
    }

    @Override
    public AdjacencyMatrixGraph addEdge(T vertex1, T vertex2, int weight) {
        vertexMatrixMap.get(vertex1).put(vertex2, weight);
        vertexMatrixMap.get(vertex2).put(vertex1, weight);
        return this;
    }

    @Override
    public AdjacencyMatrixGraph addVertex(T vertex) {
        vertexMatrixMap.put(vertex, new HashMap<>());
        return this;
    }

    @Override
    public List<T> getNeighbours(T vertex) {
        return new ArrayList<>(vertexMatrixMap.get(vertex).keySet());
    }

    @Override
    public Set<T> getVertices() {
        return vertexMatrixMap.keySet();
    }

    @Override
    public Integer getEdgeWeight(T vertex1, T vertex2) {
        return vertexMatrixMap.get(vertex1).get(vertex2);
    }
}
