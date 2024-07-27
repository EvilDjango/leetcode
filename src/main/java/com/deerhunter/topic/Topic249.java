package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.List;

/**
 * class Solution {
 * public List<List<String>> groupStrings(String[] strings) {
 * <p>
 * }
 * }
 * <p>
 * Copyright (c) @2021 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 7/22/21 10:05 PM
 */
public class Topic249 {
    public static class Solution1 {
        public List<List<String>> groupStrings(String[] strings) {
            List<List<String>> ans = new ArrayList<>();
            boolean[] grouped = new boolean[strings.length];
            for (int i = 0; i < strings.length; i++) {
                if (grouped[i]) {
                    continue;
                }
                List<String> strs = new ArrayList<>();
                strs.add(strings[i]);
                for (int j = i + 1; j < strings.length; j++) {
                    if (grouped[j]) {
                        continue;
                    }
                    if (canTransform(strings[i], strings[j])) {
                        strs.add(strings[j]);
                        grouped[j] = true;
                    }
                }
                ans.add(strs);
            }
            return ans;
        }

        private boolean canTransform(String s, String t) {
            if (s.length() != t.length()) {
                return false;
            }
            for (int i = 1; i < s.length(); i++) {
                if ((s.charAt(i) - s.charAt(i - 1) - t.charAt(i) + t.charAt(i - 1)) % 26 != 0) {
                    return false;
                }
            }
            return true;
        }
    }
}
