package com.deerhunter;

import com.deerhunter.tree.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/4/14 15:31
 */
class Topic222Test {
    void test(Function<TreeNode, Integer> function) {
        TreeNode root = TreeNode.createCompleteTree(new int[]{1, 2, 3, 4, 5, 6});
        assertEquals(6, function.apply(root));
    }

    @Test
    void solution1() {
        test(new Topic222.Solution1()::countNodes);
    }
}
