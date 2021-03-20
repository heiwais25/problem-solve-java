package baekjoon.prob_9248;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;

public class Main {
    private static Integer[] getSuffixArray(String str) {
        int n = str.length();
        int[] group = new int[n + 1];

        Integer[] perm = new Integer[n];
        for (int i = 0; i < n; ++i) perm[i] = i;
        for (int i = 0; i < n; ++i) group[i] = str.charAt(i);
        group[n] = -1;

        int t = 1;

        while (t < n) {
            int compT = t;
            int[] compGroup = group;
            Comparator<Integer> comparator = (a, b) -> {
                if (compGroup[a] != compGroup[b]) return compGroup[a] - compGroup[b];
                else return compGroup[a + compT] - compGroup[b + compT];
            };

            Arrays.sort(perm, comparator);

            t *= 2;
            if (t >= n) break;

            int[] nextGroup = new int[n + 1];
            nextGroup[perm[0]] = 0;
            nextGroup[n] = -1;
            for (int i = 1; i < n; ++i) {
                if (comparator.compare(perm[i], perm[i - 1]) == 0) {
                    nextGroup[perm[i]] = nextGroup[perm[i - 1]];
                } else {
                    nextGroup[perm[i]] = nextGroup[perm[i - 1]] + 1;
                }
            }

            group = nextGroup;
        }

        return perm;
    }

    private static int[] getLCP(String str, Integer[] suffixArray) {
        int n = suffixArray.length;
        int[] rank = new int[n];
        int[] lcp = new int[n];
        for (int i = 0; i < n; ++i) {
            rank[suffixArray[i]] = i;
        }

        int len = 0;
        for (int i = 0; i < n; ++i) {
            int curRank = rank[i];
            if (curRank > 0) {
                int compIdx = suffixArray[curRank - 1];

                while (i + len < n && compIdx + len < n &&
                        str.charAt(i + len) == str.charAt(compIdx + len))
                    len++;

                lcp[curRank] = len;

                if (len > 0) len--;
            }
        }

        return lcp;
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_9248/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String target = reader.readLine();
        Integer[] suffixArray = getSuffixArray(target);
        int[] lcp = getLCP(target, suffixArray);
        lcp[0] = -1;

        StringBuilder builder = new StringBuilder();
        for (int val : suffixArray) {
            builder.append(val + 1);
            builder.append(" ");
        }
        String suffixStr = builder.toString();

        builder = new StringBuilder();
        builder.append('x');
        for (int i = 1; i < lcp.length; ++i) {
            builder.append(" ");
            builder.append(lcp[i]);
        }

        System.out.println(suffixStr);
        System.out.println(builder.toString());
//
//        for (int i = 0; i < suffixArray.length; ++i) {
//            System.out.print(suffixArray[i] + 1);
//            System.out.print(" ");
//        }
//        System.out.println();
//
//        System.out.print("x");
//        for (int i = 0; i < lcp.length; ++i) {
//            System.out.print(" ");
//            System.out.print(lcp[i]);
//        }
//        System.out.println();
    }
}

