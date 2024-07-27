package com.deerhunter.topic;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Queue;

/**
 * 248. 中心对称数 III
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * <p>
 * 写一个函数来计算范围在 [low, high] 之间中心对称数的个数。
 * <p>
 * 示例:
 * <p>
 * 输入: low = "50", high = "100"
 * 输出: 3
 * 解释: 69，88 和 96 是三个在该范围内的中心对称数
 * 注意:
 * 由于范围可能很大，所以 low 和 high 都用字符串表示。
 * <p>
 * 通过次数2,444提交次数5,241
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/22/21 7:19 PM
 */
public class Topic248 {
    /**
     * 递归
     */
    public static class Solution1 {
        public int strobogrammaticInRange(String low, String high) {
            int ans = 0;
            int m = low.length(), n = high.length();
            for (int i = m; i <= n; i++) {
                List<String> strs = strobogrammatic(i, i);
                if (i > m && i < n) {
                    ans += strs.size();
                    continue;
                }
                for (String str : strs) {
                    if (gte(str, low) && gte(high, str)) {
                        ans++;
                    }
                }
            }
            return ans;
        }

        private List<String> strobogrammatic(int total, int left) {
            if (left == 0) {
                return Collections.singletonList("");
            } else if (left == 1) {
                return Arrays.asList("0", "1", "8");
            }
            List<String> ans = new ArrayList<>();
            List<String> subs = strobogrammatic(total, left - 2);
            for (String sub : subs) {
                if (total != left) {
                    ans.add("0" + sub + "0");
                }
                ans.add("1" + sub + "1");
                ans.add("6" + sub + "9");
                ans.add("8" + sub + "8");
                ans.add("9" + sub + "6");
            }
            return ans;
        }


    }


    /**
     * 使用队列，从底到顶
     */
    public static class Solution2 {
        public int strobogrammaticInRange(String low, String high) {
            Queue<String> queue = new ArrayDeque<>();
            queue.add("");
            queue.add("0");
            queue.add("1");
            queue.add("8");
            int ans = 0;
            while (!queue.isEmpty()) {
                String num = queue.remove();
                if (!(num.length() > 1 && num.charAt(0) == '0') && gte(num, low) && gte(high, num)) {
                    ans++;
                }
                if (num.length() + 2 > high.length()) {
                    continue;
                }
                queue.add("0" + num + "0");
                queue.add("1" + num + "1");
                queue.add("6" + num + "9");
                queue.add("8" + num + "8");
                queue.add("9" + num + "6");
            }
            return ans;
        }

    }

    /**
     * 判断数字i是否大于等于数字j
     *
     * @param i
     * @param j
     * @return
     */
    private static boolean gte(String i, String j) {
        int m = i.length(), n = j.length();
        if (m != n) {
            return m > n;
        }
        for (int k = 0; k < m; k++) {
            if (i.charAt(k) != j.charAt(k)) {
                return i.charAt(k) > j.charAt(k);
            }
        }
        return true;
    }
}
