import dataStructures.Graph;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by adi2ky on 8/27/17.
 */
public class BFS<T> {
    public List<T> traverseGraph(Graph<T> graph, T startVertex) {
        HashSet<T> visitedVertexSet = new HashSet<>();
        LinkedList<T> toBeTraversedVertexList = new LinkedList<>();
        List<T> traversalOrderList = new ArrayList<T>();

        toBeTraversedVertexList.add(startVertex);

        while(toBeTraversedVertexList.peek() != null) {
            T currentVertex = toBeTraversedVertexList.remove();
            visitedVertexSet.add(currentVertex);
            traversalOrderList.add(currentVertex);
            List<T> unvisitedNeighbours = graph.getNeighbours(currentVertex)
                    .stream()
                    .filter(toBeTraversedVertex -> !visitedVertexSet.contains(toBeTraversedVertex))
                    .collect(Collectors.toList());
            toBeTraversedVertexList.addAll(unvisitedNeighbours);
        }
        return traversalOrderList;
    }
}
