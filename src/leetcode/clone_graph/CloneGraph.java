package leetcode.clone_graph;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

// Definition for a Node.
class Node {
    public int val;
    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }
}

// How to know how many node's are exist?
// 일단 각각에 대해 순회를 하며 값을 추가
// 기존에 존재하지 않는 값의 경우에는 새로 생성, 있다면
class Solution {
    private Map<Integer, Node> newNodeMap;

    // Create new node with copied sub-graph
    private Node copyNodeGraph(Node originalNode) {
        Node newNode = new Node();
        newNode.val = originalNode.val;
        newNodeMap.put(originalNode.val, newNode);

        for (Node neighbor : originalNode.neighbors) {
            // already created
            Node newNeighbor = newNodeMap.get(neighbor.val);
            if(newNeighbor == null){
                newNeighbor = copyNodeGraph(neighbor);
            }
            newNode.neighbors.add(newNeighbor);
        }

        return newNode;
    }

    public Node cloneGraph(Node node) {
        // Init
        newNodeMap = new HashMap<>();

        if (node == null) {
            return null;
        }

        return copyNodeGraph(node);
    }
}

public class CloneGraph {
    public static void main(String[] args) {
        Node[] nodes = {
                new Node(),
                new Node(),
                new Node(),
                new Node(),
        };
        for (int i = 0; i < nodes.length; i++) {
            nodes[i].val = i + 1;
        }

        nodes[0].neighbors.add(nodes[1]);
        nodes[0].neighbors.add(nodes[3]);
        nodes[1].neighbors.add(nodes[0]);
        nodes[1].neighbors.add(nodes[2]);
        nodes[2].neighbors.add(nodes[1]);
        nodes[2].neighbors.add(nodes[3]);
        nodes[3].neighbors.add(nodes[0]);
        nodes[3].neighbors.add(nodes[2]);

        Node newNode = new Solution().cloneGraph(nodes[0]);
        System.out.println(newNode);
    }
}
