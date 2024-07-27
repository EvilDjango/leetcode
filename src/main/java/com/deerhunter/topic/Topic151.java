package com.deerhunter.topic;

/**
 * 给定一个字符串，逐个翻转字符串中的每个单词。
 * <p>
 * 说明：
 * <p>
 * 无空格字符构成一个 单词 。
 * 输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 *  
 * <p>
 * 示例 1：
 * <p>
 * 输入："the sky is blue"
 * 输出："blue is sky the"
 * 示例 2：
 * <p>
 * 输入："  hello world!  "
 * 输出："world! hello"
 * 解释：输入字符串可以在前面或者后面包含多余的空格，但是反转后的字符不能包括。
 * 示例 3：
 * <p>
 * 输入："a good   example"
 * 输出："example good a"
 * 解释：如果两个单词间有多余的空格，将反转后单词间的空格减少到只含一个。
 * 示例 4：
 * <p>
 * 输入：s = "  Bob    Loves  Alice   "
 * 输出："Alice Loves Bob"
 * 示例 5：
 * <p>
 * 输入：s = "Alice does not even like bob"
 * 输出："bob like even not does Alice"
 *  
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 104
 * s 包含英文大小写字母、数字和空格 ' '
 * s 中 至少存在一个 单词
 *  
 * <p>
 * 进阶：
 * <p>
 * 请尝试使用 O(1) 额外空间复杂度的原地解法。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/reverse-words-in-a-string
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/11/12 16:51
 */
public class Topic151 {

    /**
     * 循环，O(1)空间复杂度
     */
    public static class Solution1 {
        public String reverseWords(String s) {
            s = s.trim() + "$";
            int tail = s.length() - 1;
            boolean finished = false;
            while (!finished) {
                int i = 0;
                while (s.charAt(i) != ' ' && s.charAt(i) != '$') {
                    i++;
                }
                finished = s.charAt(i) == '$';
                String right = s.substring(i + 1, tail + 1);
                int leadingSpaces = leadingSpaces(right);
                right = right.substring(leadingSpaces);
                String left = s.substring(0, i);
                s = right + " " + left + s.substring(tail + 1);
                tail -= (left.length() + leadingSpaces + 1);
            }
            return s.substring(1);
        }

        private int leadingSpaces(String s) {
            int i = 0;
            while (i < s.length() && s.charAt(i) == ' ') {
                i++;
            }
            return i;

        }
    }

    /**
     * 递归
     */
    public static class Solution2 {
        public String reverseWords(String s) {
            s = s.trim();
            int blankIndex = -1;
            for (int i = 0; i < s.length(); i++) {
                if (s.charAt(i) == ' ') {
                    blankIndex = i;
                    break;
                }
            }
            if (blankIndex == -1) {
                return s;
            }
            return reverseWords(s.substring(blankIndex + 1)) + " " + s.substring(0, blankIndex);
        }
    }
}
