package data_structures;

public class DisjointSet {

    private final int[] sets;
    private final int[] sizes;

    public DisjointSet(int elements) {
        sets = new int[elements];
        sizes = new int[elements];
        for(int i = 0; i < elements; i++) {
            sets[i] = i;
            sizes[i] = 1;
        }
    }

    public boolean connected(int a, int b) {
        return sets[a] == sets[b];
    }

    public void union(int a, int b) {
        int rootA = getRoot(a);
        int rootB = getRoot(b);
        if (rootA == rootB) {
            return;
        }

        if (sizes[rootA] < sizes[rootB]) {
            sets[rootA] = sets[rootB];
            sizes[rootB] += sizes[rootA];
        } else {
            sets[rootB] = sets[rootA];
            sizes[rootA] += sizes[rootB];
        }
    }

    public int getRoot(int a) {
        while(sets[a] != a) {
            sets[a] = sets[sets[a]] ;
            a = sets[a];
        }
        return a;
    }
}
