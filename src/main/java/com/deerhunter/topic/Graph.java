package com.deerhunter.topic;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/22 21:29
 */
public class Graph {
    public int v;
    public int e;
    public Node[] vertexes;

    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        vertexes = new Node[v + 1];
        for (int i = 1; i <= v; i++) {
            vertexes[i] = new Node(i);
        }
    }

    public Graph(int e) {
        this(e + 1, e);
    }

    public Graph addEdge(int u, int v) {
        vertexes[u].neighbors.add(vertexes[v]);
        vertexes[v].neighbors.add(vertexes[u]);
        return this;
    }


}
