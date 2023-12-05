package TrafficPathPlanning;

import java.util.*;

public class Dijkstra {
    public static Map<String, Integer> shortestPath(Graph graph, String startNode) {
        Map<String, Integer> distances = new HashMap<>();
        Set<String> visited = new HashSet<>();
        PriorityQueue<Node> queue = new PriorityQueue<>();

        for (String node : graph.getNodes()) {
            distances.put(node, Integer.MAX_VALUE);
        }

        distances.put(startNode, 0);
        queue.add(new Node(startNode, 0));

        while (!queue.isEmpty()) {
            Node current = queue.poll();
            visited.add(current.getName());

            for (String neighbor : graph.getNeighbors(current.getName())) {
                if (!visited.contains(neighbor)) {
                    int distance = distances.get(current.getName()) + graph.getWeight(current.getName(), neighbor);
                    if (distance < distances.get(neighbor)) {
                        distances.put(neighbor, distance);
                        queue.add(new Node(neighbor, distance));
                    }
                }
            }
        }

        return distances;
    }
    private static class Node implements Comparable<Node> {
        private String name;
        private int distance;

        public Node(String name, int distance) {
            this.name = name;
            this.distance = distance;
        }

        public String getName() {
            return name;
        }

        public int getDistance() {
            return distance;
        }

        @Override
        public int compareTo(Node other) {
            return Integer.compare(distance, other.distance);
        }
    }
}