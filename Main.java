package com.company;
import java.util.*;
import java.util.concurrent.TimeUnit;

public class Main {

    public static int comps = 0;
    public static int swaps = 0;

    public static void main(String[] args) {

        Scanner myObj = new Scanner(System.in);
        System.out.println("How many elements would you like in your array?");
        int countVars = myObj.nextInt();

        System.out.println("");
        System.out.println("1. InOrder");
        System.out.println("2. ReverseOrder");
        System.out.println("3. AlmostOrder");
        System.out.println("4. Random");
        System.out.println("What type of array would you like (pick 1, 2, 3, or 4)?");
        int arrType = myObj.nextInt();

        System.out.println("");
        System.out.println("1. Insertion");
        System.out.println("2. Selection");
        System.out.println("3. Quick");
        System.out.println("4. Merge");
        System.out.println("5. Heap");
        System.out.println("6. Radix");
        System.out.println("7. Bucket");
        System.out.println("What type of sort would you like (pick 1, 2, 3, 4, 5, 6, 7)?");
        int sortType = myObj.nextInt();

        long startTime = System.nanoTime();
        createSorter(countVars, arrType, sortType);
        long endTime = System.nanoTime();
        long durationInNano = (endTime - startTime);
        long durationInMillis = TimeUnit.NANOSECONDS.toMillis(durationInNano);
        System.out.println("");
        System.out.println(durationInNano);
        System.out.println(durationInMillis);
        System.out.println("Comparisons: " + comps);
        System.out.println("Movements: " + swaps);
    }

    private static void createSorter(int countOfVars, int type, int sorterType){
        Sorts sorter = new Sorts();
        Random rand = new Random();
        int[] yur = new int[countOfVars];
        if(type == 4){ //random
            for(int i = 0; i < countOfVars; i++) {
                int rand_int1 = rand.nextInt(countOfVars);
                yur[i] = rand_int1;
            }
        }
        else if(type==1){ //inorder
            for(int i = 0; i < countOfVars; i++) {
                int rand_int1 = i;
                yur[i] = rand_int1;
            }
        }
        else if(type==2){ //reverseorder
            for(int i = countOfVars-1; i >= 0; i--) {
                int rand_int1 = i;
                yur[i] = rand_int1;
            }
        }
        else if(type==3){ //almostorder
            int almostVars = (int)(countOfVars * .8);
            for(int i = 0; i < almostVars; i++) {
                int rand_int1 = i;
                yur[i] = rand_int1;
            }
            for(int i = almostVars; i < countOfVars; i++){
                int rand_int1 = rand.nextInt(countOfVars);
                yur[i] = rand_int1;
            }
        }
        switch (sorterType) {
            case 1:
                sorter.insertionSort(yur);
                break;
            case 2:
                sorter.selectionSort(yur);
                break;
            case 3:
                sorter.quickSort(yur);
                break;
            case 4:
                sorter.setComparisons(0);
                sorter.setMovements(0);
                sorter.mergeSort(yur);
                break;
            case 5:
                sorter.setComparisons(0);
                sorter.setMovements(0);
                Integer[] yurrr = Arrays.stream(yur).boxed().toArray(Integer[]::new);
                sorter.heapSort(yurrr);
                break;
            case 6:
                sorter.radixsort(yur, yur.length);
                break;
            case 7:
                sorter.bucketSort(yur);
                break;
            default:
                System.out.println("Input Error");
                break;
        }
        comps = sorter.getComparisons();
        swaps = sorter.getMovements();
    }
}