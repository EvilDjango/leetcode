package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 247. 中心对称数 II
 * 中心对称数是指一个数字在旋转了 180 度之后看起来依旧相同的数字（或者上下颠倒地看）。
 * <p>
 * 找到所有长度为 n 的中心对称数。
 * <p>
 * 示例 :
 * <p>
 * 输入:  n = 2
 * 输出: ["11","69","88","96"]
 * 通过次数4,679提交次数8,994
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/20/21 3:38 PM
 */
public class Topic247 {
    public static class Solution1 {
        private Map<Character, Character> map = new HashMap<>();

        {
            map.put('0', '0');
            map.put('1', '1');
            map.put('8', '8');
            map.put('6', '9');
            map.put('9', '6');
        }

        public List<String> findStrobogrammatic(int n) {
            List<String> ans = new ArrayList<>();
            findStrobogrammatic(ans, n, 0, new StringBuilder());
            return ans;
        }

        private void findStrobogrammatic(List<String> ans, int n, int len, StringBuilder sb) {
            if (len == (n + 1) / 2) {
                char c = sb.charAt(len - 1);
                if (n % 2 == 0 || c == '0' || c == '1' || c == '8') {
                    int start = n % 2 == 0 ? len - 1 : len - 2;

                    for (int i = start; i >= 0; i--) {
                        sb.append(map.get(sb.charAt(i)));
                    }
                    ans.add(sb.toString());
                }
                sb.delete(len, sb.length());
                return;
            }
            for (Character c : map.keySet()) {
                // 0不能作为多位数的开头
                if (c == '0' && n > 1 && len == 0) {
                    continue;
                }
                sb.append(c);
                findStrobogrammatic(ans, n, len + 1, sb);
                sb.delete(len, sb.length());
            }
        }
    }

    /**
     * 参考优秀题解
     */
    public static class Solution2 {
        public List<String> findStrobogrammatic(int n) {
            return findStrobogrammatic(n, n);
        }

        private List<String> findStrobogrammatic(int n, int m) {
            if (m == 0) {
                return Collections.singletonList("");
            } else if (m == 1) {
                return Arrays.asList("0", "1", "8");
            }
            List<String> sub = findStrobogrammatic(n, m - 2);
            List<String> ret = new ArrayList<>();
            for (String s : sub) {
                if (m != n) {
                    ret.add("0" + s + "0");
                }
                ret.add("1" + s + "1");
                ret.add("6" + s + "9");
                ret.add("8" + s + "8");
                ret.add("9" + s + "6");
            }
            return ret;
        }
    }
}
