package leetcode.robot_bounded_in_circle;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Scanner;

class Solution {
    int[][] dirs = {
            {0, 1}, {1, 0}, {0, -1}, {-1, 0},
    };

    static class Position {
        final int x;
        final int y;
        final int dirIdx;

        Position(int x, int y, int dirIdx) {
            this.x = x;
            this.y = y;
            this.dirIdx = dirIdx;
        }

        int distanceFrom(Position target){
            return Math.abs(y - target.y) + Math.abs(x - target.x);
        }
    }

    private Position move(Position lastPosition, String instructions) {
        int curY = lastPosition.y;
        int curX = lastPosition.x;
        int dirIdx = lastPosition.dirIdx;

        char[] instructionArray = instructions.toCharArray();
        for (char cur : instructionArray) {
            if (cur == 'G') {
                curX += dirs[dirIdx][0];
                curY += dirs[dirIdx][1];
            } else if (cur == 'L') {
                dirIdx--;
            } else if (cur == 'R') {
                dirIdx++;
            }

            if (dirIdx < 0) {
                dirIdx = 4 + dirIdx;
            } else if (dirIdx >= 4) {
                dirIdx = dirIdx - 4;
            }
        }

        return new Position(curX, curY, dirIdx);
    }

    public boolean isRobotBounded(String instructions) {
        Position pos = new Position(0, 0, 0);
        Position after = move(pos, instructions);
        return (after.y == 0 && after.x == 0) || after.dirIdx != 0;
    }
}

public class RobotBoundedInCircle {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/robot_bounded_in_circle/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            String instruction = scanner.nextLine();
            System.out.println(new Solution().isRobotBounded(instruction));
        }
    }
}
