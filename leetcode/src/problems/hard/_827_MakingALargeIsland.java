package problems.hard;

import java.util.*;

public class _827_MakingALargeIsland {
    public int largestIsland(int[][] grid) {
        int[] componentSizes = new int[grid.length * grid.length + 2];

        int max = 0;
        int id = 2;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 1) {
                    dfs(grid, componentSizes, id, i, j);
                    if(componentSizes[id] > max){
                        max = componentSizes[id];
                    }
                    id++;
                }
            }
        }

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid.length; j++) {
                if (grid[i][j] == 0) {
                    max = Math.max(max, getNeighborSum(grid, componentSizes, i, j) + 1);
                }
            }
        }

        return max;
    }

    private void dfs(int[][] grid, int[] componentSizes, int id, int i, int j) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) return;
        grid[i][j] = id;
        componentSizes[id]++;
        dfs(grid, componentSizes, id, i-1, j);
        dfs(grid, componentSizes, id, i, j-1);
        dfs(grid, componentSizes, id, i+1, j);
        dfs(grid, componentSizes, id, i, j+1);
    }

    private int getNeighborSum(int[][] grid, int[] componentSizes, int i, int j) {
        int[] ids = new int[4];
        int sum = 0;
        if(i-1 >= 0){
            ids[0] = grid[i-1][j];
        }
        if(i+1 < grid.length){
            ids[1] = grid[i+1][j];
        }
        if(j-1 >= 0){
            ids[2] = grid[i][j-1];
        }
        if(j+1 < grid.length){
            ids[3] = grid[i][j+1];
        }
        sum = componentSizes[ids[0]];
        if(ids[1] != ids[0]){
            sum += componentSizes[ids[1]];
        }
        if(ids[2] != ids[0] && ids[2] != ids[1]){
            sum += componentSizes[ids[2]];
        }
        if(ids[3] != ids[0] && ids[3] != ids[1] && ids[3] != ids[2]){
            sum += componentSizes[ids[3]];
        }
        return sum;
    }
}
