package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * 给定一个单词数组和一个长度 maxWidth，重新排版单词，使其成为每行恰好有 maxWidth 个字符，且左右两端对齐的文本。
 * <p>
 * 你应该使用“贪心算法”来放置给定的单词；也就是说，尽可能多地往每行中放置单词。必要时可用空格 ' ' 填充，使得每行恰好有 maxWidth 个字符。
 * <p>
 * 要求尽可能均匀分配单词间的空格数量。如果某一行单词间的空格不能均匀分配，则左侧放置的空格数要多于右侧的空格数。
 * <p>
 * 文本的最后一行应为左对齐，且单词之间不插入额外的空格。
 * <p>
 * 说明:
 * <p>
 * 单词是指由非空格字符组成的字符序列。
 * 每个单词的长度大于 0，小于等于 maxWidth。
 * 输入单词数组 words 至少包含一个单词。
 * 示例:
 * <p>
 * 输入:
 * words = ["This", "is", "an", "example", "of", "text", "justification."]
 * maxWidth = 16
 * 输出:
 * [
 *    "This    is    an",
 *    "example  of text",
 *    "justification.  "
 * ]
 * 示例 2:
 * <p>
 * 输入:
 * words = ["What","must","be","acknowledgment","shall","be"]
 * maxWidth = 16
 * 输出:
 * [
 *   "What   must   be",
 *   "acknowledgment  ",
 *   "shall be        "
 * ]
 * 解释: 注意最后一行的格式应为 "shall be    " 而不是 "shall     be",
 *      因为最后一行应为左对齐，而不是左右两端对齐。
 * 第二行同样为左对齐，这是因为这行只包含一个单词。
 * 示例 3:
 * <p>
 * 输入:
 * words = ["Science","is","what","we","understand","well","enough","to","explain",
 *          "to","a","computer.","Art","is","everything","else","we","do"]
 * maxWidth = 20
 * 输出:
 * [
 *   "Science  is  what we",
 * "understand      well",
 *   "enough to explain to",
 *   "a  computer.  Art is",
 *   "everything  else  we",
 *   "do                  "
 * ]
 * 通过次数6,979提交次数16,233
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/text-justification
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2020-03-24
 */
public class Topic068 {
    public static class Solution1 {
        public List<String> fullJustify(String[] words, int maxWidth) {
            List<String> ans = new ArrayList<>();
            if (words == null || words.length == 0) {
                return ans;
            }

            List<String> row = new ArrayList<>();
            int curLen = 0;

            for (int i = 0; i < words.length; i++) {
                String word = words[i];
                int nextLen = row.size() > 0 ? curLen + word.length() + 1 : curLen + word.length();
                if (nextLen > maxWidth) {
                    ans.add(processRow(row, maxWidth, curLen));
                    row.clear();
                    curLen = 0;
                }
                curLen += row.size() > 0 ? word.length() + 1 : word.length();
                row.add(word);

            }

            ans.add(processLastRow(row, maxWidth));
            return ans;
        }

        private String processLastRow(List<String> row, int maxWidth) {
            StringBuilder sb = new StringBuilder(row.get(0));
            for (int i = 1; i < row.size(); i++) {
                sb.append(" ").append(row.get(i));
            }
            while (sb.length() < maxWidth) {
                sb.append(" ");
            }
            return sb.toString();
        }

        /**
         * 处理一行
         *
         * @param row      单词列表
         * @param maxWidth 总长度
         * @param curLen   单词仅间隔一个空格的长度
         * @return
         */
        private String processRow(List<String> row, int maxWidth, int curLen) {
            StringBuilder sb = new StringBuilder();
            if (row.size() == 1) {
                sb.append(row.get(0));
                while (sb.length() < maxWidth) {
                    sb.append(" ");
                }
                return sb.toString();
            }

            // 间隔数
            int interspaces = row.size() - 1;
            // 最小间隔距离
            int interspaceLen = (maxWidth - curLen) / interspaces + 1;
            // 大间隔数量
            int bigInterspaces = (maxWidth - curLen) % interspaces;

            for (int i = 0; i < row.size(); i++) {
                sb.append(row.get(i));

                // 最后一个单词后面无需加空格
                if (i != row.size() - 1) {
                    int len = bigInterspaces > i ? interspaceLen + 1 : interspaceLen;
                    for (int j = 0; j < len; j++) {
                        sb.append(" ");
                    }
                }
            }
            return sb.toString();
        }
    }

}
