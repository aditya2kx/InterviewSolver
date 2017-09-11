package traversal.graph;

import dataStructures.Graph;

import java.util.List;
import java.util.Map;

/**
 * Created by adi2ky on 8/28/17.
 */
public interface GraphTraversal<T> {
    default List<T> traverseGraph(Graph<T> graph, T startVertex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default Map<T, Integer> findShortestDistance(Graph<T> graph, T startVertex) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }
}
