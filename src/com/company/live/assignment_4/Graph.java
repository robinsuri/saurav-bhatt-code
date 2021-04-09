package com.company.live.assignment_4;

import java.util.*;

public class Graph {
    private int time = 0;
    private int numOfVertices;
    private List<VertexData> vertexData;
    private boolean visited[];

    // adjacency list is mainted as an array of linked list
    // vertices 0 to n-1  if there n vertices
    // we make an array of n linkedlist
    private LinkedList<Integer> adjacencyList[];

    // initializing the adjacencylist
    Graph(int numOfVertices) {
        this.numOfVertices = numOfVertices;
        adjacencyList = new LinkedList[numOfVertices];
        vertexData = new ArrayList<>();
        for (int i = 0; i < numOfVertices; ++i) {
            adjacencyList[i] = new LinkedList();
            vertexData.add(new VertexData());
        }
        visited = new boolean[numOfVertices];
    }

    void addEdge(int vertex1, int vertex2) {
        adjacencyList[vertex1].add(vertex2);
    }

    void runDFS(int vertex, boolean visited[]) {
        time++;
        vertexData.get(vertex).setDiscoveryTime(time);
        visited[vertex] = true;
        System.out.print(vertex + " ");
        Iterator<Integer> iterator = adjacencyList[vertex].listIterator();
        while (iterator.hasNext()) {
            int n = iterator.next();
            if (!visited[n])
                runDFS(n, visited);
        }
        time++;
        vertexData.get(vertex).setFinishTime(time);
    }

    void DFS(int v) {
        runDFS(v, visited);
    }


    public boolean[] getVisited() {
        return visited;
    }

    public static void main(String args[]) {
        System.out.print("Enter the number of nodes : ");
        Scanner scanner = new Scanner(System.in);
        int numOfNodes = scanner.nextInt();
        Graph g = new Graph(6);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(1, 3);
        g.addEdge(3, 2);
        g.addEdge(4, 5);
        g.addEdge(5, 5);
        g.addEdge(4, 3);

        for (int i = 0; i < numOfNodes; i++)
            if (!g.getVisited()[i])
                g.DFS(i);
    }
}
