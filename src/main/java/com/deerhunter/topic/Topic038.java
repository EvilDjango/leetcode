package com.deerhunter.topic;

/**
 * 报数序列是一个整数序列，按照其中的整数的顺序进行报数，得到下一个数。其前五项如下：
 * <p>
 * 1.     1
 * 2.     11
 * 3.     21
 * 4.     1211
 * 5.     111221
 * 1 被读作  "one 1"  ("一个一") , 即 11。
 * 11 被读作 "two 1s" ("两个一"）, 即 21。
 * 21 被读作 "one 2",  "one 1" （"一个二" ,  "一个一") , 即 1211。
 * <p>
 * 给定一个正整数 n（1 ≤ n ≤ 30），输出报数序列的第 n 项。
 * <p>
 * 注意：整数顺序将表示为一个字符串。
 * <p>
 *  
 * <p>
 * 示例 1:
 * <p>
 * 输入: 1
 * 输出: "1"
 * 示例 2:
 * <p>
 * 输入: 4
 * 输出: "1211"
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/count-and-say
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-18
 */
public class Topic038 {
    public static String countAndSay(int n) {
        if (n == 1) {
            return "1";
        }
        return count(countAndSay(n - 1));

    }

    /**
     * 获取基于一个字符串的报数结果
     *
     * @param s
     * @return
     */
    private static String count(String s) {
        StringBuilder sb = new StringBuilder();
        char temp = s.charAt(0);
        int cnt = 1;
        for (int i = 1; i < s.length(); i++) {
            if (temp == s.charAt(i)) {
                cnt++;
            } else {
                sb.append(cnt);
                sb.append(temp);
                temp = s.charAt(i);
                cnt = 1;
            }
        }
        sb.append(cnt);
        sb.append(temp);
        return sb.toString();
    }
}
