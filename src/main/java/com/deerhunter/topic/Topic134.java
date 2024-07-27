package com.deerhunter.topic;

/**
 * 在一条环路上有 N 个加油站，其中第 i 个加油站有汽油 gas[i] 升。
 * <p>
 * 你有一辆油箱容量无限的的汽车，从第 i 个加油站开往第 i+1 个加油站需要消耗汽油 cost[i] 升。你从其中的一个加油站出发，开始时油箱为空。
 * <p>
 * 如果你可以绕环路行驶一周，则返回出发时加油站的编号，否则返回 -1。
 * <p>
 * 说明: 
 * <p>
 * 如果题目有解，该答案即为唯一答案。
 * 输入数组均为非空数组，且长度相同。
 * 输入数组中的元素均为非负数。
 * 示例 1:
 * <p>
 * 输入:
 * gas  = [1,2,3,4,5]
 * cost = [3,4,5,1,2]
 * <p>
 * 输出: 3
 * <p>
 * 解释:
 * 从 3 号加油站(索引为 3 处)出发，可获得 4 升汽油。此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 4 号加油站，此时油箱有 4 - 1 + 5 = 8 升汽油
 * 开往 0 号加油站，此时油箱有 8 - 2 + 1 = 7 升汽油
 * 开往 1 号加油站，此时油箱有 7 - 3 + 2 = 6 升汽油
 * 开往 2 号加油站，此时油箱有 6 - 4 + 3 = 5 升汽油
 * 开往 3 号加油站，你需要消耗 5 升汽油，正好足够你返回到 3 号加油站。
 * 因此，3 可为起始索引。
 * 示例 2:
 * <p>
 * 输入:
 * gas  = [2,3,4]
 * cost = [3,4,3]
 * <p>
 * 输出: -1
 * <p>
 * 解释:
 * 你不能从 0 号或 1 号加油站出发，因为没有足够的汽油可以让你行驶到下一个加油站。
 * 我们从 2 号加油站出发，可以获得 4 升汽油。 此时油箱有 = 0 + 4 = 4 升汽油
 * 开往 0 号加油站，此时油箱有 4 - 3 + 2 = 3 升汽油
 * 开往 1 号加油站，此时油箱有 3 - 3 + 3 = 3 升汽油
 * 你无法返回 2 号加油站，因为返程需要消耗 4 升汽油，但是你的油箱只有 3 升汽油。
 * 因此，无论怎样，你都不可能绕环路行驶一周。
 * 通过次数47,869提交次数88,315
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gas-station
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/25 14:55
 */
public class Topic134 {
    public static class Solution1 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int n = gas.length;
            if (n == 0) {
                return -1;
            }

            int[] remainMin = new int[n];
            int[] remain = new int[n];
            remain[0] = gas[0] - cost[0];
            remainMin[0] = remain[0];
            for (int i = 1; i < n; i++) {
                remain[i] = remain[i - 1] + gas[i] - cost[i];
                remainMin[i] = Math.min(remainMin[i - 1], remain[i]);
            }
            if (remainMin[n - 1] >= 0) {
                return 0;
            }
            for (int i = 1; i < n; i++) {
                if (remainMin[n - 1] - remain[i - 1] >= 0 && remainMin[i - 1] + remain[n - 1] - remain[i - 1] >= 0) {
                    return i;
                }
            }
            return -1;
        }
    }

    /**
     * 参考官方题解
     */
    public static class Solution2 {
        public int canCompleteCircuit(int[] gas, int[] cost) {
            int start = 0;
            int totalTank = 0;
            int currTank = 0;
            for (int i = 0; i < gas.length; i++) {
                totalTank += gas[i] - cost[i];
                currTank += gas[i] - cost[i];
                if (currTank < 0) {
                    start = i + 1;
                    currTank = 0;
                }
            }
            return totalTank >= 0 ? start : -1;
        }
    }
}
