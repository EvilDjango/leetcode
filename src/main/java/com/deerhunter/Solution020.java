package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串，判断字符串是否有效。
 * <p>
 * 有效字符串需满足：
 * <p>
 * 左括号必须用相同类型的右括号闭合。
 * 左括号必须以正确的顺序闭合。
 * 注意空字符串可被认为是有效字符串。
 * <p>
 * 示例 1:
 * <p>
 * 输入: "()"
 * 输出: true
 * 示例 2:
 * <p>
 * 输入: "()[]{}"
 * 输出: true
 * 示例 3:
 * <p>
 * 输入: "(]"
 * 输出: false
 * 示例 4:
 * <p>
 * 输入: "([)]"
 * 输出: false
 * 示例 5:
 * <p>
 * 输入: "{[]}"
 * 输出: true
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/valid-parentheses
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-31
 */
public class Solution020 {
    private static final Map<Character, Character> closeByStart = new HashMap<>();

    static {
        closeByStart.put('(', ')');
        closeByStart.put('[', ']');
        closeByStart.put('{', '}');
    }

    public static boolean isValid(String s) {
        if (s.length() % 2 != 0) {
            return false;
        }
        char[] stack = new char[s.length() / 2];
        int index = -1;
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            if (closeByStart.containsKey(c)) {
                // 起始符号已经超过字符串的一半，必然无法全部闭合
                if (index == s.length() / 2-1) {
                    return false;
                }
                stack[++index] = c;
            } else {
                if (index != -1 && c == closeByStart.get(stack[index])) {
                    index--;
                } else {
                    return false;
                }
            }
        }
        return index == -1;
    }
}