package com.company.live.assignment3;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Q_1 {

    public static void main(String[] args) throws IOException {
        System.out.print("Enter number of words and page width : ");
        Scanner scanner = new Scanner(System.in);
        int numOfWords = scanner.nextInt();
        int width = scanner.nextInt();
        String[] words = new String[numOfWords];
        for (int i = 0; i < numOfWords; i++) {
            words[i] = generateRandomString(1, 15);
        }

                List<Integer> lineSplits = split(width, words);
        justify(width, words, lineSplits);

        FileWriter fileWriter = new FileWriter("unjust.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for(int i =0;i<words.length;i++){
            printWriter.print(words[i]);
            if(i!=words.length-1)
                printWriter.print(" ");
        }
        printWriter.close();
//        String[] words;
//        words = buildWords();// width = 91
////        System.out.println(badness(Words, 0, 3, 15));
//        //int width = 91;
//       int width = 18;words = new String[]{"This", "is", "sample", "text"};
////        words = new String[]{"abcdefghijklm"};
//        List<Integer> lineSplits = split(width, words);
////        int optimalNumOfWordsInFirstLine[] = new int[words.length];
////        System.out.println(minimumBadness(width, words, optimalNumOfWordsInFirstLine));
////        printSplitWithSingleSpace(words, lineSplits);
//        justify(width, words, lineSplits);


    }

    private static String generateRandomString(int minLength, int maxLength) {
        Random random = new Random();
        int lengthOfString = random.nextInt(maxLength - minLength + 1) + minLength;
        String randomString = "";
        // will generate a random number between minLength and maxLength inclusive
        for (int i = 0; i < lengthOfString; i++)
            randomString += "a";
        return randomString;
    }

    private static void printSplitWithSingleSpace(String[] words, List<Integer> lineSplits) {
        int previousLineIndex = 0;
        for (int i = 1; i < lineSplits.size(); i++) {
            int currentLineIndex = lineSplits.get(i);
            for (int j = previousLineIndex; j < currentLineIndex; j++)
                System.out.print(words[j] + " ");
            System.out.println();
            previousLineIndex = currentLineIndex;
        }
        for (int i = previousLineIndex; i < words.length; i++)
            System.out.print(words[i] + " ");
    }

    private static void justify(int width, String[] words, List<Integer> splits) throws IOException {
        FileWriter fileWriter = new FileWriter("just.txt");
        PrintWriter printWriter = new PrintWriter(fileWriter);
        for (int i = 0; i < splits.size(); i++) {
            int indexOfWordOnNextLine;
            if (i == splits.size() - 1) {
                indexOfWordOnNextLine = words.length;
            } else {
                indexOfWordOnNextLine = splits.get(i + 1);
            }
            int numOfWords = indexOfWordOnNextLine - splits.get(i);
            int wordsLength = rawLength(words, splits.get(i), indexOfWordOnNextLine);
            int extraSpaceLeft = width - wordsLength;
            int spaceBetweenEachWord = extraSpaceLeft / (numOfWords - 1);
            for (int j = splits.get(i); j < indexOfWordOnNextLine; j++) {
                printWriter.print(words[j]);
                if (j != indexOfWordOnNextLine - 1)
                    printSpaceToFile(spaceBetweenEachWord, printWriter);
            }
            printWriter.println();
        }
        printWriter.close();
    }

    private static int rawLength(String[] words, int i, int j) {
        int length = 0;
        for (int k = i; k < j; k++)
            length += words[k].length();
        return length;
    }

    private static void printSpaceToFile(int spaceBetweenEachWord, PrintWriter printWriter) {
        for (int i = 0; i < spaceBetweenEachWord; i++)
            printWriter.print(" ");
    }

    private static void printSpace(int spaceBetweenEachWord) {
        for (int i = 0; i < spaceBetweenEachWord; i++)
            System.out.print(" ");
    }

    private static List<Integer> split(int width, String[] words) {
        List<Integer> indexList = new ArrayList<>();
        int optimalNumOfWordsInFirstLine[] = new int[words.length];
        int x = minimumBadness(width, words, optimalNumOfWordsInFirstLine);
        System.out.println(x);
        int wordsSplitTillNow = 0;
        int index = 0;
        while (wordsSplitTillNow < words.length) {
            indexList.add(index);
            int wordsInCurrentLine = optimalNumOfWordsInFirstLine[index];
            wordsSplitTillNow += wordsInCurrentLine;
            index += wordsInCurrentLine;
        }
        return indexList;
    }

    private static int minimumBadness(int width, String[] words, int[] optimalNumOfWordsInFirstLine) {
        int length = words.length;
        //W[0]...W[length-1]
        int[] DP = new int[length + 1];
        DP[length] = 0;
        // DP[0]....Dp[length-]
        for (int i = length - 1; i >= 0; i--) {
            int currentMinimumBadness = -1;
            int numOfWordsIntheFirstLine = 1;

            for (int k = 1; k <= length - i; k++) {
                int currentBadnessTemp = badness(words, i, i + k, width);
                if (currentBadnessTemp != -1) // this means infinity
                {
                    int currentBadness = badness(words, i, i + k, width) + DP[i + k];
                    if (currentMinimumBadness == -1) {
                        currentMinimumBadness = currentBadness;
                    } else if (currentBadness < currentMinimumBadness) {
                        currentMinimumBadness = currentBadness;
                        numOfWordsIntheFirstLine = k;
                    }
                }
            }
            optimalNumOfWordsInFirstLine[i] = numOfWordsIntheFirstLine;
            DP[i] = currentMinimumBadness;
        }
        return DP[0];
    }

        private static String[] buildWords2(){
        String listOfWords = "aaaaaaaaaaaaaa aaaaaaa aaaaaaaaa aaa aaaaaaaaaaaa aaaaa aaa aaaaaaa aaaaaaaaaa aaaaaaaaaaa aaaaaaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaa aaaaaaaa aaaaaa aaaa aaaaaaaaaaaaa aaaaaaaaa aaaaa aaaaaaa aaaaaaaaaaaa aaaa aaaaaaaa aaaaaaaaa aa aaaaaaaaaaaaaaa aaa aaaaaaaaaaaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaaa aaaaaaaaa aaaaa aaaaaaaa aaaa aaaaa aaaaa aaaaaaaaaaaaaa aaaaaa aaaaaaaaaaaaaa aaaaaaaaaa a a aaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaaaaaaa aaaaaaaaaaaaaaa aaaaaaaaa aaaaaaaaaaaaa aaa aaaaaaaaaaaaaaa aaaaaaaaaaaa aaaaaaa aaaaaaa aaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaa aaaaa aaaaaa aaaaaaaaaaaaa aaaaaaaaa aaaaa a aaaaaaaaaaa aaaaaaaaaaaa aaaa aaaaaaaaa aaaaaaaaaaa aa aaaaaaa aaaaaaaa aaaaaaaaaaa aaaaaa aaa aaaaaaaaaaa aaaaaaaa aaaaaaaaaaaaaa aaaaaaaaaaaaaaa aaa a aaaa aaaaaaaaaa aaaaaa aaaaaaaaaaaa a aaa aaaaaa aaaa aaaaaaaaaa aaaaaaaaaa aaaaaaaaaa aaaaaaa aaaaaaaaaaaa aaaaaaaaaa aa aaaaaaaaaaaaaaa aaaaaaaaa aaaaaaaaaaaaaaa aaaaaa aaaaaaaa";
        String[] listofWordsSplit = listOfWords.split("\\s");
        return listofWordsSplit;
    }
    private static String[] buildWords() {
        String listOfWords = "And , we , are , increasingly , relying , on , videoconferencing , apps , like , Zoom , and ,\n" +
                "FaceTime , to , correspond , with , our , peers , But , inevitably, with , our , homes , and ,\n" +
                "workplaces , merging , into , one, the , boundaries , between , our , personal , and , professional\n" +
                ", lives , are , beginning , to , erode , and , awkward , situations , have , ensued";
        String[] strings = listOfWords.split(",");
        String[] words = new String[strings.length];
        for (int i = 0; i < strings.length; i++) {
            words[i] = strings[i].trim();
        }

        return words;
    }

    // W[i] to W[j-1] in a single than whats the badness
    private static int badness(String[] W, int i, int j, int width) {
        int badness;
        if (width - length(W, i, j) >= 0) {
            badness = (int) Math.pow(width - length(W, i, j), 3);
        } else {
            badness = -1; // Let's use -1 as a symbol for infinity
        }
        return badness;
    }

    // length of words from W[i] to W[j-1] + the number of spaces
    private static int length(String[] W, int i, int j) {
        int length = 0;
        for (int k = i; k < j; k++) {
            length = length + W[k].length();
        }
        /*
         How many spaces will be there between the Words W[i] to W[j-1] ??
         i = 2 and j = 6  ---> we write words from W[i] to W[j-1]
         W[2] W[3] W[4] W[5]  --> 4 = 6 - 2  = j -i  , spaces = 3

         i = 5 and j = 10
         how many words will be there ?
         W[5] W[6] W[7] W[8] W[9] ---> 5 = 10 - 5 = j - i
         Number of words from W[i] to W[j-1] will be equal to j-i.
         And number of spaces = Number of words - 1  = j-i-1
         Number of words = j - i  <--- Is this clear ??
         we have to take into account the space between words


        W[i] W[i+1] W[i+2]     W[j-2] W[j-1]*/
        int numOfSpaces = j - i - 1;
        return length + numOfSpaces;
    }
}
