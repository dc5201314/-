package TrafficPathPlanning;

import java.util.Map;

public class Main {
    public static void main(String[] args) {
        Graph graph = new Graph();

        graph.addNode("A");
        graph.addNode("B");
        graph.addNode("C");

        graph.addEdge("A", "B", 2);
        graph.addEdge("A", "C", 1);
        graph.addEdge("B", "C", 3);

        Map<String, Integer> shortestDistances = Dijkstra.shortestPath(graph, "A");
        System.out.println(shortestDistances);
    }
}
