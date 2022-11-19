package problems.hard;

import java.util.LinkedList;

public class _2127_MaximumEmployeesToBeInvitedToAMeeting {
    public int maximumInvitations(int[] favorite) {
        int n = favorite.length;

        int[] inDegree = new int[n];
        for (int i = 0; i < n; i++) {
            inDegree[favorite[i]]++;
        }

        LinkedList<Integer> pq = new LinkedList<>();
        int[] longestPathTo = new int[n];
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                pq.add(i);
                longestPathTo[i] = 1;
            }
        }

        boolean[] visited = new boolean[n];
        while (!pq.isEmpty()) {
            int current = pq.poll();
            visited[current] = true;
            longestPathTo[favorite[current]] = Math.max(longestPathTo[favorite[current]], longestPathTo[current] + 1);

            if (--inDegree[favorite[current]] == 0) {
                pq.add(favorite[current]);
            }
        }

        int doublesTotal = 0;
        int max = 0;
        for (int i = 0; i < n; i++) {
            if (visited[i]) {
                continue;
            }

            int length = 1;
            int current = i;
            visited[i] = true;
            while (!visited[favorite[current]]) {
                visited[current] = true;
                length++;
                current = favorite[current];
            }

            if (length == 2) {
                doublesTotal += Math.max(1, longestPathTo[i]) + Math.max(1, longestPathTo[current]);
                max = Math.max(max, doublesTotal);
            } else {
                max = Math.max(max, length);
            }
        }

        return max;
    }
}
