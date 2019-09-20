package com.deerhunter;

import java.util.Queue;

/**
 * 多种排序算法
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-19
 */
public class Sorts {
    public static void bucketSortRecursive(int[] nums) {
        int nBucket = (int) (Math.log(nums.length) / Math.log(2));
        int[][] buckets = new int[nBucket][];
        for (int i = 0; i < nBucket; i++) {
            buckets[i] = new int[nums.length / nBucket + 1];
        }


    }

    public static void main(String[] args) {
        System.out.println(Math.log(8) / Math.log(2));
        System.out.println(Math.log(8));
        System.out.println(Math.log(2));
    }
}