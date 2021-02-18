package leetcode.reorder_logs;

import utils.Utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

class Solution {
    boolean[] isDigit;
    String[] contents;
    String[] identifiers;

    private boolean isDigitString(String value){
        for(int i=0;i<value.length();++i){
            char curVal = value.charAt(i);
            if(curVal != ' '){
                if(curVal < '0' || curVal > '9'){
                    return false;
                }
            }
        }
        return true;
    }

    public String[] reorderLogFiles(String[] logs) {
        isDigit = new boolean[logs.length];
        contents = new String[logs.length];
        identifiers = new String[logs.length];

        Integer[] keys = new Integer[logs.length];
        // O(N)
        for (int i = 0; i < logs.length; i++) {
            int firstSpace = logs[i].indexOf(" ");
            contents[i] = logs[i].substring(firstSpace + 1);
            isDigit[i] = isDigitString(contents[i]);
            identifiers[i] = logs[i].substring(0, firstSpace);
            keys[i] = i;
        }

        // O(NlgN)
        return Arrays.stream(keys).sorted((Integer a, Integer b) -> {
            if(!isDigit[a] && isDigit[b]) return -1;
            if(isDigit[a] && !isDigit[b]) return 1;
            if(isDigit[a] && isDigit[b]) return 0;
            int ret = contents[a].compareTo(contents[b]);
            if(ret == 0){
                ret = identifiers[a].compareTo(identifiers[b]);
            }
            return ret;
        }).map(it -> logs[it]).toArray(String[]::new);
    }
}

/**
 * 937 Problems
 * https://leetcode.com/problems/reorder-data-in-log-files/
 */
public class ReorderLogs {
    public static void main(String[] args) throws FileNotFoundException {
        System.setIn(new FileInputStream("./src/leetcode/reorder_logs/input.txt"));
        Scanner scanner = new Scanner(System.in);

        int count = Integer.parseInt(scanner.nextLine());
        while (count-- > 0) {
            int logLength = Integer.parseInt(scanner.nextLine());
            String[] logs = new String[logLength];
            for (int i = 0; i < logLength; ++i) {
                logs[i] = scanner.nextLine();
            }

            Solution solution = new Solution();
            String[] result = solution.reorderLogFiles(logs);

            Utils.printArray(result);
        }
    }
}


