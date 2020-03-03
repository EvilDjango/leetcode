package com.deerhunter;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 给定一个包含 n 个整数的数组 nums，判断 nums 中是否存在三个元素 a，b，c ，使得 a + b + c = 0 ？找出所有满足条件且不重复的三元组。
 * <p>
 * 注意：答案中不可以包含重复的三元组。
 * <p>
 * 例如, 给定数组 nums = [-1, 0, 1, 2, -1, -4]，
 * <p>
 * 满足要求的三元组集合为：
 * [
 * [-1, 0, 1],
 * [-1, -1, 2]
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/3sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-28
 */
public class Topic015 {

    // 复制的优秀解法
    public static List<List<Integer>> threeSum(int[] nums) {
        List<List<Integer>> ans = new ArrayList<>();
        if (nums == null || nums.length < 3) {
            return ans;
        }
        int len = nums.length;
        Arrays.sort(nums); // 排序
        for (int i = 0; i < len; i++) {
            if (nums[i] > 0) {
                break; // 如果当前数字大于0，则三数之和一定大于0，所以结束循环
            }
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue; // 去重
            }
            int L = i + 1;
            int R = len - 1;
            while (L < R) {
                int sum = nums[i] + nums[L] + nums[R];
                if (sum < 0) {
                    L++;
                } else if (sum > 0) {
                    R--;
                } else {
                    ans.add(Arrays.asList(nums[i], nums[L], nums[R]));
                    while (L < R && nums[L] == nums[L + 1]) {
                        L++; // 去重
                    }
                    while (L < R && nums[R] == nums[R - 1]) {
                        R--; // 去重
                    }
                    L++;
                    R--;
                }
            }
        }
        return ans;
    }


    // 参考题解自己写的算法，非常不优雅
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> lists = new ArrayList<>();

        if (nums == null || nums.length == 0) {
            return lists;
        }
        Arrays.sort(nums);
        int zeroCnt = 0;
        if (nums[0] == 0) {
            zeroCnt++;
        }
        if (nums.length > 1 && nums[nums.length - 1] == 0) {
            zeroCnt++;
        }
        for (int i = 1; i < nums.length - 1; i++) {
            if (nums[i] == 0) {
                zeroCnt++;
            }
            if (nums[i + 1] == nums[i] && nums[i] < 0) {
                continue;
            }
            if (nums[i - 1] == nums[i] && nums[i] >= 0) {
                continue;
            }

            int l = 0;
            int r = nums.length - 1;
            while (l < i && r > i) {
                if (l - 1 > 0 && nums[l - 1] == nums[l]) {
                    l++;
                    continue;
                }
                if (r + 1 < nums.length && nums[r] == nums[r + 1]) {
                    r--;
                    continue;
                }

                int sum = nums[l] + nums[i] + nums[r];
                if (sum < 0) {
                    l++;
                } else if (sum > 0) {
                    r--;
                } else {
                    lists.add(Arrays.asList(nums[l], nums[i], nums[r]));
                    l++;
                    r--;
                }
            }
        }
        if (zeroCnt > 2) {
            lists.add(Arrays.asList(0, 0, 0));
        }
        return lists;
    }

    // 自己写的暴力算法，提交后超时了
    public List<List<Integer>> threeSum1(int[] nums) {
        Set<ThreeNum> sets = new HashSet<>();
        int len = nums.length;
        for (int i = 0; i < len; i++) {
            for (int j = i + 1; j < len; j++) {
                for (int k = j + 1; k < len; k++) {
                    if (nums[i] + nums[j] + nums[k] == 0) {
                        sets.add(new ThreeNum(nums[i], nums[j], nums[k]));
                    }
                }
            }
        }
        List<List<Integer>> lists = new ArrayList<>(sets.size());
        for (ThreeNum tn : sets) {
            List<Integer> list = new ArrayList<>(3);
            list.add(tn.a);
            list.add(tn.b);
            list.add(tn.c);
            lists.add(list);
        }
        return lists;
    }

    private static class ThreeNum {
        private int a;
        private int b;
        private int c;

        public ThreeNum(int a, int b, int c) {
//            this.a=a;
//            this.b=b;
//            this.c=c;
            this.a = Math.min(Math.min(a, b), c);
            this.c = Math.max(Math.max(a, b), c);
            this.b = 0 - this.a - this.c;
        }

        @Override
        public boolean equals(Object obj) {
            if (!(obj instanceof ThreeNum)) {
                return false;
            }
            ThreeNum tn = (ThreeNum) obj;
            return a + b == tn.a + tn.b;
        }

        @Override
        public int hashCode() {
            return 10 * a + b;
        }

        @Override
        public String toString() {
            return "[" + a + "," + b + "," + c + "]";
        }
    }

    public static void main(String[] args) {
        System.out.println(new ThreeNum(-1, 2, -1));
    }

}
