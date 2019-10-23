package com.deerhunter.graph;

public class AdjacentListNode {
    public int nodeIndex;
    public int info;

    public AdjacentListNode nextArc;

    public AdjacentListNode(int nodeIndex) {
        this.nodeIndex = nodeIndex;
        nextArc = null;
    }
}