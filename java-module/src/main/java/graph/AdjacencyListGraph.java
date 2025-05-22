package graph;

import stack.ArrayStack;
import stack.Stack;

import java.util.LinkedList;
import java.util.Queue;

public class AdjacencyListGraph<T> implements Graph<T> {
    static class Node {
        int vertex = -1;
        Node next = null;

        public Node(int vertex, Node next) {
            this.vertex = vertex;
            this.next = next;
        }
    }
    private T[] vertices;
    private Node[] list;
    private int size;
    private int maxSize;
    private boolean[] visited;

    @SuppressWarnings("unchecked")
    public AdjacencyListGraph(int maxSize) {
        this.maxSize = maxSize;
        this.vertices = (T[]) new Object[maxSize];
        list = new Node[maxSize];
    }

    @Override
    public void addVertex(T vertex) {
        vertices[size++] = vertex;
    }

    @Override
    public void addEdge(int from, int to) {
        Node temp = new Node(to, list[from]);
        list[from] = temp;
    }

    public void print() {
        for (int v = 0; v < size; v++) {
            System.out.print(vertices[v] + " : ");
            Node current = list[v];
            while (current != null) {
                System.out.print(current.vertex + " ");
                current = current.next;
            }
            System.out.println();
        }
        System.out.println();
    }

    public void depthFirstSearch() {
        resetVisited();
        depthFirstSearch(0);
        System.out.println();
    }

    private void depthFirstSearch(int v) {
        visited[v] = true;
        System.out.print(vertices[v] + " ");
        Node current = list[v];
        while (current != null) {
            int w = current.vertex;
            if (!visited[w] && vertices[w] != null) {
                depthFirstSearch(w);
            }
            current = current.next;
        }
    }

    private void iterativeDFS() {
        resetVisited();
        int start = 0;
        Stack<Integer> s = new ArrayStack<>(size);
        s.push(start);
        while (!s.isEmpty()) {
            int v = s.peek();
            s.pop();
            if (visited[v]) {
                continue;
            }
            visited[v] = true;
            System.out.print(vertices[v] + " ");
            Node current = list[v];
            while (current != null) {
                int w = current.vertex;
                if (!visited[w]) {
                  s.push(w);
                }
                current = current.next;
            }
        }
        System.out.println();
    }

    public void breadthFirstSearch() {
        resetVisited();
        Queue<Integer> q = new LinkedList<>();
        int start = 0;
        visited[start] = true;
        q.offer(start);
        System.out.print(vertices[start] + " ");
        while (!q.isEmpty()) {
            int v = q.poll();
            Node current = list[v];
            while (current != null) {
                int w = current.vertex;
                if (!visited[w]) {
                    visited[w] = true;
                    System.out.print(vertices[w] + " ");
                    q.offer(w);
                }
                current = current.next;
            }
        }
        System.out.println();
    }

    private void resetVisited() {
        if (visited == null) {
            visited = new boolean[maxSize];
        }
        for (int i = 0; i < maxSize; i++) {
            visited[i] = false;
        }
    }

    public static void main(String[] args) {
        AdjacencyListGraph<String> graph = new AdjacencyListGraph<>(7);

        graph.addVertex("A");
        graph.addVertex("B");
        graph.addVertex("C");
        graph.addVertex("D");
        graph.addVertex("E");
        graph.addVertex("F");
        graph.addVertex("G");

        graph.addEdge(0, 1);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(3, 1);
        graph.addEdge(1, 4);
        graph.addEdge(4, 1);
        graph.addEdge(2, 4);
        graph.addEdge(4, 2);
        graph.addEdge(3, 6);
        graph.addEdge(6, 3);
        graph.addEdge(4, 6);
        graph.addEdge(6, 4);
        graph.addEdge(5, 6);
        graph.addEdge(6, 5);

        System.out.println("인접 리스트:");
        graph.print();

        System.out.println("깊이 우선 탐색 결과(재귀 기반): ");
        graph.depthFirstSearch(); // A C E G F D B

        System.out.println("깊이 우선 탐색 결과(반복 기반): ");
        graph.iterativeDFS(); // A B D G E C F

        System.out.println("너비 우선 탐색 결과: ");
        graph.breadthFirstSearch(); //
    }
}
