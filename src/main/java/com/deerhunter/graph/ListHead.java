package com.deerhunter.graph;

class ListHead {
    int data;
    AdjacentListNode firstArc;

    public boolean visited;

    public ListHead(int data) {
        this.data = data;
        visited = false;
    }

    public void linkTo(int end) {
        if (firstArc == null) {
            firstArc = new AdjacentListNode(end);
            return;
        }

        AdjacentListNode n = firstArc;
        while (n.nextArc != null) {
            n = n.nextArc;
        }

        n.nextArc = new AdjacentListNode(end);
    }
}