package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * 187. Repeated DNA Sequences
 * Medium
 * <p>
 * 1063
 * <p>
 * 325
 * <p>
 * Add to List
 * <p>
 * Share
 * All DNA is composed of a series of nucleotides abbreviated as 'A', 'C', 'G', and 'T', for example: "ACGAATTCCG". When studying DNA, it is sometimes useful to identify repeated sequences within the DNA.
 * <p>
 * Write a function to find all the 10-letter-long sequences (substrings) that occur more than once in a DNA molecule.
 * <p>
 * <p>
 * <p>
 * Example 1:
 * <p>
 * Input: s = "AAAAACCCCCAAAAACCCCCCAAAAAGGGTTT"
 * Output: ["AAAAACCCCC","CCCCCAAAAA"]
 * Example 2:
 * <p>
 * Input: s = "AAAAAAAAAAAAA"
 * Output: ["AAAAAAAAAA"]
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/4 10:06
 */
public class Topic187 {
    /**
     * 暴力算法，会超时
     */
    public static class Solution1 {
        public List<String> findRepeatedDnaSequences(String s) {
            List<String> res = new ArrayList<>();
            for (int i = 0; i < s.length() - 9; i++) {
                String sub = s.substring(i, i + 10);
                if (!res.contains(sub) && findDuplicate(s, sub, i + 1)) {
                    res.add(sub);
                }
            }
            return res;
        }

        private boolean findDuplicate(String s, String sub, int start) {
            for (int j = start; j < s.length() - 9; j++) {
                if (sub.equals(s.substring(j, j + 10))) {
                    return true;
                }
            }
            return false;
        }
    }

    /**
     * 使用哈希表
     */
    public static class Solution2 {
        public List<String> findRepeatedDnaSequences(String s) {
            Map<String, Integer> subCount = new HashMap<>();
            for (int i = 0; i < s.length() - 9; i++) {
                subCount.merge(s.substring(i, i + 10), 1, Integer::sum);
            }
            List<String> res = new ArrayList<>();
            for (String sub : subCount.keySet()) {
                if (subCount.get(sub) > 1) {
                    res.add(sub);
                }
            }
            return res;
        }
    }

    /**
     * Rabin-Karp算法
     */
    public static class Solution3 {
        public List<String> findRepeatedDnaSequences(String s) {
            Set<Integer> seen = new HashSet<>();
            Set<String> res = new HashSet<>();
            int hash = -1;
            for (int i = 0; i < s.length() - 9; i++) {
                String sub = s.substring(i, i + 10);
                if (hash == -1) {
                    hash = hash(sub);
                } else {
                    hash = reHash(hash, s.charAt(i - 1), s.charAt(i + 9));
                }
                if (seen.contains(hash)) {
                    res.add(sub);
                } else {
                    seen.add(hash);
                }
            }
            return new ArrayList<>(res);
        }

        private int reHash(int hash, char headToRemove, char tailToAppend) {
            return (int) (hash * 4 - toInt(headToRemove) * Math.pow(4, 10) + toInt(tailToAppend));
        }

        private int hash(String s) {
            int base = 1;
            int hash = 0;
            for (int i = s.length() - 1; i >= 0; i--) {
                hash += base * toInt(s.charAt(i));
                base *= 4;
            }
            return hash;
        }

        private int toInt(char c) {
            switch (c) {
                case 'A':
                    return 0;
                case 'T':
                    return 1;
                case 'C':
                    return 2;
                case 'G':
                    return 3;
                default:
                    return -1;
            }
        }
    }

    /**
     * 使用位运算
     */
    public static class Solution4 {
        public List<String> findRepeatedDnaSequences(String s) {
            Set<Integer> seen = new HashSet<>();
            Set<String> res = new HashSet<>();
            int hash = -1;
            for (int i = 0; i < s.length() - 9; i++) {
                String sub = s.substring(i, i + 10);
                if (hash == -1) {
                    hash = hash(sub);
                } else {
                    hash = reHash(hash, s.charAt(i - 1), s.charAt(i + 9));
                }
                if (seen.contains(hash)) {
                    res.add(sub);
                } else {
                    seen.add(hash);
                }
            }
            return new ArrayList<>(res);
        }

        private int reHash(int hash, char headToRemove, char tailToAppend) {
            return hash * 4 - toInt(headToRemove) * (1 << 20) + toInt(tailToAppend);
        }

        private int hash(String s) {
            int hash = 0;
            for (int i = 0; i < s.length(); i++) {
                hash <<= 2;
                hash += toInt(s.charAt(i));
            }
            return hash;
        }

        private int toInt(char c) {
            switch (c) {
                case 'A':
                    return 0;
                case 'T':
                    return 1;
                case 'C':
                    return 2;
                case 'G':
                    return 3;
                default:
                    return -1;
            }
        }
    }
}
