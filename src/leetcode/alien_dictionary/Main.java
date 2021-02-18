package leetcode.alien_dictionary;

import java.util.*;
import java.util.stream.Collectors;

class Solution {
    private List<List<Integer>> connections;
    private final Map<Character, Boolean> charMaps = new HashMap<>();
    private final int NUM_ALPHA = 26;


    private void buildConnection(String[] words) {
        connections = new ArrayList<>();
        for (int i = 0; i < NUM_ALPHA; ++i) {
            connections.add(new ArrayList<>());
        }

        for (int cur = 1; cur < words.length; cur++) {
            int prev = cur - 1;
            int length = Math.min(words[cur].length(), words[prev].length());
            for (int i = 0; i < length; i++) {
                char prevChar = words[prev].charAt(i);
                char curChar = words[cur].charAt(i);
                if (prevChar != curChar) {
                    connections.get(prevChar - 'a').add(curChar - 'a');
                    break;
                }
            }
            if (!words[prev].equals(words[cur]) && words[prev].startsWith(words[cur])) {
                isValid = false;
            }
        }

        for (String word : words) {
            for (int i = 0; i < word.length(); i++) {
                charMaps.put(word.charAt(i), true);
            }
        }
    }

    private List<Integer> history;
    private boolean[] visited;
    private boolean isValid;
    private boolean[] finished;

    private void dfs(int cur) {
        visited[cur] = true;
        for (Integer next : connections.get(cur)) {
            if (!visited[next]) {
                dfs(next);
            } else if (!finished[next]) {
                isValid = false;
            }
        }

        finished[cur] = true;
        history.add(cur);
    }

    public String alienOrder(String[] words) {
        isValid = true;
        buildConnection(words);
        history = new ArrayList<>();
        visited = new boolean[NUM_ALPHA];
        finished = new boolean[NUM_ALPHA];

        for (Character val : charMaps.keySet()) {
            int intVal = val - 'a';
            if (!visited[intVal]) {
                dfs(intVal);
            }
        }

        Collections.reverse(history);
        List<Character> historyChars = history.stream()
                .map(it -> (char) ('a' + it))
                .collect(Collectors.toList());
        for (Character val : historyChars) {
            charMaps.remove(val);
        }

        historyChars.addAll(charMaps.keySet());

        return isValid ? historyChars.stream()
                .map(String::valueOf)
                .collect(Collectors.joining()) : "";
    }
}

public class Main {
    public static void main(String[] args) {
//        String[] words = {"wrt", "wrf", "er", "ett", "rftt"};
//        String[] words = {"z", "z"};
        String[] words = {"abc", "ab"};
        System.out.println(new Solution().alienOrder(words));
    }
}
