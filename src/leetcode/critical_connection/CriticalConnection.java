package leetcode.critical_connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Solution {
    Stack<Integer> stack;
    /** boolean[] onStack; (directed graph only) **/
    List<List<Integer>> graph;
    int time = 0;// 1-indexed
    int[] link;// the time when a node be visited
    int[] lowlink;// the earliest node a node can reach in dfs
    /**Set<Set<Integer>> SCCs; contains SCCs, not suitable here**/
    int[] strongCC;// record the index of the SCC a node belongs to
    int sccIdx = 0;// 1-indexed
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        stack = new Stack();
        /** onStack = new boolean[n]; (directed graph only) **/
        graph = new ArrayList();
        link = new int[n];
        lowlink = new int[n];
        strongCC = new int[n];

        // generate graph
        for (int i = 0; i < n; i ++) {
            graph.add(new ArrayList());
        }
        for (List<Integer> ls: connections) {
            graph.get(ls.get(0)).add(ls.get(1));
            // reverse direction (undirected graph only)
            graph.get(ls.get(1)).add(ls.get(0));
        }

        // traverse
        for (int i = 0; i < n; i ++) {
            if (link[i] == 0) {// i unvisited
                strongConnect(i, -1);
            }
        }

        // check every edge's validity
        List<List<Integer>> ans = new ArrayList();
        for (List<Integer> ls: connections) {
            // two ends do not belong to the same SCC
            if (strongCC[ls.get(0)] != strongCC[ls.get(1)]) {
                ans.add(ls);
            }
        }

        return ans;
    }

    // dfs
    public void strongConnect(int i, int prev) {// prev is the previous visited node (undirected graph only)
        lowlink[i] = link[i] =  ++ time;
        stack.push(i);
        /**onStack[i] = true; (directed graph only) **/

        for (Integer j: graph.get(i)) {
            if (j != prev) {// prevent one edge used twice
                if (link[j] == 0) {// j unvisited
                    strongConnect(j, i);
                    lowlink[i] = Math.min(lowlink[i], lowlink[j]);
                } else /**if (onStack[j]), (directed graph only) **/{// j visited
                    lowlink[i] = Math.min(lowlink[i], link[j]);
                }
            }
        }

        if (lowlink[i] == link[i]) {// found SCC
            sccIdx ++;
            while (stack.peek() != i) {
                int node = stack.pop();
                strongCC[node] = sccIdx;// set SCC index
                /** onStack[node] = false; for directed graph **/
            }

            strongCC[stack.pop()] = sccIdx;
        }
    }
}

public class CriticalConnection {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/critical_connection/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            int n = Integer.parseInt(scanner.nextLine());
            List<List<Integer>> connections = new ArrayList<>();
            for (int i = 0; i < n; i++) {
                int[] line = Arrays.stream(scanner.nextLine()
                        .split(" "))
                        .mapToInt(Integer::parseInt)
                        .toArray();
                List<Integer> edge = new ArrayList<>();
                for (int val : line) {
                    edge.add(val);
                }
                connections.add(edge);
            }
            List<List<Integer>> criticalConnections =
                    new Solution().criticalConnections(n, connections);

            System.out.println(criticalConnections);
        }
    }
}
