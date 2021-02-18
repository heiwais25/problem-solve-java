package leetcode.number_of_connected_components;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited;
    private List<List<Integer>> connections;

    private void dfs(int cur) {
        visited[cur] = true;

        for (Integer next : connections.get(cur)) {
            if (!visited[next]) {
                dfs(next);
            }
        }
    }

    public int countComponents(int n, int[][] edges) {
        // Prepare
        visited = new boolean[n];
        connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            connections.get(edge[0]).add(edge[1]);
            connections.get(edge[1]).add(edge[0]);
        }

        // Run dfs
        int cnt = 0;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }
        return cnt;
    }
}

public class Main {
    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {{0, 1}, {1, 2}, {3, 4}};
        System.out.println(new Solution().countComponents(n, edges));
    }
}
