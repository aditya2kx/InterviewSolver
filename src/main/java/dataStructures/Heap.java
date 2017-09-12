package dataStructures;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.BinaryOperator;

public class Heap<T> {
    private List<T> dataList;
    private BinaryOperator<T> heapPropertyFunction;

    public Heap(BinaryOperator<T> heapPropertyFunction) {
        this.dataList = new ArrayList<>();
        this.heapPropertyFunction = heapPropertyFunction;
    }

    public List<T> getDataList() {
        return dataList;
    }

    public BinaryOperator<T> getHeapPropertyFunction() {
        return heapPropertyFunction;
    }

    /**
     * Time Complexity : O(log(vertex))
     * Storage Capacity : O(Vertex)
     */
    public Heap<T> insertData(T value) {
        dataList.add(0, value);
        heapify(dataList, 0, dataList.size());
        return this;
    }

    /**
     * Time Complexity : O(vertex log(vertex))
     * Storage Capacity : O(Vertex)
     */
    public List<T> getSortedList() {
        List<T> dataListCopy = new ArrayList<>(getDataList());
        int heapSize = dataListCopy.size();
        while (heapSize != 0) {
            Collections.swap(dataListCopy, 0, heapSize - 1);
            heapSize--;
            heapify(dataListCopy, 0, heapSize);
        }
        return dataListCopy;
    }

    /**
     * Time Complexity : O(log(vertex))
     * Storage Capacity : O(Vertex)
     */
    private void heapify(List<T> dataList, int initialParentIndex, int heapSize) {
        int leftChildIndex = (2 * initialParentIndex) + 1;
        int rightChildIndex = leftChildIndex + 1;
        int calculatedParentIndex = initialParentIndex;
        if (leftChildIndex < heapSize) {
            calculatedParentIndex = heapPropertyFunction.apply(dataList.get(initialParentIndex), dataList.get
                    (leftChildIndex)) == dataList.get(initialParentIndex) ? initialParentIndex : leftChildIndex;
        }
        if (rightChildIndex < heapSize) {
            calculatedParentIndex = heapPropertyFunction.apply(dataList.get(calculatedParentIndex), dataList.get
                    (rightChildIndex)) == dataList.get(calculatedParentIndex) ? calculatedParentIndex : rightChildIndex;
        }
        if (calculatedParentIndex != initialParentIndex) {
            Collections.swap(dataList, initialParentIndex, calculatedParentIndex);
            heapify(dataList, calculatedParentIndex, heapSize);
        }
    }
}
