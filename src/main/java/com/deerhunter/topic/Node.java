package com.deerhunter.topic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.List;
import java.util.Queue;
import java.util.function.Consumer;

public class Node {
    public int val;
    public boolean visited;

    public List<Node> neighbors;

    public Node() {
        val = 0;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val) {
        val = _val;
        neighbors = new ArrayList<Node>();
    }

    public Node(int _val, ArrayList<Node> _neighbors) {
        val = _val;
        neighbors = _neighbors;
    }

    public void bfs(Consumer<Node> consumer) {
        Queue<Node> queue = new ArrayDeque<>();
        queue.add(this);
        this.visited = true;

        while (!queue.isEmpty()) {
            Node node = queue.remove();
            consumer.accept(node);
            for (Node adjacentNode : node.neighbors) {
                if (!adjacentNode.visited) {
                    queue.add(adjacentNode);
                    adjacentNode.visited = true;
                }
            }
        }
    }

    public void dfs(Consumer<Node> consumer) {
        dfs(this, consumer);
    }

    private void dfs(Node node, Consumer<Node> consumer) {
        consumer.accept(node);
        node.visited = true;
        for (Node adjacentNode : node.neighbors) {
            if (!adjacentNode.visited) {
                dfs(adjacentNode, consumer);
            }
        }
    }

    @Override
    public String toString() {
        return "" + val;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Node)) {
            return false;
        }
        Node node = (Node) obj;
        Queue<Node[]> queue = new ArrayDeque<>();
        queue.add(new Node[]{this, node});
        while (!queue.isEmpty()) {
            Node[] nodes = queue.remove();
            Node a = nodes[0];
            Node b = nodes[1];
            if (a.val != b.val) {
                return false;
            }
            if (a.neighbors.size() != b.neighbors.size()) {
                return false;
            }
            a.visited = true;
            for (int i = 0; i < a.neighbors.size(); i++) {
                if (!a.neighbors.get(i).visited) {
                    queue.add(new Node[]{a.neighbors.get(i), b.neighbors.get(i)});
                }
            }
        }
        return true;
    }
}
