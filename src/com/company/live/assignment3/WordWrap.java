package com.company.live.assignment3;

import java.util.ArrayList;
import java.util.List;

// A Dynamic programming solution for
// Word Wrap Problem in Java
public class WordWrap
{

    final int MAX = Integer.MAX_VALUE;

    // A utility function to print the solution
    int printSolution (int p[], int n)
    {
        int k;
        if (p[n] == 1)
            k = 1;
        else
            k = printSolution (p, p[n]-1) + 1;
        System.out.println("Line number" + " " + k + ": " +
                "From word no." +" "+ p[n] + " " + "to" + " " + n);
        return k;
    }

    // l[] represents lengths of different words in input sequence.
// For example, l[] = {3, 2, 2, 5} is for a sentence like
// "aaa bb cc ddddd". n is size of l[] and M is line width
// (maximum no. of characters that can fit in a line)
    void solveWordWrap (int l[], int n, int M)
    {
        // For simplicity, 1 extra space is used in all below arrays

        // extras[i][j] will have number of extra spaces if words from i
        // to j are put in a single line
        int extras[][] = new int[n+1][n+1];

        // lc[i][j] will have cost of a line which has words from
        // i to j
        int lc[][]= new int[n+1][n+1];

        // c[i] will have total cost of optimal arrangement of words
        // from 1 to i
        int c[] = new int[n+1];

        // p[] is used to print the solution.
        int p[] =new int[n+1];

        // calculate extra spaces in a single line. The value extra[i][j]
        // indicates extra spaces if words from word number i to j are
        // placed in a single line
        for (int i = 1; i <= n; i++)
        {
            extras[i][i] = M - l[i-1];
            for (int j = i+1; j <= n; j++)
                extras[i][j] = extras[i][j-1] - l[j-1] - 1;
        }

        // Calculate line cost corresponding to the above calculated extra
        // spaces. The value lc[i][j] indicates cost of putting words from
        // word number i to j in a single line
        for (int i = 1; i <= n; i++)
        {
            for (int j = i; j <= n; j++)
            {
                if (extras[i][j] < 0)
                    lc[i][j] = MAX;
                else if (j == n && extras[i][j] >= 0)
                    lc[i][j] = 0;
                else
                    lc[i][j] = extras[i][j]*extras[i][j];
            }
        }

        // Calculate minimum cost and find minimum cost arrangement.
        // The value c[j] indicates optimized cost to arrange words
        // from word number 1 to j.
        c[0] = 0;
        for (int j = 1; j <= n; j++)
        {
            c[j] = MAX;
            for (int i = 1; i <= j; i++)
            {
                if (c[i-1] != MAX && lc[i][j] != MAX &&
                        (c[i-1] + lc[i][j] < c[j]))
                {
                    c[j] = c[i-1] + lc[i][j];
                    p[j] = i;
                }
            }
        }
        System.out.println("cost = "+c[n]);
        printSolution(p, n);
    }

    public static void main(String args[])
    {
        WordWrap w = new WordWrap();

        int l[] = build();
        int n = l.length;
        int M = 50;
        w.solveWordWrap (l, n, M);
    }
    private static int[] build(){
        String listOfWords = "aaaaaaa aaaaaaa a aaaaaaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaa aaaaa aaaaaaaaaaaaaaa aa aaa aaaaaaaaaaa aaaaa aaaaaaaaaa aaaaaaaaaaaaa aaaaaaa aaaaaaaaaaa aaaaaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaaaaaaa aaaaaa aaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaa aaaaaaaaaaaaaaa a aaa aaaaaaaaaaa aaaaaaaaaa aaaa aaaaa aaaa aa aaaaaaaaaa aaaaaa aaaaaa aaaaaaa aaaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaa aaaaaaa aaaaaaaa aaa a aaaaaaaaa a aaaaaaaaa";
        String[] listOfWordsSplit = listOfWords.split("\\s");
        List<Integer> integerList = new ArrayList<>();
        for (String s : listOfWordsSplit) {
            integerList.add(s.length());
        }
        int[] primitive = integerList.stream()
                .mapToInt(Integer::intValue)
                .toArray();
        return  primitive;
    }
}

// This code is contributed by Saket Kumar
