package baekjoon.prob_1305;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

@SuppressWarnings("DuplicatedCode")
public class Main {
    private static int solve(String str, int L) {
        int n = str.length();
        int[] pi = new int[n];
        int begin = 1;
        int matched = 0;

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

        return L - pi[n - 1];
    }


    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_1305/input.txt"));

        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        int L = Integer.parseInt(reader.readLine());
        String line = reader.readLine();

        System.out.println(solve(line, L));
    }
}
