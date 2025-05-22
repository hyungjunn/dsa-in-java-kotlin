package graph;

import stack.ArrayStack;
import stack.Stack;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class AdjacencyMatrixGraph<T> implements Graph<T> {
    private T[] vertices;
    private int[][] matrix;
    private int size;
    private int maxSize;
    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public AdjacencyMatrixGraph(int maxSize) {
        this.maxSize = maxSize;
        this.vertices = (T[]) new Object[maxSize];
        matrix = new int[maxSize][maxSize];
    }

    @Override
    public void addVertex(T vertex) {
        vertices[size++] = vertex;
    }

    @Override
    public void addEdge(int from, int to) {
        if (from < 0 || from >= size || to < 0 || to >= size) {
            throw new IllegalArgumentException("Invalid vertex index");
        }
        matrix[from][to] = 1;
        matrix[to][from] = 1;
    }

    public void depthFirstSearch() {
        resetVisited();
        for (int i = 0; i < size; i++) {
            if (!visited[i])
              depthFirstSearch(i);
        }
        System.out.println();
    }

    private void depthFirstSearch(int v) {
        visited[v] = true;
        System.out.print(vertices[v] + " ");
        for (int w = 0; w < maxSize; w++) {
            if (matrix[v][w] == 1 && !visited[w]) {
                depthFirstSearch(w);
            }
        }
    }

    public void iterativeDFSV1() {
        resetVisited();
        int v = 0;
        Stack<Integer> s = new ArrayStack<>(maxSize);
        visited[v] = true;
        s.push(v);
        while (!s.isEmpty()) {
            v = s.peek();
            s.pop();
            System.out.print(vertices[v] + " ");
            for (int w = 0; w < maxSize; w++) {
                if (matrix[v][w] == 1 && !visited[w]) {
                    visited[w] = true;
                    s.push(w);
                }
            }
        }
        System.out.println();
    }

    public void iterativeDFSV2() {
        resetVisited();
        int v = 0;
        Stack<Integer> s = new ArrayStack<>(maxSize);
        s.push(v);
        while (!s.isEmpty()) {
            v = s.peek();
            s.pop();
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            System.out.print(vertices[v] + " ");
            for (int w = size - 1; w >= 0; w--) {
                if (matrix[v][w] == 1 && !visited[w]) {
                    s.push(w);
                }
            }
        }
        System.out.println();
    }

    public void iterativeDFSWithIterator() {
        resetVisited();
        Stack<Iterator<Integer>> s = new ArrayStack<>(maxSize);
        int start = 0;
        visited[start] = true;
        System.out.print(vertices[start] + " ");
        s.push(getAdjacencyIterator(start));
        while (!s.isEmpty()) {
            Iterator<Integer> current = s.peek();
            if (current.hasNext()) {
                int w = current.next();
                if (!visited[w]) {
                    visited[w] = true;
                    System.out.print(vertices[w] + " ");
                    s.push(getAdjacencyIterator(w));
                }
            } else {
                s.pop();
            }
        }
        System.out.println();
    }

    private Iterator<Integer> getAdjacencyIterator(int v) {
        List<Integer> adjacentVertices = new ArrayList<>();
        for (int i = 0; i < size; i++) {
            if (matrix[v][i] == 1) {
                adjacentVertices.add(i);
            }
        }
        return adjacentVertices.iterator();
    }

    public void breadthFirstSearch() {
        resetVisited();
        Queue<Integer> queue = new LinkedList<>();
        int start = 0;
        visited[start] = true;
        queue.offer(start);
        System.out.print(vertices[start] + " ");
        while (!queue.isEmpty()) {
            int v = queue.poll();
            for (int w = 0; w < size; w++) {
                if (!visited[w] && matrix[v][w] == 1) {
                    visited[w] = true;
                    queue.offer(w);
                    System.out.print(vertices[w] + " ");
                }
            }
        }
    }

    private void resetVisited() {
        if (visited == null) {
            visited = new boolean[maxSize];
        }
        for (int i = 0; i < maxSize; i++) {
            visited[i] = false;
        }
    }

    public void printVertices() {
        System.out.print("  ");
        for (int i = 0; i < size; i++) {
            System.out.print(vertices[i] + " ");
        }
        System.out.println();
    }

    public void printMatrix() {
        for (int i = 0; i < size; i++) {
            System.out.print(vertices[i] + " ");
            for (int j = 0; j < size; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    public static void main(String[] args) {
        AdjacencyMatrixGraph<String> graph = new AdjacencyMatrixGraph<>(7);
        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(2, 4);
        graph.addEdge(3, 6);
        graph.addEdge(4, 6);
        graph.addEdge(5, 6);

        System.out.println("인접 행렬: ");
        graph.printVertices();
        graph.printMatrix();

        System.out.println();

        System.out.println("깊이 우선 탐색 결과(재귀 기반): ");
        graph.depthFirstSearch(); // A B D G E C F
        System.out.println("깊이 우선 탐색 결과(스택 기반 v1): ");
        graph.iterativeDFSV1(); // A C E G F D B
        System.out.println("깊이 우선 탐색 결과(스택 기반 v2): ");
        graph.iterativeDFSV2(); // A B D G E C F
        System.out.println("깊이 우선 탐색 결과(스택+이터레이터 기반): ");
        graph.iterativeDFSWithIterator(); // A B D G E C F
        System.out.println("너비 우선 탐색 결과: ");
        graph.breadthFirstSearch(); // A B C D E F G
    }
}
