package dataStructures;

import java.util.List;
import java.util.Set;

/**
 * Created by adi2ky on 8/27/17.
 */
public interface Graph<T> {
    default Graph<T> addEdge(T vertex1, T vertex2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    }

    default Graph<T> addEdge(T vertex1, T vertex2, int weight) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    };

    Graph<T> addVertex(T vertex);

    List<T> getNeighbours(T vertex);

    Set<T> getVertices();

    default Integer getEdgeWeight(T vertex1, T vertex2) throws UnsupportedOperationException {
        throw new UnsupportedOperationException();
    };
}
