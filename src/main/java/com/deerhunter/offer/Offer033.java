package com.deerhunter.offer;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.LinkedList;

/**
 * 剑指 Offer 33. 二叉搜索树的后序遍历序列
 * 输入一个整数数组，判断该数组是不是某二叉搜索树的后序遍历结果。如果是则返回 true，否则返回 false。假设输入的数组的任意两个数字都互不相同。
 * <p>
 * <p>
 * <p>
 * 参考以下这颗二叉搜索树：
 * <p>
 * 5
 * / \
 * 2   6
 * / \
 * 1   3
 * 示例 1：
 * <p>
 * 输入: [1,6,3,2,5]
 * 输出: false
 * 示例 2：
 * <p>
 * 输入: [1,3,2,6,5]
 * 输出: true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 数组长度 <= 1000
 * 通过次数161,113提交次数287,448
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/7 上午11:23
 */
public class Offer033 {
    /**
     * 分治
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder(int[] postorder) {
        return verifyPostorder(postorder, 0, postorder.length - 1);
    }

    private boolean verifyPostorder(int[] postorder, int left, int right) {
        if (left >= right) {
            return true;
        }
        int root = postorder[right];
        int pivot = left;
        while (pivot < right && postorder[pivot] < root) {
            pivot++;
        }
        for (int i = pivot; i < right; i++) {
            if (postorder[i] < root) {
                return false;
            }
        }
        return verifyPostorder(postorder, left, pivot - 1) && verifyPostorder(postorder, pivot, right - 1);
    }

    /**
     * 递归尝试构建搜索树
     *
     * @param postorder
     * @return
     */
    public boolean verifyPostorder2(int[] postorder) {
        int[] index = new int[]{postorder.length - 1};
        isValid(postorder, index, Integer.MIN_VALUE, Integer.MAX_VALUE);
        return index[0] == -1;
    }

    private void isValid(int[] postorder, int[] index, int min, int max) {
        if (index[0] < 0) {
            return;
        }
        int root = postorder[index[0]];
        if (root < min || root > max) {
            return;
        }
        index[0]--;
        // 尝试构建右子树
        isValid(postorder, index, root, max);
        // 尝试构建左子树
        isValid(postorder, index, min, root);
    }

}
