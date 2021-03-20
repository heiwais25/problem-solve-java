package baekjoon.prob_11659;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class FenwickTree {
        int[] memory;

        FenwickTree(int n) {
            memory = new int[n + 1];
        }

        int sum(int p) {
            p += 1;
            int ret = 0;
            while (p > 0) {
                ret += memory[p];
                p &= (p - 1);
            }
            return ret;
        }

        void add(int p, int val) {
            p += 1;

            while (p < memory.length) {
                memory[p] += val;
                p += (p & -p);
            }
        }
    }

    private static class SegmentTree {
        int[] memory;

        SegmentTree(int[] arr) {
            memory = new int[arr.length * 4];
            init(arr, 0, arr.length - 1, 1);
        }

        // return the sum over range
        private int init(int[] arr, int left, int right, int node) {
            if (left == right) return memory[node] = arr[left];

            int mid = (left + right) / 2;
            int leftSum = init(arr, left, mid, node * 2);
            int rightSum = init(arr, mid + 1, right, node * 2 + 1);

            return memory[node] = leftSum + rightSum;
        }

        int query(int left, int right, int nodeLeft, int nodeRight, int node) {
            if (right < nodeLeft || left > nodeRight) return 0;
            if (left <= nodeLeft && right >= nodeRight) return memory[node];

            int mid = (nodeLeft + nodeRight) / 2;
            return query(left, right, nodeLeft, mid, node * 2) +
                    query(left, right, mid + 1, nodeRight, node * 2 + 1);
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_11659/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String[] parts = reader.readLine().split(" ");
        int N = Integer.parseInt(parts[0]);
        int M = Integer.parseInt(parts[1]);

        FenwickTree tree = new FenwickTree(N);
        parts = reader.readLine().split(" ");
        for (int i = 0; i < N; ++i) {
            tree.add(i, Integer.parseInt(parts[i]));
        }

        for (int i = 0; i < M; ++i) {
            parts = reader.readLine().split(" ");
            int from = Integer.parseInt(parts[0]) - 1;
            int to = Integer.parseInt(parts[1]) - 1;
            System.out.println(tree.sum(to) - tree.sum(from - 1));
        }
    }
}
