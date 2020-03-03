package com.deerhunter;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-08-13
 */
public class Topic007 {
    public int reverse(int x) {
        String reversed = "";
        String num = x + "";
        boolean negetive = x < 0;
        for (int i = num.length() - 1; i >= 0; i--) {
            if (num.charAt(i) == '-') {
                break;
            }
            reversed += num.charAt(i);
        }
        int anInt;
        try {
            anInt = Integer.parseInt(reversed);
        } catch (NumberFormatException e) {
            return 0;
        }

        if (negetive) {
            return -anInt;
        }
        return anInt;
    }
}
