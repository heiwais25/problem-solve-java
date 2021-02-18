package leetcode.number_of_distinct_island;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

class Solution {
    private int[][] grid;
    private int MAX_SIZE = 50;
    private int[][] directions = {
            {0, 1},
            {1, 0},
            {0, -1},
            {-1, 0},
    };
    private boolean[][] visited;
    private Set<List<Integer>> islands;

    private boolean isValidPos(int row, int col){
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }

    private void dfs(int row, int col, int startRow, int startCol, boolean start, List<Integer> path) {
        // find
        if (visited[row][col]) {
            return;
        }

        path.add((row - startRow) * MAX_SIZE + (col - startCol));
        visited[row][col] = true;
        for (int i = 0; i < directions.length; i++) {
            int newCol = col + directions[i][0];
            int newRow = row + directions[i][1];
            if (isValidPos(newRow, newCol) && grid[newRow][newCol] == 1) {
                dfs(newRow, newCol, startRow, startCol, false, path);
            }
        }

        if (start) {
            islands.add(path);
        }
    }

    public int numDistinctIslands(int[][] grid) {
        islands = new HashSet<>();
        this.grid = grid;
        this.visited = new boolean[MAX_SIZE][MAX_SIZE];

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j] && grid[i][j] == 1) {
                    dfs(i, j, i, j, true, new ArrayList<>());
                }
            }
        }

        return islands.size();
    }
}

public class Main {
    public static void main(String[] args) {
        int[][] grid = {
                {1,1,0,0,0},
                {1,1,0,0,0},
                {0,0,0,1,1},
                {0,0,0,1,1},
        };

        System.out.println(new Solution().numDistinctIslands(grid));
    }
}
