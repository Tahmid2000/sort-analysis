package com.company;
import java.lang.*;
import java.io.*;
import java.util.*;

public class Sorts {

    private int movements;
    private int comparisons;

    public Sorts() {
        movements = 0;
        comparisons = 0;
    }

    public void insertionSort(int[] list) {
        movements = 0;
        comparisons = 0;
        for (int i = 1; i < list.length; i++) {
            int currentElement = list[i];
            int k;
            for (k = i - 1; k >= 0 && list[k] > currentElement; k--) {
                list[k + 1] = list[k];
                comparisons++;
                movements++;
            }
            comparisons++;
            list[k + 1] = currentElement;
        }
        movements = Math.abs(movements);
        comparisons = Math.abs(comparisons);
    }

    public void selectionSort(int[] list) {
        movements = 0;
        comparisons = 0;
        for (int i = 0; i < list.length - 1; i++) {
            int currentMin = list[i];
            int currentMinIndex = i;

            for (int j = i + 1; j < list.length; j++) {
                if (currentMin > list[j]) {
                    currentMin = list[j];
                    currentMinIndex = j;
                    comparisons++;
                }
            }
            if (currentMinIndex != i) {
                list[currentMinIndex] = list[i];
                list[i] = currentMin;
                movements++;
            }
        }
    }

    //Quick Sort
    public void quickSort(int[] list) {
        comparisons = 0;
        movements = 0;
        quickSort(list, 0, list.length - 1);
    }

    public void quickSort(int[] list, int first, int last) {
        if (last > first) {
            int pivotIndex = partition(list, first, last);
            quickSort(list, first, pivotIndex - 1);
            quickSort(list, pivotIndex + 1, last);
        }
    }

    public int partition(int[] list, int first, int last) {

        int pivot = list[first];
        int low = first + 1;
        int high = last;

        while (high > low) {
            while (low <= high && list[low] <= pivot) {
                low++;
                comparisons++;
            }
            while (low <= high && list[high] > pivot) {
                high--;
                comparisons++;
            }
            if (high > low) {
                int temp = list[high];
                list[high] = list[low];
                list[low] = temp;
                movements++;
            }
        }

        while (high > first && list[high] >= pivot) {
            comparisons++;
            high--;
        }

        if (pivot > list[high]) {
            list[first] = list[high];
            list[high] = pivot;
            movements++;
            return high;
        } else
            return first;
    }
    //Quick Sort

    //merge sort
    public void mergeSort(int[] list) {
        if (list.length > 1) {
            int[] firstHalf = new int[list.length / 2];
            System.arraycopy(list, 0, firstHalf, 0, list.length / 2);
            mergeSort(firstHalf);

            int secondHalfLength = list.length - list.length / 2;
            int[] secondHalf = new int[secondHalfLength];
            System.arraycopy(list, list.length / 2, secondHalf, 0, secondHalfLength);
            mergeSort(secondHalf);
            merge(firstHalf, secondHalf, list);
        }
    }

    public void merge(int[] list1, int[] list2, int[] temp) {
        int current1 = 0;
        int current2 = 0;
        int current3 = 0;

        while (current1 < list1.length && current2 < list2.length) {
            comparisons++;
            if (list1[current1] < list2[current2]) {
                temp[current3++] = list1[current1++];
                //movements++;

            } else {
                temp[current3++] = list2[current2++];
                //movements++;
            }
        }

        while (current1 < list1.length) {
            temp[current3++] = list1[current1++];
            //movements++;
        }
        while (current2 < list2.length) {
            temp[current3++] = list2[current2++];
            //movements++;
        }
    }

    //radix sort
    public int getMax(int arr[], int n) {
        int mx = arr[0];
        for (int i = 1; i < n; i++)
            if (arr[i] > mx) mx = arr[i];
        return mx;
    }

    public void countSort(int arr[], int n, int exp) {
        int output[] = new int[n];
        int i;
        int count[] = new int[10];
        Arrays.fill(count, 0);
        for (i = 0; i < n; i++)
            count[(arr[i] / exp) % 10]++;
        for (i = 1; i < 10; i++) {
            count[i] += count[i - 1];
        }
        for (i = n - 1; i >= 0; i--) {
            output[count[(arr[i] / exp) % 10] - 1] = arr[i];
            count[(arr[i] / exp) % 10]--;
            movements++;
        }
        for (i = 0; i < n; i++)
            arr[i] = output[i];
    }

    public void radixsort(int arr[], int n) {
        movements = 0;
        comparisons = 0;
        int m = getMax(arr, n);
        for (int exp = 1; m / exp > 0; exp *= 10)
            countSort(arr, n, exp);
    }

    class Heap<E extends Comparable<E>> {
        private java.util.ArrayList<E> list = new java.util.ArrayList<>();

        public Heap() {

        }

        public Heap(E[] objects) {
            for (int i = 0; i < objects.length; i++)
                add(objects[i]);
        }

        public void add(E newObject) {
            list.add(newObject);
            int currentIndex = list.size() - 1;

            while (currentIndex > 0) {
                int parentIndex = (currentIndex - 1) / 2;
                comparisons++;
                if (list.get(currentIndex).compareTo(list.get(parentIndex)) > 0) {
                    E temp = list.get(currentIndex);
                    list.set(currentIndex, list.get(parentIndex));
                    list.set(parentIndex, temp);
                    movements++;
                } else
                    break;
                currentIndex = parentIndex;
            }
        }

        public E remove() {
            if (list.size() == 0)
                return null;
            E removedObject = list.get(0);
            list.set(0, list.get(list.size() - 1));
            list.remove(list.size() - 1);

            int currentIndex = 0;
            while (currentIndex < list.size()) {
                int leftChildIndex = 2 * currentIndex + 1;
                int rightChildIndex = 2 * currentIndex + 2;
                if (leftChildIndex >= list.size())
                    break;
                int maxIndex = leftChildIndex;
                if (rightChildIndex < list.size()) {
                    if (list.get(maxIndex).compareTo(list.get(rightChildIndex)) < 0) {
                        maxIndex = rightChildIndex;
                    }
                }
                if (list.get(currentIndex).compareTo(list.get(maxIndex)) < 0) {
                    E temp = list.get(maxIndex);
                    list.set(maxIndex, list.get(currentIndex));
                    list.set(currentIndex, temp);
                    currentIndex = maxIndex;
                    // movements++;
                } else
                    break;
                //comparisons++;
            }
            return removedObject;
        }

        public int getSize() {
            return list.size();
        }
    }

    public <E extends Comparable<E>> void heapSort(E[] list) {
        comparisons = 0;
        movements = 0;
        Heap<E> heap = new Heap<>();
        for (int i = 0; i < list.length; i++)
            heap.add(list[i]);
        for (int i = list.length - 1; i >= 0; i--)
            list[i] = heap.remove();
    }

    public void bucketSort(int[] list){
        movements = 0;
        comparisons = 0;
        ArrayList<Integer>[] bucket = new java.util.ArrayList[list.length+1];
        for(int i = 0; i < list.length; i++){
            int key = list[i];
            if(bucket[key] == null){
                bucket[key] = new java.util.ArrayList<Integer>();
                movements++;
            }
            bucket[key].add(list[i]);
        }
        int k = 0;
        for(int i = 0; i < bucket.length; i++){
            if(bucket[i] != null){
                for(int j = 0; j < bucket[i].size(); j++) {
                    list[k++] = bucket[i].get(j);
                    movements++;
                }
            }
        }
    }
    public int getMovements() {
        return movements;
    }

    public int getComparisons() {
        return comparisons;
    }

    public int setMovements(int n) {
        return movements;
    }

    public int setComparisons(int n) {
        return comparisons;
    }
}
