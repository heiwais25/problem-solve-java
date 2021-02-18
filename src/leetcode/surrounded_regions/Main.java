package leetcode.surrounded_regions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class Solution {
    static class Position {
        final int row;
        final int col;


        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    private char[][] board;
    private boolean[][] visited;
    private final int[][] directions = {
            {1, 0},
            {0, 1},
            {-1, 0},
            {0, -1},
    };

    private List<Position> history;

    private boolean isBorder(int row, int col) {
        return row == 0 || row == board.length - 1 || col == 0 || col == board[0].length - 1;
    }

    private boolean isValid(int row, int col) {
        return row >= 0 && row <= board.length - 1 && col >= 0 && col <= board[0].length - 1;
    }

    // If finished dfs without touching border
    private boolean dfs(int row, int col) {
        history.add(new Position(row, col));
        visited[row][col] = true;
        boolean ret = !isBorder(row, col);

        for (int[] direction : directions) {
            int newRow = row + direction[0];
            int newCol = col + direction[1];

            if (isValid(newRow, newCol) &&
                    !visited[newRow][newCol] &&
                    board[newRow][newCol] == 'O') {
                ret &= dfs(newRow, newCol);
            }
        }

        return ret;
    }

    private void unset() {
        for (Position pos : history) {
            this.board[pos.row][pos.col] = 'X';
        }
    }

    public void solve(char[][] board) {
        if (board == null || board.length == 0 || board[0].length == 0) {
            return;
        }
        this.board = board;
        this.visited = new boolean[board.length][board[0].length];
        // find adjacent cells

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (!visited[i][j] && board[i][j] == 'O') {
                    history = new ArrayList<>();
                    boolean valid = dfs(i, j);
                    if (valid) {
                        unset();
                    }
                }
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        char[][] board = {
                {'X', 'X', 'X', 'X'},
                {'X', 'O', 'O', 'X'},
                {'X', 'X', 'O', 'X'},
                {'X', 'O', 'X', 'X'},
        };
        new Solution().solve(board);
        System.out.println(Arrays.deepToString(board));
    }
}
