package problems.hard;

import java.util.Comparator;
import java.util.PriorityQueue;

public class _1368_MinimumCostToMakeAtLeastOneValidPathInAGrid {
    public int minCost(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        boolean[][] visited = new boolean[n][m];
        PriorityQueue<Node> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a.dist));
        pq.add(new Node(0, 0, 0));

        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int i = current.i;
            int j = current.j;
            if (i == n-1 && j == m-1) {
                return current.dist;
            }

            visited[i][j] = true;
            if (j+1 < m && !visited[i][j+1]) pq.add(new Node(i, j + 1, grid[i][j] == 1 ? current.dist : (current.dist + 1)));
            if (j-1 >= 0 && !visited[i][j-1]) pq.add(new Node(i, j - 1, grid[i][j] == 2 ? current.dist : (current.dist + 1)));
            if (i+1 < n && !visited[i+1][j]) pq.add(new Node(i + 1, j, grid[i][j] == 3 ? current.dist : (current.dist + 1)));
            if (i-1 >= 0 && !visited[i-1][j]) pq.add(new Node(i - 1, j, grid[i][j] == 4 ? current.dist : (current.dist + 1)));
        }

        return -1;
    }

    public static class Node {
        int i;
        int j;
        int dist;

        public Node (int i, int j, int dist) {
            this.i = i;
            this.j = j;
            this.dist = dist;
        }
    }
}
