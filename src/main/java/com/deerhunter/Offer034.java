package com.deerhunter;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.List;

/**
 * 剑指 Offer 34. 二叉树中和为某一值的路径
 * 给你二叉树的根节点 root 和一个整数目标和 targetSum ，找出所有 从根节点到叶子节点 路径总和等于给定目标和的路径。
 * <p>
 * 叶子节点 是指没有子节点的节点。
 * <p>
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * <p>
 * <p>
 * 输入：root = [5,4,8,11,null,13,4,7,2,null,null,5,1], targetSum = 22
 * 输出：[[5,4,11,2],[5,8,4,5]]
 * 示例 2：
 * <p>
 * <p>
 * <p>
 * 输入：root = [1,2,3], targetSum = 5
 * 输出：[]
 * 示例 3：
 * <p>
 * 输入：root = [1,2], targetSum = 0
 * 输出：[]
 * <p>
 * <p>
 * 提示：
 * <p>
 * 树中节点总数在范围 [0, 5000] 内
 * -1000 <= Node.val <= 1000
 * -1000 <= targetSum <= 1000
 * 注意：本题与主站 113 题相同：https://leetcode-cn.com/problems/path-sum-ii/
 * <p>
 * 通过次数191,594提交次数327,252
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/6 上午12:13
 */
public class Offer034 {
    /**
     * 简单的回溯
     *
     * @param root
     * @param target
     * @return
     */
    public List<List<Integer>> pathSum(TreeNode root, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        backTrack(root, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    private void backTrack(TreeNode node, int target, int sum, ArrayList<Integer> temp, List<List<Integer>> ans) {
        if (node == null) {
            return;
        }
        sum += node.val;
        temp.add(node.val);
        if (node.left == null && node.right == null && sum == target) {
            ans.add(new ArrayList<>(temp));
        }
        backTrack(node.left, target, sum, temp, ans);
        backTrack(node.right, target, sum, temp, ans);
        temp.remove(temp.size() - 1);
    }
}
