package leetcode.critical_connection;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Solution2 {
    private Stack<Integer> stack;
    private int sccNumber;
    private List<List<Integer>> graphs;
    private int[] scc;
    private boolean[] visited;
    private int[] link;
    private int[] unlink;
    private int linkCnt;
    private int n;

    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        this.n = n;
        this.sccNumber = 0;
        this.linkCnt = 0;
        this.stack = new Stack<>();
        this.scc = new int[n];
        this.link = new int[n];
        this.unlink = new int[n];
        this.visited = new boolean[n];

        for (int i = 0; i < n; i++) {
            graphs.add(new ArrayList<>());
        }
        // Save graph
        for (List<Integer> edge : connections) {
            graphs.get(edge.get(0)).add(edge.get(1));
            graphs.get(edge.get(1)).add(edge.get(0));
        }

        // Create SCC
        for (int i = 0; i < n; i++) {
            if (link[i] == 0) {
                strongConnect(i, -1);
            }
        }

        // From SCCs, find the connections between two SCC
        List<List<Integer>> criticals = new ArrayList<>();
        for(List<Integer> edge:connections){
            if(scc[edge.get(0)] != scc[edge.get(1)]){
                criticals.add(edge);
            }
        }
        return criticals;
    }

    // dfs
    public void strongConnect(int i, int prev) {// prev is the previous visited node (undirected graph only)
        link[i] = unlink[i] = ++linkCnt;
        stack.push(i);

        for (Integer next : graphs.get(i)) {
            if (next != prev) {
                if (link[next] == 0) {
                    strongConnect(next, i);
                }
                unlink[i] = Math.min(unlink[next], unlink[i]);
            }
        }

        if (link[i] == unlink[i]) {
            sccNumber++;
            while (true) {
                Integer cur = stack.pop();
                scc[cur] = sccNumber;
                if(cur == i){
                    break;
                }
            }
        }
    }
}

public class CriticalConnection2 {
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
                    new Solution2().criticalConnections(n, connections);

            System.out.println(criticalConnections);
        }
    }
}
