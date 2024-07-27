package com.deerhunter.topic;

/**
 * 给定两个以字符串形式表示的非负整数 num1 和 num2，返回 num1 和 num2 的乘积，它们的乘积也表示为字符串形式。
 * <p>
 * 示例 1:
 * <p>
 * 输入: num1 = "2", num2 = "3"
 * 输出: "6"
 * 示例 2:
 * <p>
 * 输入: num1 = "123", num2 = "456"
 * 输出: "56088"
 * 说明：
 * <p>
 * num1 和 num2 的长度小于110。
 * num1 和 num2 只包含数字 0-9。
 * num1 和 num2 均不以零开头，除非是数字 0 本身。
 * 不能使用任何标准库的大数类型（比如 BigInteger）或直接将输入转换为整数来处理。
 * <p>
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/multiply-strings
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-20
 */
public class Topic043 {
    public String multiply(String num1, String num2) {
        int m = num1.length();
        int n = num2.length();
        byte[][] matrix = new byte[n][m + 1];
        int row = -1;
        for (int i = n - 1; i >= 0; i--) {
            byte d = (byte) Character.getNumericValue(num2.charAt(i));
            byte[] products = getProduct(num1, d);
            matrix[++row] = products;
        }

        int add = 0;
        int[] results = new int[m + n + 1];
        for (int i = m + n; i > 0; i--) {
            int sum = 0;
            for (int j = 0; j < n; j++) {
                int index = i - n + j;
                if (index < 0) {
                    continue;
                }
                if (index >= m + 1) {
                    break;
                }
                sum += matrix[j][index];
            }
            results[i] = (sum + add) % 10;
            add = (sum + add) / 10;
        }
        results[0] = add;

        StringBuilder sb = new StringBuilder();
        boolean start = false;
        for (int result : results) {
            if (result != 0) {
                start = true;
            }

            if (start) {
                sb.append(result);
            }
        }
        String ret = sb.toString();

        if ("".equals(ret)) {
            return "0";
        }
        return ret;
    }

    private byte[] getProduct(String num1, byte d) {
        byte[] ret = new byte[num1.length() + 1];
        int add = 0;
        for (int i = num1.length() - 1; i >= 0; i--) {
            byte num = (byte) Character.getNumericValue(num1.charAt(i));
            byte product = (byte) (num * d);
            ret[i + 1] = (byte) ((product + add) % 10);
            add = (product + add) / 10;
        }
        ret[0] = (byte) add;
        return ret;
    }

    static class ReferenceSolution {
        /**
         * 参考了题解 https://leetcode-cn.com/problems/multiply-strings/solution/you-hua-ban-shu-shi-da-bai-994-by-breezean/
         *
         * @param num1
         * @param num2
         * @return
         */
        public String multiply(String num1, String num2) {
            if ("0".equals(num1) || "0".equals(num2)) {
                return "0";
            }
            int[] result = new int[num1.length() + num2.length()];
            for (int i = num1.length() - 1; i >= 0; i--) {
                int d1 = num1.charAt(i) - '0';
                for (int j = num2.length() - 1; j >= 0; j--) {
                    int d2 = num2.charAt(j) - '0';
                    int sum = result[i + j + 1] + d1 * d2;
                    result[i + j + 1] = sum % 10;
                    result[i + j] += sum / 10;
                }
            }
            StringBuilder sb = new StringBuilder();
            boolean start = false;
            for (int i : result) {
                if (i != 0) {
                    start = true;
                }
                if (!start) {
                    continue;
                }
                sb.append(i);
            }
            return sb.toString();
        }

    }
}
