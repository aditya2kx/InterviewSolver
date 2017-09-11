package traversal.graph;

import org.apache.commons.collections4.CollectionUtils;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

import dataStructures.Graph;

/**
 * Created by adi2ky on 8/31/17.
 */
public class DijkstraGraphSearch<T> implements GraphTraversal<T> {

    /**
     * Approach: Basically, keeps track of visited vertices and
     * does not traverse them again. Apart from that its simple BFS
     * similar to a BFS for a Tree.
     *
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
                           .filter(neighbourVertex -> isDistanceGreater(graph, shortestDistanceMap, neighbourVertex, closestUnvisitedVertex))
                           .collect(Collectors.toMap(Function.identity(),
                                neighbourVertex -> (shortestDistanceMap.get(closestUnvisitedVertex) +
                                graph.getEdgeWeight(closestUnvisitedVertex, neighbourVertex)))));
        } while (CollectionUtils.isNotEmpty(unvisitedVertexSet));

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


}
