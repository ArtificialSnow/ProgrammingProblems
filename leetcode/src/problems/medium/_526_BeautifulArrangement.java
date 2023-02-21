package problems.medium;

public class _526_BeautifulArrangement {
    public int countArrangement(int n) {
        int[] currentSolution = new int[n+1];
        for (int i = 1; i <= n; i++) {
            currentSolution[i] = i;
        }

        return solve(currentSolution, 1);
    }

    private int solve(int[] currentSolution, int index) {
        if (index == currentSolution.length) return 1;

        int total = 0;
        for (int i = index; i < currentSolution.length; i++) {
            swap(currentSolution, index, i);
            if ((currentSolution[index] % index == 0 || index % currentSolution[index] == 0)) {
                total += solve(currentSolution, index+1);
            }
            swap(currentSolution, index, i);
        }

        return total;
    }

    private void swap(int[] arr, int a, int b) {
        int temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
    }
}
