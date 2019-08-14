package com.deerhunter;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-13
 */
public class Solution006 {
    public String convert(String s, int numRows) {
        if (1 == numRows) {
            return s;
        }
        boolean down = true;
        boolean right = false;
        int[][] f = new int[numRows][(s.length()+1)/2];
        int i = 0, j = 0;
        for (int k = 0; k < s.length(); k++) {
            f[i][j] = s.charAt(k);
            if (k > 0 && (numRows - 1 == i || 0 == i)) {
                down = !down;
                right = !right;
            }
            if (down) {
                i++;
            } else {
                i--;
            }
            if (right) {
                j++;
            }
        }
        StringBuilder sb = new StringBuilder();
        for (int k = 0; k < f.length; k++) {
            for (int l = 0; l < f[k].length; l++) {
                if (0 != f[k][l]) {
                    sb.append((char) f[k][l]);
                }
            }
        }
        return sb.toString();
    }
}