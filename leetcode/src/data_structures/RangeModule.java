package data_structures;

import java.util.TreeMap;

/**
 * RangeModule obj = new RangeModule();
 * obj.addRange(left,right);
 * boolean param_2 = obj.queryRange(left,right);
 * obj.removeRange(left,right);
 */

public class RangeModule {

    TreeMap<Integer, Integer> map;

    public RangeModule() {
        map = new TreeMap<>();
    }

    public void addRange(int left, int right) {
        if (right <= left) return;

        Integer higherKey = map.higherKey(left);
        Integer floorKey = map.floorKey(left);
        if (floorKey == null && higherKey == null) {
            map.put(left, right);
            return;
        }

        if (higherKey == null) {
            Integer leftEnd = map.get(floorKey);
            if (left <= leftEnd) {
                map.remove(floorKey);
                map.put(floorKey, Math.max(leftEnd, right));
                return;
            }

            map.put(left, right);
            return;
        }


        Integer tempRightEnd = null;
        while (map.higherKey(left) != null && map.higherKey(left) <= right) {
            higherKey = map.higherKey(left);
            tempRightEnd = map.get(higherKey);
            map.remove(higherKey);
        }

        Integer previousHighestRightEnd = tempRightEnd;
        if (previousHighestRightEnd != null) {
            right = Math.max(right, previousHighestRightEnd);
        }

        if (floorKey == null) {
            map.put(left, right);
            return;
        }

        Integer leftEnd = map.get(floorKey);
        if (left <= leftEnd) {
            map.remove(floorKey);
            map.put(floorKey, Math.max(leftEnd, right));
            return;
        }

        map.put(left, right);
    }

    public boolean queryRange(int left, int right) {
        if (right <= left) return false;

        Integer floorKey = map.floorKey(left);
        if (floorKey == null) {
            return false;
        }

        return map.get(floorKey) >= right;
    }

    public void removeRange(int left, int right) {
        if (right <= left) return;

        Integer higherKey = map.higherKey(left);
        Integer floorKey = map.floorKey(left);
        if (floorKey == null && higherKey == null) {
            return;
        }

        if (higherKey == null) {
            Integer leftEnd = map.get(floorKey);
            if (right < leftEnd) {
                map.put(right, leftEnd);
            }

            if (left == floorKey) {
                map.remove(floorKey);
                return;
            }

            map.remove(floorKey);
            map.put(floorKey, Math.min(left, leftEnd));
            return;
        }

        while (map.higherKey(left) != null && map.get(map.higherKey(left)) <= right) {
            map.remove(higherKey);
            higherKey = map.higherKey(left);
        }

        if (higherKey != null && right > higherKey) {
            Integer higherEnd = map.get(higherKey);
            map.remove(higherKey);
            map.put(right, higherEnd);
        }

        if (floorKey != null) {
            if (right < map.get(floorKey)) {
                map.put(right, map.get(floorKey));
            }

            if (left == floorKey) {
                map.remove(floorKey);
                return;
            }

            if (left < map.get(floorKey)) {
                map.remove(floorKey);
                map.put(floorKey, left);
            }
        }
    }
}
