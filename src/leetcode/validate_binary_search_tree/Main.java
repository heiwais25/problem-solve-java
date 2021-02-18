package leetcode.validate_binary_search_tree;

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
    private boolean dfs(TreeNode cur, long min, long max) {
        boolean valid = cur.val > min && cur.val < max;
        if (cur.left != null) {
            valid = valid && dfs(cur.left, min, cur.val);
        }
        if (cur.right != null) {
            valid = valid && dfs(cur.right, cur.val, max);
        }

        return valid;
    }

    public boolean isValidBST(TreeNode root) {
        return dfs(root, Long.MIN_VALUE, Long.MAX_VALUE);
    }
}

public class Main {
}
