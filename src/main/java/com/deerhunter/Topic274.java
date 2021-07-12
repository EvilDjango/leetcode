package com.deerhunter;

import java.util.Arrays;

/**
 * 274. H 指数
 * 给定一位研究者论文被引用次数的数组（被引用次数是非负整数）。编写一个方法，计算出研究者的 h 指数。
 * <p>
 * h 指数的定义：h 代表“高引用次数”（high citations），一名科研人员的 h 指数是指他（她）的 （N 篇论文中）总共有 h 篇论文分别被引用了至少 h 次。且其余的 N - h 篇论文每篇被引用次数 不超过 h 次。
 * <p>
 * 例如：某人的 h 指数是 20，这表示他已发表的论文中，每篇被引用了至少 20 次的论文总共有 20 篇。
 * <p>
 * <p>
 * <p>
 * 示例：
 * <p>
 * 输入：citations = [3,0,6,1,5]
 * 输出：3
 * 解释：给定数组表示研究者总共有 5 篇论文，每篇论文相应的被引用了 3, 0, 6, 1, 5 次。
 * 由于研究者有 3 篇论文每篇 至少 被引用了 3 次，其余两篇论文每篇被引用 不多于 3 次，所以她的 h 指数是 3。
 * <p>
 * <p>
 * 提示：如果 h 有多种可能的值，h 指数是其中最大的那个。
 * <p>
 * 通过次数47,992提交次数110,337
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/12/21 2:51 PM
 */
public class Topic274 {
    public static class Solution1 {
        public int hIndex(int[] citations) {
            int len = citations.length;
            int h = len;
            Arrays.sort(citations);
            for (int i = citations.length - 1; i >= 0; i--) {
                int citation = citations[i];
                if (citation < h) {
                    h = Math.max(citation, len - i - 1);
                }
                if (h <= len - i) {
                    break;
                }
            }
            return h;
        }
    }

    public static class Solution2 {
        public int hIndex(int[] citations) {
            Arrays.sort(citations);
            int i = citations.length - 1;
            int h = 0;
            while (i >= 0 && h < citations[i]) {
                h++;
                i--;
            }
            return h;
        }
    }

    /**
     * 基数排序
     */
    public static class Solution3 {
        public int hIndex(int[] citations) {
            int len = citations.length;
            int[] sorted = new int[len + 1];
            for (int citation : citations) {
                if (citation >= len) {
                    sorted[len]++;
                } else {
                    sorted[citation]++;
                }
            }
            int count = 0;
            for (int i = len; i >= 0; i--) {
                count += sorted[i];
                if (count >= i) {
                    return i;
                }
            }

            return 0;
        }
    }
}
