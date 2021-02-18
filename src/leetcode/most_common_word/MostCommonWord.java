package leetcode.most_common_word;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class Solution {
    public String mostCommonWord(String paragraph, String[] banned) {
        Map<String, Integer> counts = new HashMap<>();

        char[] chars = paragraph.toCharArray();
        StringBuilder builder = new StringBuilder();

        for (char c : chars) {
            if (c >= 'a' && c <= 'z') {
                builder.append(c);
            } else if (c >= 'A' && c <= 'Z') {
                builder.append(Character.toString(c + ('a' - 'A')));
            } else {
                if(builder.length() > 0){
                    String word = builder.toString();
                    counts.put(word, counts.getOrDefault(word, 0) + 1);
                    builder = new StringBuilder();
                }
            }
        }
        if(builder.length() > 0){
            String word = builder.toString();
            counts.put(word, counts.getOrDefault(word, 0) + 1);
        }

        for (String ban : banned) {
            counts.remove(ban);
        }

        int max = -1;
        String ret = "";
        for (String key : counts.keySet()) {
            int cur = counts.get(key);
            if (cur > max) {
                max = cur;
                ret = key;
            }
        }

        return ret;
    }
}

public class MostCommonWord {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/most_common_word/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            String sentence = scanner.nextLine();
            String[] bannedWords = scanner.nextLine().split(",");
            System.out.println(new Solution().mostCommonWord(sentence, bannedWords));
        }

    }
}
