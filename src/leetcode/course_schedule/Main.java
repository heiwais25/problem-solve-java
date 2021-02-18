package leetcode.course_schedule;

import java.util.ArrayList;
import java.util.List;

class Solution {
    List<List<Integer>> graph;
    boolean[] finished;
    boolean[] visited;
    boolean isPossible;

    private void dfs(int cur) {
        visited[cur] = true;

        for (Integer next : graph.get(cur)) {
            if (!visited[next]) {
                dfs(next);
            } else if (!finished[next]) {
                isPossible = false;
            }
        }

        finished[cur] = true;
    }

    public boolean canFinish(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        finished = new boolean[numCourses];
        graph = new ArrayList<>();
        isPossible = true;

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
            graph.get(prerequisite[0]).add(prerequisite[1]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        return isPossible;
    }
}

public class Main {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{0, 1}, {1, 0}};
        System.out.println(new Solution().canFinish(numCourses, prerequisites));
    }
}
