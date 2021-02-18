package leetcode.deep_copy;

import java.util.ArrayList;
import java.util.IdentityHashMap;
import java.util.List;
import java.util.Map;

// Definition for a Node.
class Node {

    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        Map<Node, Integer> indexMap = new IdentityHashMap<>();
        Node current = head;

        List<Node> nodeList = new ArrayList<>();
        int idx = 0;
        while (current != null) {
            indexMap.put(current, idx++);
            nodeList.add(new Node(current.val));
            current = current.next;
        }

        // Get each random index in the list
        current = head;
        int[] randomIdx = new int[idx];
        idx = 0;
        while (current != null) {
            Node randomTarget = current.random;
            if (randomTarget == null) {
                randomIdx[idx] = -1;
            } else {
                randomIdx[idx] = indexMap.get(randomTarget);
            }
            current = current.next;
            idx++;
        }

        // Connect next pointer
        for (int i = 0; i < nodeList.size() - 1; i++) {
            nodeList.get(i).next = nodeList.get(i + 1);
        }
        for (int i = 0; i < nodeList.size(); i++) {
            if (randomIdx[i] != -1) {
                nodeList.get(i).random = nodeList.get(randomIdx[i]);
            }
        }

        return nodeList.get(0);
    }
}

public class DeepCopyNode {
    static Node getFirstProb() {
        Node[] nodes = {
                new Node(7),
                new Node(13),
                new Node(11),
                new Node(10),
                new Node(1),
        };
        for (int i = 0; i < nodes.length - 1; ++i) {
            nodes[i].next = nodes[i + 1];
        }
        nodes[1].random = nodes[0];
        nodes[2].random = nodes[4];
        nodes[3].random = nodes[2];
        nodes[4].random = nodes[0];
        return nodes[0];
    }

    public static void main(String[] args) {
        Node first = getFirstProb();
        Node copies = new Solution().copyRandomList(first);
        System.out.println(copies);
    }
}
