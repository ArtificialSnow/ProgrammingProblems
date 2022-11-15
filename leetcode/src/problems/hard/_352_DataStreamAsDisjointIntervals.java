package problems.hard;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class _352_DataStreamAsDisjointIntervals {
    private final TreeMap<Integer, Integer> map;

    public _352_DataStreamAsDisjointIntervals() {
        map = new TreeMap<>();
    }

    public void addNum(int val) {
        Integer leftStart = map.floorKey(val);
        Integer rightStart = map.higherKey(val);
        if (leftStart != null && map.get(leftStart) >= val-1) {
            if (map.get(leftStart) > val-1) {
                return;
            }

            map.put(leftStart, val);
            if (rightStart != null && rightStart == val+1) {
                map.put(leftStart, map.get(rightStart));
                map.remove(rightStart);
            }
        } else {
            map.put(val, val);
            if (rightStart != null && rightStart == val+1) {
                map.put(val, map.get(rightStart));
                map.remove(rightStart);
            }
        }
    }

    public int[][] getIntervals() {
        Set<Map.Entry<Integer, Integer>> entrySet = map.entrySet();
        if (entrySet.isEmpty()) {
            return new int[0][0];
        }

        int index = 0;
        int[][] ans = new int[entrySet.size()][2];
        for (Map.Entry<Integer, Integer> entry : entrySet) {
            ans[index][0] = entry.getKey();
            ans[index++][1] = entry.getValue();
        }

        return ans;
    }
}
