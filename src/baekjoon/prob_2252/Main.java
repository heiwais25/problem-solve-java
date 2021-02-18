package baekjoon.prob_2252;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Main {
    private static List<List<Integer>> graph = new ArrayList<>();
    private static boolean[] visited;
    private static List<Integer> finished = new ArrayList<>();

    private static void dfs(int cur) {
        visited[cur] = true;

        for (Integer next : graph.get(cur)) {
            if (!visited[next]) {
                dfs(next);
            }
        }

        finished.add(cur + 1);
    }

    public static void main(String[] args) throws FileNotFoundException {
        // System.setIn(new FileInputStream("resources/baekjoon/2252.txt"));
        Scanner sc = new Scanner(System.in);

        int n = sc.nextInt();
        int m = sc.nextInt();
        visited = new boolean[n];
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int i = 0; i < m; i++) {
            graph.get(sc.nextInt() - 1).add(sc.nextInt() - 1);
        }

        for (int i = 0; i < graph.size(); ++i) {
            if (!visited[i]) dfs(i);
        }

        Collections.reverse(finished);

        System.out.println(
                finished.stream()
                        .map(String::valueOf)
                        .collect(Collectors
                                .joining(" ")));

    }
}
