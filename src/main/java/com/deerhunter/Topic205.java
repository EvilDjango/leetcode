package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2021/1/27 21:26
 */
public class Topic205 {
    public static class Solution {
        public boolean isIsomorphic(String s, String t) {
            Map<Character, Character> tByS = new HashMap<>();
            Map<Character, Character> sByT = new HashMap<>();
            for (int i = 0; i < s.length(); i++) {
                char sChar = s.charAt(i);
                char tChar = t.charAt(i);
                if (tByS.containsKey(sChar) && tByS.get(sChar) != tChar) {
                    return false;
                }
                if (sByT.containsKey(tChar) && sByT.get(tChar) != sChar) {
                    return false;
                }
                tByS.put(sChar, tChar);
                sByT.put(tChar, sChar);
            }
            return true;
        }
    }
}
