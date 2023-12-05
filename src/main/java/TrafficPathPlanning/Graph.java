package TrafficPathPlanning;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class Graph {
    private Map<String, Map<String, Integer>> adjacencyMap;

    public Graph() {
        adjacencyMap = new HashMap<>();
    }

    public void addNode(String node) {
        if (!adjacencyMap.containsKey(node)) {
            adjacencyMap.put(node, new HashMap<>());
        }
    }

    public void addEdge(String source, String destination, int weight) {
        adjacencyMap.get(source).put(destination, weight);
        adjacencyMap.get(destination).put(source, weight);
    }

    public int getWeight(String source, String destination) {
        return adjacencyMap.get(source).get(destination);
    }

    public Set<String> getNodes() {
        return adjacencyMap.keySet();
    }

    public Set<String> getNeighbors(String node) {
        return adjacencyMap.get(node).keySet();
    }
}
