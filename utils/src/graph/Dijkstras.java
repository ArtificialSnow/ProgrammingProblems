package graph;

import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class Dijkstras {

    public Long[] weightedGraphShortestPaths(List<List<Edge>> weightedAdjacencyList, int src) {
        Long[] pathLengths = new Long[weightedAdjacencyList.size()];
        boolean[] done = new boolean[weightedAdjacencyList.size()];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (done[current.index]) continue;

            pathLengths[current.index] = current.cost;
            done[current.index] = true;

            for (Edge edge : weightedAdjacencyList.get(current.index)) {
                if (done[edge.neighbour]) continue;
                pq.add(new Node(edge.neighbour, current.cost + edge.weight));
            }
        }

        return pathLengths;
    }

    static class Edge {
        int neighbour;
        int weight;

        public Edge(int neighbour, int weight) {
            this.neighbour = neighbour;
            this.weight = weight;
        }
    }

    static class Node {
        int index;
        long cost;

        public Node(int index, long cost) {
            this.index = index;
            this.cost = cost;
        }
    }
}
