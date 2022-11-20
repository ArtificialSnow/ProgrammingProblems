package problems.hard;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class _2203_MinimumWeightedSubgraphWithTheRequiredPaths {
    public long minimumWeight(int n, int[][] edges, int src1, int src2, int dest) {
        List<List<Edge>> graph = new ArrayList<>();
        List<List<Edge>> complementGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
            complementGraph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            graph.get(edge[0]).add(new Edge(edge[1], edge[2]));
            complementGraph.get(edge[1]).add(new Edge(edge[0], edge[2]));
        }

        Long[] src1Paths = shortestPathsWeighted(graph, src1);
        Long[] src2Paths = shortestPathsWeighted(graph, src2);
        Long[] destPaths = shortestPathsWeighted(complementGraph, dest);
        if (src1Paths[dest] == null || src2Paths[dest] == null) return -1;

        long ans = Long.MAX_VALUE;
        for (int i = 0; i < n; i++) {
            Long a = src1Paths[i], b = src2Paths[i], c = destPaths[i];
            if (a == null || b == null || c == null) continue;
            ans = Math.min(ans, a + b + c);
        }

        return ans;
    }

    private Long[] shortestPathsWeighted(List<List<Edge>> graph, int src) {
        Long[] pathLengths = new Long[graph.size()];
        boolean[] done = new boolean[graph.size()];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a.cost));
        pq.add(new Node(src, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            if (done[current.index]) continue;

            pathLengths[current.index] = current.cost;
            done[current.index] = true;

            for (Edge edge : graph.get(current.index)) {
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
