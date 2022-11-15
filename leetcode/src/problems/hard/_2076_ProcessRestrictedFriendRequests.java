package problems.hard;

public class _2076_ProcessRestrictedFriendRequests {
    static class DisjointSet {

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

    public boolean[] friendRequests(int n, int[][] restrictions, int[][] requests) {
        boolean[] responses = new boolean[requests.length];
        DisjointSet ds = new DisjointSet(n);

        outer:
        for (int i = 0; i < requests.length; i++) {
            if (ds.connected(requests[i][0], requests[i][1])) {
                responses[i] = true;
                continue;
            }

            int rootA = ds.getRoot(requests[i][0]);
            int rootB = ds.getRoot(requests[i][1]);
            for (int[] restriction : restrictions) {
                int restrictionsRootA = ds.getRoot(restriction[0]);
                int restrictionsRootB = ds.getRoot(restriction[1]);

                if ((rootA == restrictionsRootA && rootB == restrictionsRootB) || (rootB == restrictionsRootA && rootA == restrictionsRootB)) {
                    continue outer;
                }
            }

            ds.union(requests[i][0], requests[i][1]);
            responses[i] = true;
        }

        return responses;
    }
}
