package com.deerhunter.topic;

/**
 * 给定一个排序数组，你需要在原地删除重复出现的元素，使得每个元素最多出现两次，返回移除后数组的新长度。
 * <p>
 * 不要使用额外的数组空间，你必须在原地修改输入数组并在使用 O(1) 额外空间的条件下完成。
 * <p>
 * 示例 1:
 * <p>
 * 给定 nums = [1,1,1,2,2,3],
 * <p>
 * 函数应返回新长度 length = 5, 并且原数组的前五个元素被修改为 1, 1, 2, 2, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * 示例 2:
 * <p>
 * 给定 nums = [0,0,1,1,1,1,2,3,3],
 * <p>
 * 函数应返回新长度 length = 7, 并且原数组的前五个元素被修改为 0, 0, 1, 1, 2, 3, 3 。
 * <p>
 * 你不需要考虑数组中超出新长度后面的元素。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/remove-duplicates-from-sorted-array-ii
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-29
 */
public class Topic080 {
    /**
     * 比较暴力的算法，效率不高
     */
    public static class Solution1 {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int len = nums.length;
            int count = 1;
            int num = nums[0];
            for (int i = 1; i < len; i++) {
                if (nums[i] == num) {
                    if (count == 2) {
                        moveToLast(i, nums);
                        len--;
                        i--;
                    } else {
                        count++;
                    }
                } else {
                    num = nums[i];
                    count = 1;
                }
            }
            return len;
        }

        private void moveToLast(int i, int[] nums) {
            int temp = nums[i];
            for (int j = i; j < nums.length - 1; j++) {
                nums[j] = nums[j + 1];
            }
            nums[nums.length - 1] = temp;
        }
    }


    public static class Solution2 {
        public int removeDuplicates(int[] nums) {
            if (nums.length == 0) {
                return 0;
            }
            int index = 1;
            int count = 1;
            for (int i = 1; i < nums.length; i++) {
                if (nums[i] == nums[i - 1]) {
                    count++;
                } else {
                    count = 1;
                }
                if (count <= 2) {
                    nums[index++] = nums[i];
                }
            }
            return index;
        }
    }

    /**
     * 评论中看到的优秀解法
     */
    public static class Solution3 {
        public int removeDuplicates(int[] nums) {
            int i = 0;
            for (int num : nums) {
                if (i < 2 || num > nums[i - 2]) {
                    nums[i++] = num;
                }
            }
            return i;
        }
    }
}
