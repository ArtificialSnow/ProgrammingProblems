package problems.medium;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class _1387_SortIntegersByThePowerValue {
    public int getKth(int lo, int hi, int k) {
        Map<Integer, Integer> stepsMap = new HashMap<>();
        stepsMap.put(1, 1);

        PriorityQueue<Node> pq = new PriorityQueue<>((a, b) -> {
            if (a.steps == b.steps) {
                return Integer.compare(a.num, b.num);
            }
            return Integer.compare(a.steps, b.steps);
        });

        for (int i = lo; i <= hi; i++) {
            pq.add(new Node(i, solve(stepsMap, i)));
        }

        for (int i = 1; i < k; i++) {
            pq.poll();
        }

        return pq.poll().num;
    }

    private int solve(Map<Integer, Integer> stepsMap, int n) {
        if (stepsMap.containsKey(n)) return stepsMap.get(n);

        int steps = 1 + ((n % 2 == 0) ? solve(stepsMap, n / 2) : solve(stepsMap, 3 * n + 1));
        stepsMap.put(n, steps);
        return steps;
    }

    static class Node {
        int num, steps;

        public Node(int num, int steps) {
            this.num = num;
            this.steps = steps;
        }
    }
}
