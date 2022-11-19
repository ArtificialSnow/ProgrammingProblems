package problems.hard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class _2050_ParallelCoursesIII {
    public int minimumTime(int n, int[][] relations, int[] time) {
        List<Integer>[] dependsOn = new ArrayList[n+1];
        for (int i = 1; i <= n; i++) {
            dependsOn[i] = new ArrayList<>();
        }

        int[] numDependents = new int[n+1];
        for (int i = 0; i < relations.length; i++) {
            dependsOn[relations[i][0]].add(relations[i][1]);
            numDependents[relations[i][1]]++;
        }

        Queue<Integer> pq = new LinkedList<>();
        int[] totalTimes = new int[n+1];
        for (int i = 1; i <= n; i++) {
            if (numDependents[i] == 0) {
                pq.add(i);
                totalTimes[i] = time[i-1];
            }
        }

        while (!pq.isEmpty()) {
            int curr = pq.poll();

            for (int depending : dependsOn[curr]) {
                totalTimes[depending] = Math.max(totalTimes[depending], totalTimes[curr] + time[depending-1]);
                if (--numDependents[depending] == 0) {
                    pq.add(depending);
                }
            }
        }

        int ans = 0;
        for (int i = 0; i <= n; i++) {
            ans = Math.max(ans, totalTimes[i]);
        }
        return ans;
    }
}
