package dataStructures;

import java.util.List;

/**
 * Created by adi2ky on 8/27/17.
 */
public interface Graph<T> {
    void addEdge(T vertex1, T vertex2);
    void addVertex(T vertex);
    List<T> getNeighbours(T vertex);
}
