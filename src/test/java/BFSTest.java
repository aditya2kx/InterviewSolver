import dataStructures.AdjacencyListGraph;
import dataStructures.Graph;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;
import traversal.graph.BFS;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

/**
 * Created by adi2ky on 8/28/17.
 */
@Listeners(FailListener.class)
public class BFSTest {
    @DataProvider
    public Object[][] traverseAdjacencyListGraphDataProvider() {
        Graph<Integer> graph = new AdjacencyListGraph<>();
        Integer[] vertices = new Integer[] {0, 1, 2, 3};

        for (Integer vertex : vertices) {
            graph.addVertex(vertex);
        }

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(2, 3);
        graph.addEdge(3, 3);

        return new Object[][] {
                {graph, 2, Arrays.asList(new Integer[] {2, 0, 3, 1})}
        };
    }

    @Test(dataProvider = "traverseAdjacencyListGraphDataProvider")
    public void traverseAdjacencyListGraphTest(Graph<Integer> graph,
                                               Integer startVertex,
                                               List<Integer> expectedTraversalOrder) {
        BFS<Integer> bfs = new BFS<>();
        List<Integer> actualTravesalOrder = bfs.traverseGraph(graph, startVertex);
        assertEquals(actualTravesalOrder, expectedTraversalOrder);
    }
}
