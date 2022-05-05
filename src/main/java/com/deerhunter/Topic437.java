package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Handler;

/**
 * 437. 路径总和 III
 * 给定一个二叉树的根节点 root ，和一个整数 targetSum ，求该二叉树里节点值之和等于 targetSum 的 路径 的数目。
 * <p>
 * 路径 不需要从根节点开始，也不需要在叶子节点结束，但是路径方向必须是向下的（只能从父节点到子节点）。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [10,5,-3,3,2,null,11,3,-2,null,1], targetSum = 8
 * 输出：3
 * 解释：和等于 8 的路径有 3 条，如图所示。
 * 示例 2：
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：3
 * <p>
 * <p>
 * 提示:
 * <p>
 * 二叉树的节点个数的范围是 [0,1000]
 * -109 <= Node.val <= 109
 * -1000 <= targetSum <= 1000
 * 通过次数162,598提交次数285,759
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/6 上午12:37
 */
public class Topic437 {
    /**
     * 前缀和
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum(TreeNode root, int targetSum) {
        int[] count = new int[1];
        pathSum(root, targetSum, 0, new ArrayList<>(), count);
        return count[0];
    }

    private void pathSum(TreeNode root, int targetSum, int sum, ArrayList<Integer> sums, int[] count) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (sum == targetSum) {
            count[0]++;
        }
        for (int preSum : sums) {
            if (sum - preSum == targetSum) {
                count[0]++;
            }
        }
        sums.add(sum);
        pathSum(root.left, targetSum, sum, sums, count);
        pathSum(root.right, targetSum, sum, sums, count);
        sums.remove(sums.size() - 1);
    }

    /**
     * 前缀和，使用哈希表来存储
     *
     * @param root
     * @param targetSum
     * @return
     */
    public int pathSum2(TreeNode root, int targetSum) {
        int[] count = new int[1];
        Map<Integer, Integer> prefix = new HashMap<>();
        prefix.put(0, 1);
        pathSum2(root, targetSum, 0, prefix, count);
        return count[0];
    }

    private void pathSum2(TreeNode root, int targetSum, int sum, Map<Integer, Integer> prefix, int[] count) {
        if (root == null) {
            return;
        }
        sum += root.val;
        count[0] += prefix.getOrDefault(sum - targetSum, 0);
        prefix.merge(sum, 1, Integer::sum);
        pathSum2(root.left, targetSum, sum, prefix, count);
        pathSum2(root.right, targetSum, sum, prefix, count);
        prefix.merge(sum, -1, Integer::sum);
    }

}
