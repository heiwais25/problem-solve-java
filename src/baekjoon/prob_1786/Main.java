package baekjoon.prob_1786;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int[] getPartialMatch(String N) {
        int n = N.length();
        int[] pi = new int[n];
        int begin = 1;
        int matched = 0;

        while (begin + matched < n) {
            if (N.charAt(begin + matched) == N.charAt(matched)) {
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


    private static List<Integer> kmpSearch(String H, String N) {
        int m = H.length();
        int n = N.length();
        int[] pi = getPartialMatch(N);
        int begin = 0;
        int matched = 0;
        List<Integer> ret = new ArrayList<>();

        while (begin <= m - n) {
            if (matched < n && H.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                if (matched == n) {
                    ret.add(begin + 1);
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

        return ret;
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_1786/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String H = reader.readLine();
        String N = reader.readLine();
        List<Integer> ret = kmpSearch(H, N);
        System.out.println(ret.size());
        System.out.println(ret.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}
