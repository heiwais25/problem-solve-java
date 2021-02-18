package leetcode.vertical_order_traversal;

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
    private static class NodePos {
        final TreeNode node;
        final int col;
        final int row;

        private NodePos(TreeNode node, int row, int col) {
            this.node = node;
            this.row = row;
            this.col = col;
        }
    }

    public List<List<Integer>> verticalTraversal(TreeNode root) {
        List<List<Integer>> ret = new ArrayList<>();
        if (root == null) {
            return ret;
        }

        Map<Integer, List<NodePos>> columnMaps = new HashMap<>();
        Queue<NodePos> queue = new LinkedList<>();

        queue.add(new NodePos(root, 0, 0));
        while (!queue.isEmpty()) {
            NodePos pos = queue.poll();
            List<NodePos> column = columnMaps.
                    computeIfAbsent(pos.col, k -> new ArrayList<>());
            column.add(pos);
            if (pos.node.left != null) {
                queue.add(new NodePos(pos.node.left, pos.row + 1, pos.col - 1));
            }
            if (pos.node.right != null) {
                queue.add(new NodePos(pos.node.right, pos.row + 1, pos.col + 1));
            }
        }

        List<Integer> keys = new ArrayList<>(columnMaps.keySet());
        Collections.sort(keys);
        for (Integer key : keys) {
            List<NodePos> nodes = columnMaps.get(key);
            nodes.sort((a, b) -> {
                if (a.row != b.row) return a.row - b.row;
                else return a.node.val - b.node.val;
            });

            ret.add(nodes.stream().map(it -> it.node.val).collect(Collectors.toList()));
        }
        return ret;
    }
}

public class Main {

}
