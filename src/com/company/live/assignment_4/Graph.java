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
        System.out.println(System.currentTimeMillis());
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
        System.out.print("Enter the number of edges : ");
        int numOfEdges = scanner.nextInt();
        Graph g = new Graph(numOfNodes);
        for (int i = 0; i < numOfEdges; i++) {
            int vertex1 = g.generateRandom(0, numOfNodes - 1);
            int vertex2 = g.generateRandom(0, numOfNodes - 1);
            g.addEdge(vertex1, vertex2);
        }
        for (int i = 0; i < numOfNodes; i++)
            if (!g.getVisited()[i])
                g.DFS(i);
    }

    // This will generate a random number
    // between min and max inclusive of both
    private int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
