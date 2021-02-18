package leetcode.cours_schedule_2;

import utils.Utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    List<List<Integer>> graph;
    boolean[] finished;
    boolean[] visited;
    boolean isPossible;
    List<Integer> finishedOrder;

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
        finishedOrder.add(cur);
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        visited = new boolean[numCourses];
        finished = new boolean[numCourses];
        graph = new ArrayList<>();
        isPossible = true;
        finishedOrder = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] prerequisite : prerequisites) {
                graph.get(prerequisite[1]).add(prerequisite[0]);
        }

        for (int i = 0; i < numCourses; i++) {
            if (!visited[i]) {
                dfs(i);
            }
        }

        Collections.reverse(finishedOrder);
        int [] ret = {};
        if(isPossible){
            ret = finishedOrder.stream().mapToInt(Integer::intValue).toArray();
        }
        return ret;
    }
}

public class Main {
    public static void main(String[] args) {
        int numCourses = 2;
        int[][] prerequisites = {{1, 0}};
        System.out.println(new Solution().findOrder(numCourses, prerequisites));
    }
}
