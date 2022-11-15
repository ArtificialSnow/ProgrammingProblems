package problems.hard;

public class _801_MinimumSwapsToMakeSequencesIncreasing {
    public int minSwap(int[] A, int[] B) {
        int n = A.length;

        int[] noSwap = new int[n];
        int[] swap = new int[n];
        noSwap[0] = 0;
        swap [0] = 1;

        for (int i = 1; i < n; i++) {
            swap[i] = Integer.MAX_VALUE;
            noSwap[i] = Integer.MAX_VALUE;

            //For us to be comparing A[i] with A[i-1] and B[i] with B[i-1] then either we must have not swapped last turn,
            //and not swapped this turn, or swapped last turn and swapped this turn.
            if (A[i-1] < A[i] && B[i-1] < B[i]) {
                noSwap[i] = noSwap[i-1];
                swap[i] = swap[i-1] + 1;
            }

            //For us to be comparing A[i] with B[i-1] and B[i] with A[i-1] then either we must have not swapped last turn,
            //and swapped this turn, or not swapped last turn and swapped this turn.
            if (B[i-1] < A[i] && A[i-1] < B[i]) {
                if (noSwap[i] > swap[i-1]) {
                    noSwap[i] = swap[i-1];
                }

                if (swap[i] > noSwap[i-1] + 1){
                    swap[i] = noSwap[i-1] + 1;
                }
            }
        }

        return Math.min(noSwap[n-1], swap[n-1]);
    }
}
