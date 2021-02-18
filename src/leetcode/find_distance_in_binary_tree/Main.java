package leetcode.find_distance_in_binary_tree;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {
    }

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}

class Solution {
    private List<Integer> firstPath;
    private List<Integer> secondPath;
    private int from;
    private int to;

    private int dfs(TreeNode root) {
        int ret = 0;
        if (root == null) {
            return ret;
        }

        ret |= dfs(root.left);
        ret |= dfs(root.right);

        if (root.val == from) {
            ret |= 1;
        }
        if (root.val == to) {
            ret |= 2;
        }

        if ((ret & 1) == 1) {
            firstPath.add(root.val);
        }
        if ((ret & 2) == 2) {
            secondPath.add(root.val);
        }
        return ret;
    }

    public int findDistance(TreeNode root, int p, int q) {
        firstPath = new ArrayList<>();
        secondPath = new ArrayList<>();

        from = p;
        to = q;

        // Run dfs
        dfs(root);

        // Calculate the path
        // Find the common
        Collections.reverse(firstPath);
        Collections.reverse(secondPath);

        int idx = 0;
        while (idx < firstPath.size() && idx < secondPath.size() &&
                firstPath.get(idx).equals(secondPath.get(idx))) {
            idx++;
        }

        return firstPath.size() + secondPath.size() - idx * 2;
    }
}

public class Main {
    public static void main(String[] args) {
    }
}
