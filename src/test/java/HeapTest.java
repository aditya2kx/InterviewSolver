import dataStructures.Heap;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static org.testng.Assert.assertEquals;

@Listeners(FailListener.class)
public class HeapTest {

    @DataProvider
    public Object[][] heapSortDataProvider() {

        return new Object[][] {
                {new Heap<Integer>((value1 ,value2) -> Math.min(value1, value2))
                        .insertData(1)
                        .insertData(5)
                        .insertData(3)
                        .insertData(10)
                        .insertData(7)
                        .insertData(6), Arrays.asList(10, 7, 6, 5, 3, 1)},
                {new Heap<String>((word1 ,word2) -> word1.compareTo(word2) >= 0 ? word1 : word2)
                        .insertData("Aditya")
                        .insertData("Sachin")
                        .insertData("Bhavik")
                        .insertData("Prerit")
                        .insertData("Rachit")
                        .insertData("Deepanker"), Arrays.asList("Aditya", "Bhavik", "Deepanker", "Prerit", "Rachit", "Sachin")}
        };
    }

    @Test(dataProvider = "heapSortDataProvider")
    public void heapSortTest(Heap heap,
                             List expectedSortedList) {
        List actualSortedList = heap.getSortedList();
        assertEquals(actualSortedList, expectedSortedList, String.format("\nActual List: %s\nExpected List: %s\n", actualSortedList, expectedSortedList));

        /*Heap<String> wordHeap = new Heap<>((word1 ,word2) -> word1.compareTo(word2) >= 0 ? word1 : word2);
        wordHeap.insertData("Aditya")
                .insertData("Sachin")
                .insertData("Bhavik")
                .insertData("Prerit")
                .insertData("Rachit")
                .insertData("Deepanker");
        System.out.println(wordHeap);*/
    }
}
