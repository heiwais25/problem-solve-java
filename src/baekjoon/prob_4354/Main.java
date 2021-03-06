package baekjoon.prob_4354;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    private static int solve(String str) {
        int n = str.length();
        int[] pi = new int[n];
        int begin = 1;
        int matched = 1;

        while (begin + matched < n) {
            if (str.charAt(begin + matched) == str.charAt(matched)) {
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

        int chunkLength = n - pi[n - 1];
        return n % chunkLength == 0 ? n / chunkLength : 1;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_4354/input.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        while (true) {
            String line = reader.readLine();
            if (line.equals(".")) {
                break;
            }

            System.out.println(solve(line));
        }
    }
}
