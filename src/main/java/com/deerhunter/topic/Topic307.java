package com.deerhunter.topic;

/**
 * 307. 区域和检索 - 数组可修改
 * 给你一个数组 nums ，请你完成两类查询。
 * <p>
 * 其中一类查询要求 更新 数组 nums 下标对应的值
 * 另一类查询要求返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 ，其中 left <= right
 * 实现 NumArray 类：
 * <p>
 * NumArray(int[] nums) 用整数数组 nums 初始化对象
 * void update(int index, int val) 将 nums[index] 的值 更新 为 val
 * int sumRange(int left, int right) 返回数组 nums 中索引 left 和索引 right 之间（ 包含 ）的nums元素的 和 （即，nums[left] + nums[left + 1], ..., nums[right]）
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：
 * ["NumArray", "sumRange", "update", "sumRange"]
 * [[[1, 3, 5]], [0, 2], [1, 2], [0, 2]]
 * 输出：
 * [null, 9, null, 8]
 * <p>
 * 解释：
 * NumArray numArray = new NumArray([1, 3, 5]);
 * numArray.sumRange(0, 2); // 返回 1 + 3 + 5 = 9
 * numArray.update(1, 2);   // nums = [1,2,5]
 * numArray.sumRange(0, 2); // 返回 1 + 2 + 5 = 8
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= nums.length <= 3 * 104
 * -100 <= nums[i] <= 100
 * 0 <= index < nums.length
 * -100 <= val <= 100
 * 0 <= left <= right < nums.length
 * 调用 update 和 sumRange 方法次数不大于 3 * 104
 * 通过次数51,186提交次数101,077
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/3 下午1:29
 */
public class Topic307 {
    /**
     * 线段树,构造一个完全二叉树，输入的nums恰好在最后一层。
     */
    static class NumArray {
        private int shift;
        private int[] tree;

        public NumArray(int[] nums) {
            int levels = 1;
            int len = nums.length;
            while (1 << (levels - 1) < len) {
                levels++;
            }
            tree = new int[(1 << levels) - 1 - (1 << levels - 1) + len];
            shift = tree.length - len;
            for (int i = 0; i < len; i++) {
                update(i, nums[i]);
            }
        }

        public void update(int index, int val) {
            index += shift;
            int delta = val - tree[index];
            while (index >= 0) {
                tree[index] += delta;
                index = (index - 1) >> 1;
            }
        }

        public int sumRange(int left, int right) {
            left += shift;
            right += shift;
            int extra = 0;
            while (left != right) {
                // 如果左端点属于右节点，则应该移除左子树的和
                if ((left & 1) == 0 && left > 0) {
                    extra += tree[left - 1];
                }
                // 如果右端点属于左节点，则应该移除右子树的和
                if ((right & 1) != 0 && right + 1 < tree.length) {
                    extra += tree[right + 1];
                }
                left = (left - 1) >> 1;
                right = (right - 1) >> 1;
            }
            return tree[left] - extra;
        }
    }

    /**
     * 依然是线段树,参考题解，自上而下
     */
    static class NumArray2 {
        private int n;
        private int[] segmentTree;

        public NumArray2(int[] nums) {
            n = nums.length;
            segmentTree = new int[n << 2];
            buildTree(0, 0, n - 1, nums);
        }

        private void buildTree(int node, int low, int high, int[] nums) {
            if (low == high) {
                segmentTree[node] = nums[low];
                return;
            }
            int mid = ((high - low) >> 1) + low;
            buildTree(2 * node + 1, low, mid, nums);
            buildTree(2 * node + 2, mid + 1, high, nums);
            segmentTree[node] = segmentTree[node * 2 + 1] + segmentTree[node * 2 + 2];
        }

        public void update(int index, int val) {
            dpUpdate(index, val, 0, 0, n - 1);
        }

        private void dpUpdate(int index, int val, int node, int low, int high) {
            if (low == high) {
                segmentTree[node] = val;
                return;
            }
            int mid = ((high - low) >> 1) + low;
            if (index <= mid) {
                dpUpdate(index, val, 2 * node + 1, low, mid);
            } else {
                dpUpdate(index, val, 2 * node + 2, mid + 1, high);
            }
            segmentTree[node] = segmentTree[2 * node + 1] + segmentTree[2 * node + 2];
        }

        public int sumRange(int left, int right) {
            return doSumRange(0, 0, n - 1, left, right);
        }

        private int doSumRange(int node, int low, int high, int left, int right) {
            if (low == left && high == right) {
                return segmentTree[node];
            }
            int mid = ((high - low) >> 1) + low;
            int lChild = 2 * node + 1;
            int rChild = 2 * node + 2;
            if (right <= mid) {
                return doSumRange(lChild, low, mid, left, right);
            } else if (left > mid) {
                return doSumRange(rChild, mid + 1, high, left, right);
            } else {
                return doSumRange(lChild, low, mid, left, mid) + doSumRange(rChild, mid + 1, high, mid + 1, right);
            }
        }
    }

    /**
     * 树状数组
     */
    class NumArray3 {
        private int[] tree;
        private int[] nums;

        public NumArray3(int[] nums) {
            this.nums = nums;
            tree = new int[nums.length + 1];
            for (int i = 0; i < nums.length; i++) {
                tree[i + 1] += nums[i];
                int next = i + 1 + lowBit(i + 1);
                if (next < tree.length) {
                    tree[next] += tree[i + 1];
                }
            }
        }


        public void update(int index, int val) {
            add(index + 1, val - nums[index]);
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            return query(right + 1) - query(left);
        }

        private void add(int index, int val) {
            while (index < tree.length) {
                tree[index] += val;
                index += lowBit(index);
            }
        }

        private int query(int index) {
            int sum = 0;
            while (index > 0) {
                sum += tree[index];
                index -= lowBit(index);
            }
            return sum;
        }


        private int lowBit(int x) {
            return -x & x;
        }
    }

    /**
     * 分块处理
     */
    class NumArray4 {
        private int[] nums;
        private int[] sums;
        private int size;

        public NumArray4(int[] nums) {
            this.nums = nums;
            int n = nums.length;
            size = (int) Math.sqrt(n);
            sums = new int[(n + size - 1) / size];
            for (int i = 0; i < n; i++) {
                sums[i / size] += nums[i];
            }
        }

        public void update(int index, int val) {
            sums[index / size] += val - nums[index];
            nums[index] = val;
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            while (left % size != 0 && left <= right) {
                sum += nums[left++];
            }
            while (left + size - 1 <= right) {
                sum += sums[left / size];
                left += size;
            }
            while (left <= right) {
                sum += nums[left++];
            }
            return sum;
        }
    }

    /**
     * 仅使用2n空间的线段树，自底向上，注意树是从下标1开始的，就是说总结点数为2n-1，那么i节点的左右子树分别为2i和2i+1，父节点为i/2
     */
    class NumArray5 {
        private int n;
        private int[] tree;

        public NumArray5(int[] nums) {
            n = nums.length;
            tree = new int[n << 1];
            for (int i = 0; i < n; i++) {
                tree[n + i] = nums[i];
            }
            for (int i = n - 1; i > 0; i--) {
                tree[i] = tree[i << 1] + tree[(i << 1) + 1];
            }
        }

        public void update(int index, int val) {
            index += n;
            int delta = val - tree[index];
            while (index > 0) {
                tree[index] += delta;
                index >>= 1;
            }
        }

        public int sumRange(int left, int right) {
            int sum = 0;
            left += n;
            right += n;
            while (left <= right) {
                // 左端点为右节点，那么左节点是不能计入的，所以累加上本节点的值后，left右移一位
                if ((left & 1) == 1) {
                    sum += tree[left++];
                }
                // 右端点为左节点，那么右节点是不嫩计入的，所以累加上本节点的值后，right左移一位
                if ((right & 1) == 0) {
                    sum += tree[right--];
                }
                left >>= 1;
                right >>= 1;
            }
            return sum;
        }
    }
}
