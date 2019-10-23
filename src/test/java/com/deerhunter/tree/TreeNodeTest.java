package com.deerhunter.tree;

import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-20
 */
class TreeNodeTest {

    @Test
    void createTree() throws IOException {
        TreeNode root = TreeNode.createTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println();
    }
}