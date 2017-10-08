package traversal.graph;

import dataStructures.Graph;
import org.apache.commons.collections4.CollectionUtils;

import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Created by adi2ky on 8/31/17.
 */
public class DijkstraGraphSearch<T> implements GraphTraversal<T> {

    /**
     * Approach: Basically, keeps track of visited vertices and
     * does not traverse them again. Apart from that its simple BFS
     * similar to a BFS for a Tree.
     * <p>
     * Time Complexity : O(Vertex^2)
     * Storage Capacity : O(Vertex)
     */
    @Override
    public Map<T, Integer> findShortestDistance(Graph<T> graph, T startVertex) {
        Map<T, Integer> shortestDistanceMap = new HashMap<>();
        Set<T> unvisitedVertexSet = new HashSet<>(graph.getVertices());

        shortestDistanceMap.put(startVertex, 0);

        do {
            T closestUnvisitedVertex = unvisitedVertexSet
                    .stream()
                    .filter(shortestDistanceMap::containsKey)
                    .min(Comparator.comparing(shortestDistanceMap::get))
                    .get();
            unvisitedVertexSet.remove(closestUnvisitedVertex);
            shortestDistanceMap.putAll(graph.getNeighbours(closestUnvisitedVertex)
                    .stream()
                    .filter(neighbourVertex -> isDistanceGreater(graph, shortestDistanceMap, neighbourVertex,
                            closestUnvisitedVertex))
                    .collect(Collectors.toMap(Function.identity(),
                            neighbourVertex -> (shortestDistanceMap.get(closestUnvisitedVertex) +
                                    graph.getEdgeWeight(closestUnvisitedVertex, neighbourVertex)))));
        } while (CollectionUtils.isNotEmpty(unvisitedVertexSet));

        return shortestDistanceMap;
    }

    /**
     * Approach: Uses Priority Queue to keep track of unvisited vertices.
     * The time complexity of extract minimum element from Priority Queue is O(log N)
     * and the time to traverse all vertices in a adjacency list backed Graph is
     * O(V + E).
     * Time Complexity : O(E Log V)
     * Storage Capacity : O(Vertex)
     */
    public Map<T, Integer> findShortestDistancePQ(Graph<T> graph, T startVertex) {
        Map<T, Integer> shortestDistanceMap = new HashMap<>();
        Map<T, Integer> unvisitedNodeMap = graph.getVertices()
                .stream()
                .collect(Collectors.toMap(Function.identity(), unvisitedVertex -> Integer.MAX_VALUE));

        shortestDistanceMap.put(startVertex, 0);
        unvisitedNodeMap.put(startVertex, 0);

        PriorityQueue<Map.Entry<T, Integer>> unvisitedNodePriorityQueue = new PriorityQueue<>(Comparator.comparing
                (Map.Entry<T, Integer>::getValue));
        unvisitedNodePriorityQueue.addAll(unvisitedNodeMap.entrySet());

        while (unvisitedNodePriorityQueue.peek() != null) {
            T closestVertex = unvisitedNodePriorityQueue.poll().getKey();
            unvisitedNodeMap.remove(closestVertex);
            Map<T, Integer> updatedNeighbourVertexMap = graph.getNeighbours(closestVertex)
                    .stream()
                    .filter(neighbourVertex -> unvisitedNodeMap.containsKey(neighbourVertex))
                    .filter(neighbourVertex -> isDistanceGreater(graph, shortestDistanceMap, neighbourVertex,
                            closestVertex))
                    .collect(Collectors.toMap(Function.identity(),
                            neighbourVertex -> (shortestDistanceMap.get(closestVertex) +
                                    graph.getEdgeWeight(closestVertex, neighbourVertex))));
            updatedNeighbourVertexMap.entrySet()
                    .forEach(updatedNeighbourVertexEntry -> {
                        /* Todo(adi2ky) Have custom implementation of Priority Q, with O(log N)
                         * runtime for removal of an element.
                         * Java's implementation for Priority Queue's removal of element takes  take O(N)
                         */
                        unvisitedNodePriorityQueue.remove(updatedNeighbourVertexEntry.getKey());
                        unvisitedNodePriorityQueue.add(updatedNeighbourVertexEntry);
                        shortestDistanceMap.put(updatedNeighbourVertexEntry.getKey(), updatedNeighbourVertexEntry.getValue());
                    });
        }

        return shortestDistanceMap;
    }

    /**
     * Time Complexity : O(1)
     * Storage Capacity : None
     */
    private boolean isDistanceGreater(Graph<T> graph,
                                      Map<T, Integer> shortestDistanceMap,
                                      T neighbourVertex,
                                      T closestUnvisitedVertex) {
        if (!shortestDistanceMap.containsKey(neighbourVertex)) {
            return true;
        }

        if (shortestDistanceMap.get(neighbourVertex) > (shortestDistanceMap.get(closestUnvisitedVertex) +
                graph.getEdgeWeight(closestUnvisitedVertex, neighbourVertex))) {
            return true;
        }

        return false;
    }

    class Node<T> implements Comparable<T> {
        private T vertex;
        private int distance;

        public Node(T vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }

        @Override
        public int compareTo(T o) {
            return 0;
        }

        @Override
        public boolean equals(Object otherObject) {
            if (!(otherObject instanceof Node)) {
                return false;
            }

            if (otherObject == null) {
                return false;
            }

            Node<T> otherNode = (Node) otherObject;
            return otherNode.getVertex() == this.vertex;
        }

        public T getVertex() {
            return vertex;
        }

        public int getDistance() {
            return distance;
        }
    }

}
