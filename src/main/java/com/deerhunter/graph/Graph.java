package com.deerhunter.graph;

import java.util.ArrayDeque;
import java.util.Queue;
import java.util.Scanner;
import java.util.function.Consumer;

public class Graph {
    int v;
    int e;
    ListHead[] vertex;

    /**
     * 构造函数
     *
     * @param v 定点数
     * @param e 边数
     */
    public Graph(int v, int e) {
        this.v = v;
        this.e = e;
        vertex = new ListHead[v + 1];

        for (int i = 1; i <= v; i++) {
            vertex[i] = new ListHead(i);
        }
    }

    /**
     * 构造函数
     * <p>
     * 当不知道有多少个顶点式，最多有e+1个顶点
     *
     * @param e
     */
    public Graph(int e) {
        this(e + 1, e);
    }

    public void addEdge(int a, int b) {
        vertex[a].linkTo(b);
        vertex[b].linkTo(a);
    }

    /**
     * 从标准输入读取数据，并构造得到一个Graph对象
     * <p>
     * 输入格式如下：
     * <p>
     * 7 9
     * 1 2
     * 1 3
     * 1 5
     * 2 5
     * 2 3
     * 3 4
     * 4 6
     * 4 7
     * 6 7
     * <p>
     * 其中第一行第一个数字代表顶点的数量，第二个数字代表边的数量
     * 从第二行起每行代表一条边
     *
     * @return
     */
    public static Graph fromStdIn() {
        Scanner cin = new Scanner(System.in);

        int v = cin.nextInt();
        int e = cin.nextInt();

        Graph g = new Graph(v, e);

        for (int i = 0; i < e; i++) {
            int a = cin.nextInt();
            int b = cin.nextInt();
            g.addEdge(a, b);
        }
        return g;
    }

    /**
     * 广度优先遍历
     *
     * @param visitor
     */
    public void breadthFirstSearch(Consumer<ListHead> visitor) {
        Queue<ListHead> q = new ArrayDeque<>(v);
        q.add(vertex[1]);
        vertex[1].visited = true;
        while (!q.isEmpty()) {
            ListHead tmp = q.remove();
            visitor.accept(tmp);
            AdjacentListNode n = tmp.firstArc;

            while (n != null) {
                tmp = vertex[n.nodeIndex];
                if (!tmp.visited) {
                    q.add(tmp);
                    tmp.visited = true;
                }

                n = n.nextArc;
            }
        }
    }

    /**
     * 深度优先遍历
     *
     * @param visitor
     */
    public void depthFirstSearch(Consumer<ListHead> visitor) {
        depthFirstSearch(visitor, vertex[1]);
    }

    private void depthFirstSearch(Consumer<ListHead> visitor, ListHead v) {
        visitor.accept(v);
        v.visited = true;
        AdjacentListNode node = v.firstArc;
        while (node != null) {
            ListHead tmp = vertex[node.nodeIndex];
            if (!tmp.visited) {
                depthFirstSearch(visitor, tmp);
            }
            node = node.nextArc;
        }
    }

    public static void main(String[] args) {
        Graph g = fromStdIn();
//        g.breadthFirstSearch(head -> System.out.println(head.data));
        g.depthFirstSearch(head -> System.out.println(head.data));
    }
}