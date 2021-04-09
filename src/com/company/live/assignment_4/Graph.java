package com.company.live.assignment_4;
import java.util.*;
public class Graph {
    private int numOfVertices;
    private LinkedList<Integer> adjacencyList[];
    Graph(int numOfVertices)
    {
        this.numOfVertices = numOfVertices;
        adjacencyList = new LinkedList[numOfVertices];
        for (int i = 0; i < numOfVertices; ++i)
            adjacencyList[i] = new LinkedList();
    }
    void addEdge(int vertex1, int vertex2)
    {
        adjacencyList[vertex1].add(vertex2);
    }
    void runDFS(int vertex, boolean visited[])
    {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        Iterator<Integer> iterator = adjacencyList[vertex].listIterator();
        while (iterator.hasNext())
        {
            int n = iterator.next();
            if (!visited[n])
                runDFS(n, visited);
        }
    }
    void DFS(int v)
    {
        boolean visited[] = new boolean[numOfVertices];
        runDFS(v, visited);
    }

    public static void main(String args[])
    {
        System.out.print("Enter the number of nodes : ");
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        Graph g = new Graph(4);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(
                "Following is Depth First Traversal "
                        + "(starting from vertex 2)");

        g.DFS(2);
    }
}
