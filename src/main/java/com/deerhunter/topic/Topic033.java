package com.deerhunter.topic;

import com.deerhunter.common.Utils;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-17
 */
public class Topic033 {
    // 错误解法
    public static int search2(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }

        int left = 0;
        int right = nums.length - 1;
        int mid;
        while (left <= right) {
            // 防止溢出
            mid = (right - left) / 2 + left;
            if (nums[mid] == target) {
                return mid;
            }
            if (nums[mid] < target) {
                if (nums[right] < target) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            } else {
                if (nums[left] > target) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }
        return -1;
    }

    public static int search(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int pivot = findPivot(nums);
        int i = Utils.binarySearch(nums, target, 0, pivot);
        return i != -1 ? i : Utils.binarySearch(nums, target, pivot, nums.length);
    }

    public static int findPivot(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {
            int mid = (right - left) / 2 + left;
            if (mid > 0 && nums[mid - 1] > nums[mid]) {
                return mid;
            }
            if (nums[left] <= nums[mid] && nums[mid] > nums[right]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return 0;
    }

    /**
     * 自己写的解法，过于复杂
     */
    public static class Solution2 {
        public int search(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }

            // target是否在旋转点左侧
            boolean onLeft = target >= nums[0];
            int left = 0;
            int right = nums.length - 1;
            while (left <= right) {
                int middle = (right - left) / 2 + left;
                if (nums[middle] == target) {
                    return middle;
                }

                // middle是否在旋转点左侧
                boolean middleOnLeft = nums[middle] >= nums[0];

                if (onLeft && !middleOnLeft) {
                    right = middle - 1;
                }

                if (!onLeft && middleOnLeft) {
                    left = middle + 1;
                }

                if (onLeft == middleOnLeft) {
                    if (nums[middle] > target) {
                        right = middle - 1;
                    } else {
                        left = middle + 1;
                    }
                }

            }
            return -1;
        }
    }

    /**
     * 参照官方题解重新写了一次
     */
    public static class Solution3 {
        public int search(int[] nums, int target) {
            if (nums.length == 0) {
                return -1;
            }
            // pivot是指发生了旋转后，右半段的起点。若nums没有经过旋转，则得到的pivot==nums.length
            int pivot = findPivot(nums);
            return nums[0] > target ? binarySearch(nums, pivot, nums.length, target) : binarySearch(nums, 0, pivot, target);
        }

        /**
         * 二分查找
         *
         * @param nums
         * @param left   左界，包含
         * @param right  右界，不包含
         * @param target
         * @return
         */
        private int binarySearch(int[] nums, int left, int right, int target) {
            while (left < right) {
                int middle = (right - left) / 2 + left;
                if (nums[middle] > target) {
                    right = middle;
                } else if (nums[middle] < target) {
                    left = middle + 1;
                } else {
                    return middle;
                }
            }
            return -1;
        }

        int findPivot(int[] nums) {
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (pivot - 1 >= 0 && nums[pivot - 1] > nums[pivot]) {
                    return pivot;
                }

                if (nums[pivot] >= nums[0]) {
                    left = pivot + 1;
                } else {
                    right = pivot;
                }
            }
            return left;
        }
    }

    /**
     * 参考评论中的优秀解法
     * <p>
     * 基本思想，使用二分法查找时，至少有一边是升序，那么久可以快速判断target是否在这个区间。
     */
    public static class Solution4 {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] == target) {
                    return pivot;
                }
                // 左侧是升序
                if (nums[pivot] > nums[left]) {
                    if (nums[left] <= target && nums[pivot] >= target) {
                        right = pivot;
                    } else {
                        left = pivot + 1;
                    }
                    // 右侧是升序
                } else {
                    if (nums[pivot] <= target && nums[right - 1] >= target) {
                        left = pivot + 1;
                    } else {
                        right = pivot;
                    }
                }
            }
            return -1;
        }
    }

    /**
     * 一趟查找
     */
    public static class Solution5 {
        public int search(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] == target) {
                    return pivot;
                } else if (nums[pivot] < target) {
                    // pivot值小了，直觉是向右查找，但是有一种情况例外：pivot落在右侧，并且最右端小于目标值。这种情况应该到左边查找
                    if (nums[pivot] < nums[0] && nums[right - 1] < target) {
                        right = pivot;
                    } else {
                        left = pivot + 1;
                    }
                } else {
                    // pivot值大了，直觉是向左查找。但是有一种情况例外：pivot落在左侧，并且最左端大于目标值。这种情况应该到右边查找
                    if (nums[pivot] > nums[0] && nums[left] > target) {
                        left = pivot + 1;
                    } else {
                        right = pivot;
                    }
                }
            }
            return -1;
        }
    }

}
