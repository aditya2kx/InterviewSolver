import dataStructures.Graph;

import java.util.List;

/**
 * Created by adi2ky on 8/28/17.
 */
public interface TraversalSearch<T> {
    List<T> traverseGraph(Graph<T> graph, T startVertex);
}
