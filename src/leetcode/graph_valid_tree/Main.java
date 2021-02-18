package leetcode.graph_valid_tree;

import java.util.ArrayList;
import java.util.List;

class Solution {
    private boolean[] visited;
    private List<List<Integer>> connections;

    private boolean dfs(int cur, int prev) {
        visited[cur] = true;

        boolean ret = true;
        for (Integer next : connections.get(cur)) {
            if (next != prev) {
                if (visited[next]) ret = false;
                else ret &= dfs(next, cur);
            }
        }

        return ret;
    }

    public boolean validTree(int n, int[][] edges) {
        visited = new boolean[n];
        connections = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            connections.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            connections.get(edge[0]).add(edge[1]);
            connections.get(edge[1]).add(edge[0]);
        }

        int cnt = 0;
        boolean ret = true;
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                ret &= dfs(i, -1);
                cnt++;
            }
        }

        return cnt == 1 && ret;
    }
}

public class Main {
}
