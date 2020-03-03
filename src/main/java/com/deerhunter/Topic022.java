package com.deerhunter;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 给出 n 代表生成括号的对数，请你写出一个函数，使其能够生成所有可能的并且有效的括号组合。
 * <p>
 * 例如，给出 n = 3，生成结果为：
 * <p>
 * [
 * "((()))",
 * "(()())",
 * "(())()",
 * "()(())",
 * "()()()"
 * ]
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/generate-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-09-01
 */
public class Topic022 {
    public static List<String> generateParenthesis(int n) {
        List<String> ret = new LinkedList<>();
        generateParenthesis(n, 0, 0, "", ret);
        return ret;
    }

    private static void generateParenthesis(int n, int leftCnt, int rightCnt, String temp, List<String> results) {
        if (temp.length() == n * 2) {
            results.add(temp);
            return;
        }
        if (leftCnt < n) {
            generateParenthesis(n, leftCnt + 1, rightCnt, temp + '(', results);
        }
        if (rightCnt < leftCnt) {
            generateParenthesis(n, leftCnt, rightCnt + 1, temp + ')', results);
        }
    }

    /**
     * 复制的官方题解闭合数解法
     *
     * @param n
     * @return
     */
    public static List<String> generateParenthesis2(int n) {
        List<String> ans = new ArrayList();
        if (n == 0) {
            ans.add("");
        } else {
            for (int c = 0; c < n; ++c) {
                for (String left : generateParenthesis2(c)) {
                    for (String right : generateParenthesis2(n - 1 - c)) {
                        ans.add("(" + left + ")" + right);
                    }
                }
            }
        }
        return ans;
    }

}
