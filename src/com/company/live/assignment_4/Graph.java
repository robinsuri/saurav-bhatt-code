package com.company.live.assignment_4;

import java.util.*;

// Q2
/*  Time is in nanoseconds
V       |V|-1             (|V|-1)^3/2              (|V|-1)^2
10      481900  (E=9)      357600    (E=27)         213500    (E=81)
100     523800  (E=99)     1050300   (E=985)        3745600   (E=9801)
1000    2539700 (E=999)    10327000  (E=31575)      80795800  (E=998001)
*/

public class Graph {
    private int time = 0;
    private int numOfVertices;
    private List<VertexData> vertexData;
    private boolean visited[];
    private boolean hasCycle;

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
        hasCycle = false;
    }

    void addEdge(int vertex1, int vertex2) {
        adjacencyList[vertex1].add(vertex2);
    }

    void runDFS(int vertex, boolean visited[]) {
        time++;
        vertexData.get(vertex).setDiscoveryTime(time);
        visited[vertex] = true;
        Iterator<Integer> iterator = adjacencyList[vertex].listIterator();
        while (iterator.hasNext()) {
            int n = iterator.next();
            if (!visited[n]) {
                runDFS(n, visited);
                vertexData.get(n).setParentNode(vertex);
            }
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
        System.out.print("Enter the number of edges : ");
        int numOfEdges = scanner.nextInt();
        Graph g = new Graph(numOfNodes);
        for (int i = 0; i < numOfEdges; i++) {
            int vertex1 = g.generateRandom(0, numOfNodes - 1);
            int vertex2 = g.generateRandom(0, numOfNodes - 1);
            g.addEdge(vertex1, vertex2);
        }
        long startTime = System.nanoTime();
        for (int i = 0; i < numOfNodes; i++)
            if (!g.getVisited()[i])
                g.DFS(i);
        if (g.selfLoops() || g.detectBackEdge())
            System.out.println("Graph has a cycle");
        else
            System.out.println("Graph doesn't have a cycle");
        System.out.println("Running time = " + (System.nanoTime() - startTime));
    }

    private boolean selfLoops() {
        for (int i = 0; i < numOfVertices; i++) {
            if (adjacencyList[i].contains(i))
                return true;
        }
        return false;
    }

    private Graph generateGraphWithoutCycle(){
        Graph g = new Graph(1500);
        for (int i = 0; i < 1500-1; i++) {
            for(int j =0;j<=i;j++)
               g.addEdge(i, j+1);
        }
        return g;
    }
    private boolean detectBackEdge() {
        for (int i = 0; i < numOfVertices; i++) {
            Set<Integer> visitedNodesSet = new HashSet<>();
            int node = i;
            visitedNodesSet.add(node);
            node = vertexData.get(node).getParentNode();
            while (node != -1) {
                for (Integer visitedNode : visitedNodesSet) {
                    boolean isThereAnEdge = isThereAnEdge(visitedNode, node);
                    if (isThereAnEdge)
                        return true;
                }
                visitedNodesSet.add(node);
                node = vertexData.get(node).getParentNode();
            }
        }
        return false;
    }

    private boolean isThereAnEdge(Integer visitedNode, int node) {
        int length = adjacencyList[visitedNode].size();
        for (int i = 0; i < length; i++) {
            if (adjacencyList[visitedNode].get(i) == node)
                return true;
        }
        return false;
    }


    // This will generate a random number
    // between min and max inclusive of both
    private int generateRandom(int min, int max) {
        Random random = new Random();
        return random.nextInt(max - min + 1) + min;
    }
}
