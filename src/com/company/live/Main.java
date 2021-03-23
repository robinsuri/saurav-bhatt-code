package com.company.live;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/*
------------
bucket sort
    # 1000_000   # n2 n3 n4 n5 n6
100 # 1.526768E9 #
r2
r3
r4
r5
r6

------------------
quick sort
    # 1000_000    # n2 n3 n4 n5 n6
100 # 1.0824864E9 #
r2
r3
r4
r5
r6
------------------

n = 1000_000
r = 100
quicksort = 1.0824864E9 ns
Bucketsort = 1.526768E9 ns

int n = 1000_000;
int r = 1000;

Bucket sort took time = 1.7039861E9 ns
Quick sort took time  = 2.05363676E10 ns


 */
public class Main {

    public static void main(String[] args) {
//        double[] doubleArray = {.78, .17, .39, .72, .94, .21, .12, .23, .68};
//        quickSort(doubleArray, 0, doubleArray.length - 1);
        int n = 1000_00;
        int r = 100;

        
        /*

        i = 0
        0.1234  r = 3
        Array[0] =0.1234, Array[1] = 0.1234 , [2]= 0.1234
        i = 3
        i = 0+3-1 = 2
         */
        double[] doubleArray = new double[n];
        for (int i = 0; i < n; i++) {
            double element = Math.random(); // This will be between 0 and 1
            int repetitions = generateRandom(1, 2 * r);
            for (int j = i; j < i + repetitions && j<n; j++)
                doubleArray[j] = element;
            i = i + repetitions - 1;
        }
        double[] dupArray = doubleArray.clone();

        double currentTime = System.nanoTime();
        //
        bucketSort(doubleArray);
        double endTime= System.nanoTime();
        System.out.println("Bucket sort took time = "+(endTime - currentTime)+" ns");

        currentTime = System.nanoTime();
        quickSort(dupArray,0,n-1);
        endTime= System.nanoTime();

        System.out.println("Quick sort took time  = "+(endTime - currentTime)+" ns");

        // .12, .17, .21, .23, .39, .68, .72  .78, .94
//        for (int i = 0; i < dupArray.length; i++)
//            System.out.print(dupArray[i] + ", ");

    }

    private static int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min) + min;
        /*
        random.nextInt(max-min) will generate a random number
        between 0 and max-min
        X =  random.nextInt(max-min)+min
        X = 0 + min = min
        X = max-min + min = max
        min<=X<max
         */
    }


    private static void bucketSort(double[] A) {
        int n = A.length;
        List<Double>[] buckets = new ArrayList[n];
        for (int i = 0; i < n; i++)
            buckets[i] = new ArrayList<>(0);
        for (int i = 0; i < n; i++) {
            int bucketNumber = (int) (n * A[i]);
            buckets[bucketNumber].add(A[i]);
        }
        for (int i = 0; i < n; i++)
            insertionSort(buckets[i]);
        int index = 0;
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < buckets[i].size(); j++)
                A[index++] = buckets[i].get(j);
        }

    }

    private static void insertionSort(List<Double> bucket) {
        int n = bucket.size();
        for (int i = 1; i < n; i++) {
            Double key = bucket.get(i);
            // we are trying to find the correct position of the key
            int j = i - 1;
            while (j >= 0 && key < bucket.get(j)) {
                j--;
            }
            bucket.remove(i);
            bucket.add(j + 1, key);
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
        ///-->generateRandom(p,r)
        double x = A[r];
        int i = p - 1;
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