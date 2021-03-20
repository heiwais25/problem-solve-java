package baekjoon.prob_1786;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Main {

    private static int[] getPartialMatchTable(String N) {
        int n = N.length();
        int begin = 1, matched = 0;
        int[] pi = new int[n];
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


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_1786/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        String H = reader.readLine();
        String N = reader.readLine();

        int[] pi = getPartialMatchTable(N);
        int begin = 0;
        int matched = 0;
        int h = H.length();
        int n = N.length();
        List<Integer> posList = new ArrayList<>();

        while (begin <= h - n) {
            if (matched < n && H.charAt(begin + matched) == N.charAt(matched)) {
                matched++;
                if (matched == n) {
                    posList.add(begin + 1);
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

        System.out.println(posList.size());
        System.out.println(posList.stream().map(String::valueOf).collect(Collectors.joining(" ")));
    }
}