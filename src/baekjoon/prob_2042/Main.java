package baekjoon.prob_2042;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static class SegmentTree {
        long[] buffer;

        SegmentTree(long[] arr) {
            buffer = new long[arr.length * 4];
            init(arr, 0, arr.length - 1, 1);
        }

        private long init(long[] arr, int left, int right, int node) {
            if (left == right) return buffer[node] = arr[left];

            int mid = (left + right) / 2;

            return buffer[node] = init(arr, left, mid, node * 2) +
                    init(arr, mid + 1, right, node * 2 + 1);
        }

        long query(long left, long right, int nodeLeft, int nodeRight, int node) {
            if (right < nodeLeft || left > nodeRight) return 0;
            if (left <= nodeLeft && right >= nodeRight) return buffer[node];

            int mid = (nodeLeft + nodeRight) / 2;

            return query(left, right, nodeLeft, mid, node * 2)
                    + query(left, right, mid + 1, nodeRight, node * 2 + 1);
        }

        long update(long idx, long val, int left, int right, int node) {
            if (idx < left || idx > right) return buffer[node];
            if (left == right) return buffer[node] = val;

            int mid = (left + right) / 2;

            return buffer[node] = update(idx, val, left, mid, node * 2)
                    + update(idx, val, mid + 1, right, node * 2 + 1);

        }
    }

    private static int[] parseIntArray(String str) {
        String[] parts = str.split(" ");
        int[] intArr = new int[parts.length];
        for (int i = 0; i < intArr.length; ++i) {
            intArr[i] = Integer.parseInt(parts[i]);
        }

        return intArr;
    }

    private static long[] parseLongArray(String str) {
        String[] parts = str.split(" ");
        long[] longArr = new long[parts.length];
        for (int i = 0; i < longArr.length; ++i) {
            longArr[i] = Long.parseLong(parts[i]);
        }

        return longArr;
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_2042/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int[] inputArr = parseIntArray(reader.readLine());
        int N = inputArr[0], M = inputArr[1], K = inputArr[2];

        long[] arr = new long[N];
        for (int i = 0; i < N; ++i) {
            arr[i] = Long.parseLong(reader.readLine());
        }
        SegmentTree tree = new SegmentTree(arr);

        for (int i = 0; i < M + K; ++i) {
            long[] longArr = parseLongArray(reader.readLine());
            long operation = longArr[0];
            if (operation == 1) {
                tree.update(longArr[1] - 1, longArr[2], 0, arr.length - 1, 1);
            } else {
                System.out.println(tree.query(longArr[1] - 1, longArr[2] - 1, 0, arr.length - 1, 1));
            }
        }
    }
}
