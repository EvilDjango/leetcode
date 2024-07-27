package com.deerhunter.topic;

/**
 * 假设按照升序排序的数组在预先未知的某个点上进行了旋转。
 * <p>
 * ( 例如，数组 [0,0,1,2,2,5,6] 可能变为 [2,5,6,0,0,1,2] )。
 * <p>
 * 编写一个函数来判断给定的目标值是否存在于数组中。若存在返回 true，否则返回 false。
 * <p>
 * 示例 1:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 0
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: nums = [2,5,6,0,0,1,2], target = 3
 * 输出: false
 * 进阶:
 * <p>
 * 这是 搜索旋转排序数组 的延伸题目，本题中的 nums  可能包含重复元素。
 * 这会影响到程序的时间复杂度吗？会有怎样的影响，为什么？
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/search-in-rotated-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-30
 */
public class Topic081 {
    /**
     * 一趟查找: 基本思想是每次判断pivot落在左侧还是右侧
     */
    public static class Solution1 {
        public boolean search(int[] nums, int target) {
            int left = 0;
            int right = nums.length;

            // 如果开头与末尾相等，需要移除尾部的元素，否则后面无法判断pivot落在左侧还是右侧
            while (right - 1 > 0 && nums[0] == nums[right - 1]) {
                right--;
            }

            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] > target) {
                    // pivot值小了，直觉是向右查找，但是有一种情况例外：pivot落在右侧，并且最右端小于目标值。这种情况应该到左边查找
                    if (nums[pivot] >= nums[0] && nums[left] > target) {
                        left = pivot + 1;
                    } else {
                        right = pivot;
                    }
                } else if (nums[pivot] < target) {
                    // pivot值大了，直觉是向左查找。但是有一种情况例外：pivot落在左侧，并且最左端大于目标值。这种情况应该到右边查找
                    if (nums[pivot] < nums[0] && nums[right - 1] < target) {
                        right = pivot;
                    } else {
                        left = pivot + 1;
                    }
                } else {
                    return true;
                }
            }

            return false;
        }
    }


    /**
     * 一趟查找: 基本思想是任何时候pivot至少有一侧是升序
     */
    public static class Solution2 {
        public boolean search(int[] nums, int target) {
            int left = 0;
            int right = nums.length;
            // 如果开头与末尾相等，需要移除尾部的元素，否则后面无法判断左侧是否是升序
            while (right - 1 > 0 && nums[0] == nums[right - 1]) {
                right--;
            }
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] == target) {
                    return true;
                }

                // 左侧是升序
                if (nums[pivot] >= nums[left]) {
                    if (nums[left] <= target && nums[pivot] >= target) {
                        right = pivot;
                    } else {
                        left = pivot + 1;
                    }

                    // 右边是升序
                } else {
                    if (nums[pivot] <= target && nums[right - 1] >= target) {
                        left = pivot + 1;
                    } else {
                        right = pivot;
                    }
                }
            }
            return false;
        }
    }

    /**
     * 先找到旋转点，再搜索
     */
    public static class Solution3 {
        public boolean search(int[] nums, int target) {
            if (nums.length == 0) {
                return false;
            }

            int pivot = findPivot(nums);
            return nums[0] <= target ? binarySearch(nums, 0, pivot, target) : binarySearch(nums, pivot, nums.length, target);
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
        private boolean binarySearch(int[] nums, int left, int right, int target) {
            while (left < right) {
                int pivot = (right - left) / 2 + left;
                if (nums[pivot] > target) {
                    right = pivot;
                } else if (nums[pivot] < target) {
                    left = pivot + 1;
                } else {
                    return true;
                }
            }
            return false;
        }

        /**
         * 查找旋转点，旋转点是指旋转后右侧升序数列的起始下标。如果数据没有旋转，将返回nums.length
         *
         * @param nums
         * @return
         */
        int findPivot(int[] nums) {
            int left = 0;
            int right = nums.length;
            // 如果开头与末尾相等，需要移除尾部的元素，否则后面无法判断middle在哪一侧
            while (right - 1 > 0 && nums[0] == nums[right - 1]) {
                right--;
            }

            while (left < right) {
                int middle = (right - left) / 2 + left;
                if (middle + 1 < nums.length && nums[middle] > nums[middle + 1]) {
                    return middle + 1;
                }

                // middle在左侧
                if (nums[middle] >= nums[left]) {
                    left = middle + 1;
                } else {
                    right = middle;
                }
            }
            return left;
        }
    }

}
