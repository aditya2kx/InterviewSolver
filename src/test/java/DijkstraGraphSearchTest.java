import com.google.common.collect.ImmutableMap;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Map;

import dataStructures.AdjacencyMatrixGraph;
import dataStructures.Graph;
import traversal.graph.DijkstraGraphSearch;

import static org.testng.Assert.assertEquals;

/**
 * Created by adi2ky on 9/1/17.
 */
@Listeners(FailListener.class)
public class DijkstraGraphSearchTest {
  @DataProvider
  public Object[][] shortestDistanceSearchDataProvider() {
    Graph<Integer> graph = new AdjacencyMatrixGraph<>();
    Integer[] vertices = new Integer[] {0, 1, 2, 3, 4, 5, 6, 7, 8};

    for (Integer vertex : vertices) {
      graph.addVertex(vertex);
    }

    graph.addEdge(0, 1, 4);
    graph.addEdge(0, 7, 8);
    graph.addEdge(1, 7, 11);
    graph.addEdge(1, 2, 8);
    graph.addEdge(2, 3, 7);
    graph.addEdge(2, 5, 4);
    graph.addEdge(2, 8, 2);
    graph.addEdge(3, 4, 9);
    graph.addEdge(3, 5, 14);
    graph.addEdge(4, 5, 10);
    graph.addEdge(5, 6, 2);
    graph.addEdge(6, 7, 1);
    graph.addEdge(6, 8, 6);
    graph.addEdge(7, 8, 7);

    return new Object[][] {
        {graph, 0, ImmutableMap.builder()
                    .put(0, 0)
                    .put(1, 4)
                    .put(2, 12)
                    .put(3, 19)
                    .put(4, 21)
                    .put(5, 11)
                    .put(6, 9)
                    .put(7, 8)
                    .put(8, 14)
                    .build()
        }
    };
  }

  @Test(dataProvider = "shortestDistanceSearchDataProvider")
  public void shortestDistanceSearchListTest(Graph<Integer> graph,
                                             Integer startVertex,
                                             Map<Integer, Integer> expectedShortestDistanceMap) {
    DijkstraGraphSearch dijkstraGraphSearch = new DijkstraGraphSearch();
    Map<Integer, Integer> actualShortestDistanceMap = dijkstraGraphSearch.findShortestDistance(graph, startVertex);
    assertEquals(actualShortestDistanceMap, expectedShortestDistanceMap);
  }
}
