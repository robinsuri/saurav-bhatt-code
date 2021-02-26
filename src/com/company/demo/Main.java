package com.company.demo;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

/*
13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, 11
quicksort(A,1,12);
quicksort(A)  we need to include the parameters for indexes

quicksort(A,q,r) ---> Sort the subarray of A from index q to r
(1)  (11,6,2,4,7,8, 5,9), [12], (13, 19, 21)  <--- 2 new arrays
      quicksort(A,1,8)            quicksort(A,10,12)
(2)   ( 2,4,[5], 11,6,7,8,9), [12], (13, [19], 21 )
-------------X---------------------------
13, 19, 9, 5, 12, 8, 7, 4, 21, 2, 6, [11]
[0           6]<-
9,5,8,7,4,2,6, |12,21,13,19|[11]
(9,5,8,7,4,2,6), [11],|21,13,19,12|
index of pariitioning pivot = 7

 */
public class Main {

    public static void main(String[] args) {
        int n = 10;
        int r = 2;
        double[] doubleArray = generateRandom(n, r);
        for(int i = 0;i<10;i++)
            System.out.print(doubleArray[i]+" ,");
        System.out.println();
//        quickSort(doubleArray, 0, doubleArray.length - 1);

        bucketSort(doubleArray);
//        Math.random();
    }


    private static double[] generateRandom(int n, int r) {
        double[] doubles = new double[n];
        int i = 0;
        while (i < n) {
            double randomNumber = Math.random();
            int repetitions = generateRandomIntegerWithinBounds(1, 2 * r);
            for (int j = i; j < i + repetitions && j<n; j++)
                doubles[j] = randomNumber;
            i = i + r;
        }
        return doubles;
    }

    private static int generateRandomIntegerWithinBounds(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
    }

    private static void bucketSort(double[] A) {
        int n = A.length;
        List<Double>[] buckets = new ArrayList[n];

        for(int i =0;i<n;i++)
            buckets[i] = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            int listIndex = (int) (n * A[i]);
            buckets[listIndex].add(A[i]);
        }
        for (int i = 0; i < n; i++) {
            insertionSort(buckets[i]);
            //Collections.sort(buckets[i])
        }

    }

    private static void insertionSort(List<Double> bucket) {
        int n = bucket.size();
        Object[] bucketArray =  bucket.toArray();
        for (int i = 1; i < n; ++i) {
            Double key = (Double) bucketArray[i];
            int j = i - 1;

            /* Move elements of arr[0..i-1], that are
               greater than key, to one position ahead
               of their current position */
            while (j >= 0 && (Double)bucketArray[j] > key) {
                bucketArray[j + 1] = bucketArray[j];
                j = j - 1;
            }
            bucketArray[j + 1] = key;
        }
        for (int i = 0; i < n; i++) {
            System.out.print(bucketArray[i] + ", ");
        }
    }

    // {13, 19}--> [19]/
    private static void quickSort(double[] A, int p, int r) {
        if (p < r) {
            int q = partition(A, p, r);
            quickSort(A, p, q - 1);
            quickSort(A, q + 1, r);
        }
    }

    private static int partition(double[] A, int p, int r) {
        double x = A[r]; // x = 12
        int i = p - 1;  //p = 0 , r = 2, 13,19,12
        for (int j = p; j < r; j++) {
            if (A[j] <= x) {
                i++;
                double tmp = A[i];
                A[i] = A[j];
                A[j] = tmp;
            }
        }
        double tmp = A[i + 1];
        A[i + 1] = A[r];
        A[r] = tmp;

        return i + 1;
    }


}
