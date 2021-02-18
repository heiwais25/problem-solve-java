package leetcode.number_of_island;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.util.Stack;

class Solution {
    class Position {
        final int row;
        final int col;

        Position(int row, int col) {
            this.row = row;
            this.col = col;
        }
    }

    // mark grid to 0 after
    private void unsetRecursive(char[][] grid, int row, int col) {
        // mark all possible
        // dfs
        Stack<Position> stack = new Stack<>();
        stack.push(new Position(row, col));
        while (!stack.isEmpty()) {
            Position pos = stack.pop();
            grid[pos.row][pos.col] = '0';

            if (pos.row > 0 && grid[pos.row - 1][pos.col] == '1')
                stack.push(new Position(pos.row - 1, pos.col));
            if (pos.col > 0 && grid[pos.row][pos.col - 1] == '1')
                stack.push(new Position(pos.row, pos.col - 1));
            if (pos.row < grid.length - 1 && grid[pos.row + 1][pos.col] == '1')
                stack.push(new Position(pos.row + 1, pos.col));
            if (pos.col < grid[0].length - 1 && grid[pos.row][pos.col + 1] == '1')
                stack.push(new Position(pos.row, pos.col + 1));
        }
    }

    public int numIslands(char[][] grid) {
        int islandNumber = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if(grid[i][j] == '1'){
                    islandNumber++;
                    unsetRecursive(grid, i, j);
                }
            }
        }

        return islandNumber;
    }
}

public class NumberOfIsland {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/number_of_island/input.txt"));
        Scanner scanner = new Scanner(System.in);
        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            int lineCount = Integer.parseInt(scanner.nextLine());
            char[][] grid = new char[lineCount][];
            for (int i = 0; i < lineCount; i++) {
                String line = scanner.nextLine();
                char[] chars = line.toCharArray();
                grid[i] = chars;
            }
            System.out.println(new Solution().numIslands(grid));
        }
    }
}
