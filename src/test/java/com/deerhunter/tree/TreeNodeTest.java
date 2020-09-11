package com.deerhunter.tree;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

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
        TreeNode root = TreeNode.createCompleteTree(new int[]{1, 2, 3, 4, 5, 6, 7});
        System.out.println();
    }

    @Test
    void toArray() {
        Integer[] nums = {1, 2, 3, 4, 5, 6, 7};
        TreeNode root = TreeNode.createTree2(nums);
        assertArrayEquals(nums, root.toIntegers());

        nums = new Integer[]{1, 3, null, null, 2};
        root = TreeNode.createTree2(nums);
        assertArrayEquals(nums, root.toIntegers());
    }

}
