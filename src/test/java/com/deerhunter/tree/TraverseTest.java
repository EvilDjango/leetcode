package com.deerhunter.tree;

import org.junit.jupiter.api.Test;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-20
 */
class TraverseTest {
    private TreeNode root = TreeNode.createCompleteTree(new int[]{1, 2, 3, 4, 5, 6, 7});


    @Test
    void preOrderRecursively() {
        Traverse.preOrderRecursively(root);
    }

    @Test
    void preorderByLoop() {
        Traverse.preOrderByLoop(root);
    }

    @Test
    void preorderByLoop2() {
        Traverse.preOrderByLoop2(root);
    }



    @Test
    void preorderByLoop3() {
        Traverse.preOrderByLoop3(root);
    }

    @Test
    void preOrderByMorris() {
        Traverse.preOrderByMorris(root);
        System.out.println();
    }
    @Test
    void traverseByLayer() {
        Traverse.traverseByLayer(root);
    }

    @Test
    void inorderRecursively() {
        Traverse.inOrderRecursively(root);
    }

    @Test
    void inorderByLoop() {
        Traverse.inOrderByLoop(root);
    }

    @Test
    void inorderByLoop2() {
        Traverse.inOrderByLoop2(root);
    }

    @Test
    void inOrderByMorris() {
        Traverse.inOrderByMorris(root);
    }

    @Test
    void postOrderRecursively() {
        Traverse.postOrderRecursively(root);
    }

    @Test
    void postOrderByLoop() {
        Traverse.postOrderByLoop(root);
    }

    @Test
    void postOrderByLoo2() {
        Traverse.postOrderByLoop2(root);
    }

    @Test
    void postOrderMorris() {
        Traverse.postOrderMorris(root);
    }
}
