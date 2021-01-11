package com.deerhunter;

/**
 * 189. Rotate Array
 * Medium
 * <p>
 * 3959
 * <p>
 * 894
 * <p>
 * Add to List
 * <p>
 * Share
 * Given an array, rotate the array to the right by k steps, where k is non-negative.
 * <p>
 * Follow up:
 * <p>
 * Try to come up as many solutions as you can, there are at least 3 different ways to solve this problem.
 * Could you do it in-place with O(1) extra space?
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: nums = [1,2,3,4,5,6,7], k = 3
 * Output: [5,6,7,1,2,3,4]
 * Explanation:
 * rotate 1 steps to the right: [7,1,2,3,4,5,6]
 * rotate 2 steps to the right: [6,7,1,2,3,4,5]
 * rotate 3 steps to the right: [5,6,7,1,2,3,4]
 * Example 2:
 * <p>
 * Input: nums = [-1,-100,3,99], k = 2
 * Output: [3,99,-1,-100]
 * Explanation:
 * rotate 1 steps to the right: [99,-1,-100,3]
 * rotate 2 steps to the right: [3,99,-1,-100]
 * <p>
 * <p>
 * Constraints:
 * <p>
 * 1 <= nums.length <= 2 * 104
 * -231 <= nums[i] <= 231 - 1
 * 0 <= k <= 105
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/7 16:56
 */
public class Topic189 {
    public static class Solution1 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            if (k == 0) {
                return;
            }
            for (int i = 0; i < k; i++) {
                int last = nums[n - 1];
                for (int j = n - 1; j >= 1; j--) {
                    nums[j] = nums[j - 1];
                }
                nums[0] = last;
            }
        }
    }

    public static class Solution2 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            if (k == 0) {
                return;
            }
            int[] tmp = new int[k];
            System.arraycopy(nums, n - k, tmp, 0, k);
            for (int i = n - k - 1; i >= 0; i--) {
                nums[i + k] = nums[i];
            }
            System.arraycopy(tmp, 0, nums, 0, k);
        }
    }

    public static class Solution3 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k = k % n;
            if (k == 0) {
                return;
            }
            while (true) {
                System.out.println();
                for (int i = n - k - 1; i >= 0; i--) {
                    int tmp = nums[i];
                    nums[i] = nums[i + k];
                    nums[i + k] = tmp;
                }
                if (n % k == 0) {
                    break;
                }
                int tmp = k;
                k = k - n % k;
                n = tmp;
            }
        }
    }

    /**
     * 循环替换
     */
    public static class Solution4 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n;
            if (k == 0) {
                return;
            }
            for (int i = 0, count = 0; count < n; i++) {
                int curr = i;
                int prev = nums[curr];
                do {
                    curr = (curr + k) % n;
                    int temp = nums[curr];
                    nums[curr] = prev;
                    prev = temp;
                    count++;
                } while (curr != i);
            }
        }
    }

    /**
     * 反转
     */
    public static class Solution5 {
        public void rotate(int[] nums, int k) {
            int n = nums.length;
            k %= n;
            if (k == 0) {
                return;
            }
            reverse(nums, 0, n);
            reverse(nums, 0, k);
            reverse(nums, k, n);

        }

        private void reverse(int[] nums, int start, int end) {
            int l = start;
            int r = end - 1;
            while (l < r) {
                int temp = nums[l];
                nums[l] = nums[r];
                nums[r] = temp;
                l++;
                r--;
            }
        }
    }
}
