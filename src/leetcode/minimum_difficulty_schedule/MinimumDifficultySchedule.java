package leetcode.minimum_difficulty_schedule;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Scanner;

class Solution {
    private int[] difficulties;
    private static int MAX = 123456789;
    private int[][] cache;

    // 주어진 start, leftDays에서 최소한의 값을 반환
    private int solve(int start, int leftDays) {
        int l = difficulties.length;
        if ((start != l && leftDays == 0) ||
                (start == l && leftDays != 0)) {
            return MAX;
        }

        if (cache[start][leftDays] != 0) {
            return cache[start][leftDays];
        }

        if (leftDays == 1) {
            int max = 0;
            for (int i = start; i < l; ++i) {
                max = Math.max(max, difficulties[i]);
            }
            return max;
        }

        int currentCost = difficulties[start];
        int ret = MAX;
        for (int i = 1; i + start < l; i++) {
            ret = Math.min(ret, solve(start + i, leftDays - 1) + currentCost);
            currentCost = Math.max(currentCost, difficulties[i + start]);
        }

        cache[start][leftDays] = ret;
        return ret;
    }

    public int minDifficulty(int[] jobDifficulty, int d) {
        cache = new int[jobDifficulty.length][d + 1];
        difficulties = jobDifficulty;
        int ret = solve(0, d);
        return ret == MAX ? -1 : ret;
    }
}

public class MinimumDifficultySchedule {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/minimum_difficulty_schedule/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            int[] difficulties = Arrays.stream(scanner.nextLine().split(" "))
                    .mapToInt(it -> Integer.parseInt(it))
                    .toArray();
            int day = Integer.parseInt(scanner.nextLine());
            System.out.println(new Solution().minDifficulty(difficulties, day));
        }
    }
}
