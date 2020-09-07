package com.deerhunter.common;

import com.deerhunter.tree.TreeNode;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-01
 */
public class Utils {
    private Utils() {
    }

    /**
     * 创建一个链表
     *
     * @param nums
     * @return
     */
    public static ListNode createLinkList(int... nums) {
        if (null == nums || nums.length == 0) {
            return null;
        }
        ListNode head = new ListNode(nums[0]);
        ListNode cur = head;
        for (int i = 1; i < nums.length; i++) {
            cur.next = new ListNode(nums[i]);
            cur = cur.next;
        }
        return head;
    }

    /**
     * 合并两个升序链表
     *
     * @param l1
     * @param l2
     * @return
     */
    public static ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0);
        ListNode cur = dummy;
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                cur.next = l1;
                l1 = l1.next;
            } else {
                cur.next = l2;
                l2 = l2.next;
            }
            cur = cur.next;
        }
        cur.next = l1 != null ? l1 : l2;
        return dummy.next;
    }

    public static int binarySearch(int[] nums, int target) {
        return binarySearch(nums, target, 0, nums.length);
    }

    /**
     * 二分查找第一个等于target的元素下标
     *
     * @param nums
     * @param target
     * @param start
     * @param end
     * @return
     */
    public static int binarySearch(int[] nums, int target, int start, int end) {
        int lowerBound = lowerBound(nums, target, start, end);
        if (lowerBound < nums.length && nums[lowerBound] == target) {
            return lowerBound;
        }
        return -1;
    }


    public static int lowerBound(int[] nums, int target) {
        return lowerBound(nums, target, 0, nums.length);
    }

    /**
     * 查找升序数组中第一个不小于target的元素下标
     *
     * @param nums   升序数组
     * @param target 目标值
     * @param start  查找起始下标，包含
     * @param end    查找终止下标，不包含
     * @return
     */
    public static int lowerBound(int[] nums, int target, int start, int end) {
        if (nums == null || nums.length == 0 || start > nums.length - 1) {
            return -1;
        }
        int left = start;
        int right = end > nums.length ? nums.length : end;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (target <= nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }


    public static int upperBound(int[] nums, int target) {
        return upperBound(nums, target, 0, nums.length);
    }

    /**
     * 查找升序数组中第一个大于target的元素下标
     *
     * @param nums   升序数组
     * @param target 目标值
     * @param start  查找起始下标，包含
     * @param end    查找终止下标，不包含
     * @return
     */
    public static int upperBound(int[] nums, int target, int start, int end) {
        if (nums == null || nums.length == 0 || start > nums.length - 1) {
            return -1;
        }
        int left = 0;
        int right = end > nums.length ? nums.length : end;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left) / 2;
            if (target < nums[mid]) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        return left;
    }

    /**
     * 查找降序数组中最后一个不小于target的元素下标
     *
     * @param nums   降序数组
     * @param target
     * @return
     */
    public static int reversedLowerBound(int[] nums, int target) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int left = -1;
        int right = nums.length - 1;
        while (left < right) {
            // 防止溢出
            int mid = left + (right - left + 1) / 2;
            if (nums[mid] >= target) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    /**
     * 查找降序数组中最后一个大于target的元素下标
     *
     * @param nums   降序数组
     * @param target
     * @param left
     * @param right
     * @return
     */
    public static int reversedUpperBound(int[] nums, int target, int left, int right) {
        if (nums == null || nums.length == 0) {
            return -1;
        }
        int l = left - 1 < -1 ? -1 : left - 1;
        int r = right > nums.length ? nums.length - 1 : right - 1;
        while (l < r) {
            // 防止溢出
            int mid = l + (r - l + 1) / 2;
            if (nums[mid] > target) {
                l = mid;
            } else {
                r = mid - 1;
            }
        }
        return l;
    }

    public static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    public static void swap(int[][] matrix, int x1, int y1, int x2, int y2) {
        int temp = matrix[x1][y1];
        matrix[x1][y1] = matrix[x2][y2];
        matrix[x2][y2] = temp;
    }

    public static void printArray(char[][] arr) {
        System.out.println(arrToStr(arr));
    }


    public static String arrToStr(char[][] arr) {
        StringBuilder sb = new StringBuilder();
        for (char[] row : arr) {
            sb.append(Arrays.toString(row));
            sb.append("\n");
        }
        // 删掉最后一个多余的换行符
        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }
        return sb.toString();
    }


    public static void printArray(int[][] arr, int bit) {
        String format = "%" + bit + "d";
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr[i].length; j++) {
                System.out.printf(format, arr[i][j]);
                if (j != arr[i].length - 1) {
                    System.out.print(", ");
                }
            }
            System.out.println();
        }
    }

    /**
     * 深度复制一个树结构
     *
     * @param head
     * @return
     */
    public static TreeNode copyTree(TreeNode head) {
        if (head == null) {
            return null;
        }
        TreeNode newHead = new TreeNode(head.val);
        dfsCopyTree(head, newHead);
        return newHead;
    }

    private static void dfsCopyTree(TreeNode head, TreeNode newHead) {
        if (head.left != null) {
            newHead.left = new TreeNode(head.left.val);
            dfsCopyTree(head.left, newHead.left);
        }
        if (head.right != null) {
            newHead.right = new TreeNode(head.right.val);
            dfsCopyTree(head.right, newHead.right);
        }
    }


    public static void checkArrayEqual(int[][] expected, int[][] actual) {
        if (expected.length != actual.length) {
            throw new RuntimeException(String.format("Length not equal. Expect: %d, Actual: %d", expected.length, actual.length));
        }
        for (int i = 0; i < expected.length; i++) {
            int[] expectedRow = expected[i];
            int[] row = actual[i];
            if (expectedRow.length != row.length) {
                throw new RuntimeException(String.format("Row length not equal. Expect: %d, Actual: %d. Row index: %d", expectedRow.length, row.length, i));
            }
            for (int j = 0; j < expectedRow.length; j++) {
                if (expectedRow[j] != row[j]) {
                    throw new RuntimeException(String.format("Expected: %d, Actual: %d, Row: %d, Column: %d", expectedRow[j], row[j], i, j));
                }
            }
        }
    }

    public static boolean isArrayEqual(int[][] expected, int[][] actual) {
        try {
            checkArrayEqual(expected, actual);
            return true;
        } catch (RuntimeException e) {
            return false;
        }
    }

    public static long factorial(int n) {
        int ret = 1;
        for (int i = 2; i <= n; i++) {
            ret *= i;
        }
        return ret;
    }

    /**
     * 将十进制数转换为二进制表示
     * <p>
     * 从低到高转换
     *
     * @param n
     * @return
     */
    public static String getBinary(int n) {
        if (n == 0) {
            return "0";
        }
        StringBuilder sb = new StringBuilder();
        while (n > 0) {
            int bit = n % 2;
            sb.append(bit);
            n /= 2;
        }
        return sb.reverse().toString();
    }

    /**
     * 将十进制数转换为二进制表示
     * <p>
     * 从高到低转换
     *
     * @param n
     * @return
     */
    public static String getBinary2(int n) {
        if (n == 0) {
            return "0";
        }
        List<Integer> powers = new ArrayList<>();
        powers.add(1);
        while (powers.get(powers.size() - 1) <= n) {
            powers.add(powers.get(powers.size() - 1) * 2);
        }
        StringBuilder sb = new StringBuilder();
        for (int i = powers.size() - 2; i >= 0; i--) {
            int bit = n / powers.get(i);
            sb.append(bit);
            n -= bit * powers.get(i);
        }
        return sb.toString();
    }


}
