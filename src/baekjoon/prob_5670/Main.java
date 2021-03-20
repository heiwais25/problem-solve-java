package baekjoon.prob_5670;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {

    private static class TrieNode {
        Map<Character, TrieNode> children = new HashMap<>();
        boolean isTerminal = false;

        // 100000 * 23
        void insert(String str, int idx) {
            if (str.length() == idx) {
                isTerminal = true;
                return;
            }

            children.computeIfAbsent(str.charAt(idx), c -> new TrieNode())
                    .insert(str, idx + 1);
        }

        int getTypeCount(String str, int idx) {
            if (str.length() == idx) return 0;

            int curTypeCount = idx != 0 && children.size() == 1 ? 0 : 1;
            if(curTypeCount == 0 && isTerminal) curTypeCount = 1;

            return children
                    .get(str.charAt(idx))
                    .getTypeCount(str, idx + 1) + curTypeCount;
        }
    }

    public static void main(String[] args) throws IOException {
        System.setIn(new FileInputStream("./src/baekjoon/prob_5670/input.txt"));
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        String wordCountStr;
        while ((wordCountStr = reader.readLine()) != null) {
            int N = Integer.parseInt(wordCountStr);
            List<String> words = new ArrayList<>();
            TrieNode root = new TrieNode();
            for (int i = 0; i < N; ++i) {
                String str = reader.readLine();
                root.insert(str, 0);
                words.add(str);
            }

            int totalTypeCount = 0;
            for (String word : words) {
                totalTypeCount += root.getTypeCount(word, 0);
            }

            System.out.printf("%.2f%n", (float) totalTypeCount / N);
        }
    }
}
