import dataStructures.Graph;
import org.apache.commons.collections4.CollectionUtils;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adi2ky on 8/28/17.
 */
public class DFS<T> implements TraversalSearch<T> {
    /**
     * Approach: Basically, keeps track of visited vertices and
     * does not traverse them again. Apart from that its simple DFS
     * similar to a BFS for a Tree.
     *
     * Time Complexity : O(Vertex + Edge)
     * Storage Capacity : O(Vertex)
     */
    @Override
    public List<T> traverseGraph(Graph<T> graph, T startVertex) {
        List<T> traversalOrderList = new ArrayList<>();
        LinkedList<T> toBeTraversedList = new LinkedList<>();
        HashSet<T> visitedVertexSet = new HashSet<>();
        toBeTraversedList.add(startVertex);
        traverseGraph(graph, toBeTraversedList, visitedVertexSet, traversalOrderList);
        return traversalOrderList;
    }

    private void traverseGraph(Graph<T> graph, LinkedList<T> toBeTraversedList, HashSet<T> visitedVertexSet, List<T> traversalOrderList) {
        if (CollectionUtils.isEmpty(toBeTraversedList)) {
            return;
        }
        T currentVertex = toBeTraversedList.removeFirst();
        visitedVertexSet.add(currentVertex);
        traversalOrderList.add(currentVertex);
        List<T> unvisitedNeighbours = graph.getNeighbours(currentVertex)
                .stream()
                .filter(neighbourVertex -> !visitedVertexSet.contains(neighbourVertex))
                .collect(Collectors.toList());
        toBeTraversedList.addAll(0, unvisitedNeighbours);
        traverseGraph(graph, toBeTraversedList, visitedVertexSet, traversalOrderList);
    }
}
