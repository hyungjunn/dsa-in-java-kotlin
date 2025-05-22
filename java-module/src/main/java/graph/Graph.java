package graph;

public interface Graph<T> {
    void addVertex(T vertex);
    void addEdge(int from, int to);
}
