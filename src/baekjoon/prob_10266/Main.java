package baekjoon.prob_10266;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

@SuppressWarnings("DuplicatedCode")
public class Main {
    private static int[] getPartialMatch(boolean[] arr) {
        int n = arr.length;
        int[] pi = new int[n];
        int begin = 1;
        int matched = 0;

        while (begin + matched < n) {
            if (arr[begin + matched] == arr[matched]) {
                matched++;
                pi[begin + matched - 1] = matched;
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }
        return pi;
    }

    private static boolean kmpSearch(boolean[] H, boolean[] N) {
        int m = H.length;
        int n = N.length;
        int[] pi = getPartialMatch(N);
        int begin = 0;
        int matched = 0;

        while (begin <= m - n) {
            if (matched < n && H[begin + matched] == N[matched]) {
                matched++;
                if (matched == n) {
                    return true;
                }
            } else {
                if (matched == 0) {
                    begin++;
                } else {
                    begin += matched - pi[matched - 1];
                    matched = pi[matched - 1];
                }
            }
        }

        return false;
    }

    private static boolean[] getNormalizedArr(int[] intArr) {
        boolean[] ret = new boolean[360000];
        for (int val : intArr) {
            ret[val] = true;
        }

        return ret;
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_10266/input.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(reader.readLine());
        int[] first = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        boolean[] normalizedFirst = getNormalizedArr(first);
        boolean[] normalizedFirstExtended = new boolean[normalizedFirst.length * 2];
        for (int i = 0; i < normalizedFirst.length; ++i) {
            normalizedFirstExtended[i] = normalizedFirst[i];
            normalizedFirstExtended[normalizedFirst.length + i] = normalizedFirst[i];
        }

        int[] second = Arrays.stream(reader.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        System.out.println(
                kmpSearch(
                        normalizedFirstExtended,
                        getNormalizedArr(second))
                        ? "possible" : "impossible"
        );
    }
}
