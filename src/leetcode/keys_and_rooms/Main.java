package leetcode.keys_and_rooms;

import java.util.List;

class Solution {
    private boolean[] visited;
    private List<List<Integer>> connections;

    private void dfs(int cur) {
        visited[cur] = true;

        for (Integer key : connections.get(cur)) {
            if (!visited[key]) {
                dfs(key);
            }
        }
    }

    public boolean canVisitAllRooms(List<List<Integer>> rooms) {
        visited = new boolean[rooms.size()];
        connections = rooms;

        int cnt = 0;
        for (int i = 0; i < rooms.size(); i++) {
            if (!visited[i]) {
                dfs(i);
                cnt++;
            }
        }

        return cnt == 1;
    }
}

public class Main {
}
