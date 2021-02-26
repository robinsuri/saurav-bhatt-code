package com.company.live;

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
        double[] doubleArray = {1, 1, 2, 2, 3, 3};
        quickSort(doubleArray, 0, doubleArray.length - 1);
        for (int i = 0; i < doubleArray.length; i++)
            System.out.print(doubleArray[i] + ", ");

    }


    private static void bucketSort(double[] A) {

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