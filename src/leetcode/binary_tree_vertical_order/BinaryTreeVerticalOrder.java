package leetcode.binary_tree_vertical_order;

import java.util.*;
import java.util.stream.Collectors;

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
    private Map<Integer, List<TreeNode>> columnMap = new HashMap<>();

    class ColumnNode {
        final TreeNode node;
        final int column;

        ColumnNode(TreeNode node, int column) {
            this.node = node;
            this.column = column;
        }
    }

    public List<List<Integer>> verticalOrder(TreeNode root) {
        if (root == null) {
            return new ArrayList<>();
        }
        // Search by dfs
        Queue<ColumnNode> queue = new LinkedList<>();
        queue.add(new ColumnNode(root, 0));
        while (!queue.isEmpty()) {
            ColumnNode node = queue.poll();

            List<TreeNode> nodes = columnMap.get(node.column);
            if (nodes == null) {
                nodes = new ArrayList<>();
                columnMap.put(node.column, nodes);
            }
            nodes.add(node.node);

            if (node.node.left != null) {
                queue.add(new ColumnNode(node.node.left, node.column - 1));
            }

            if (node.node.right != null) {
                queue.add(new ColumnNode(node.node.right, node.column + 1));
            }
        }

        List<Integer> columns = new ArrayList<>(columnMap.keySet());
        Collections.sort(columns);

        return columns.stream()
                .map(colKey -> columnMap.get(colKey)
                        .stream().map(it -> it.val)
                        .collect(Collectors.toList()))
                .collect(Collectors.toList());
    }
}

public class BinaryTreeVerticalOrder {

}
