package search;

public class Search {

    // Find index of target or returns -1 if target does not exist
    public int binarySearch(int[] arr, int target) {
        int low = 0, high = arr.length-1, mid;
        while (low < high) {
            mid = (low+high)/2;
            if (arr[mid] < target) {
                low = mid + 1;
            } else {
                high = mid;
            }
        }

        return arr[low] == target ? low : -1;
    }
}
