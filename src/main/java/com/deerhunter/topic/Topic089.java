package com.deerhunter.topic;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

/**
 * 格雷编码是一个二进制数字系统，在该系统中，两个连续的数值仅有一个位数的差异。
 * <p>
 * 给定一个代表编码总位数的非负整数 n，打印其格雷编码序列。格雷编码序列必须以 0 开头。
 * <p>
 * 示例 1:
 * <p>
 * 输入: 2
 * 输出: [0,1,3,2]
 * 解释:
 * 00 - 0
 * 01 - 1
 * 11 - 3
 * 10 - 2
 * <p>
 * 对于给定的 n，其格雷编码序列并不唯一。
 * 例如，[0,2,3,1] 也是一个有效的格雷编码序列。
 * <p>
 * 00 - 0
 * 10 - 2
 * 11 - 3
 * 01 - 1
 * 示例 2:
 * <p>
 * 输入: 0
 * 输出: [0]
 * 解释: 我们定义格雷编码序列必须以 0 开头。
 *      给定编码总位数为 n 的格雷编码序列，其长度为 2n。当 n = 0 时，长度为 20 = 1。
 *      因此，当 n = 0 时，其格雷编码序列为 [0]。
 * <p>
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/gray-code
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/4/11 01:41
 */
public class Topic089 {
    /**
     * 暴力法：每次去遍历与上一个格雷码只差一位的二进制数，如果该二进制数还没有被使用，则选用，进入下一次循环
     */
    public static class Solution1 {
        public List<Integer> grayCode(int n) {
            int len = (int) Math.pow(2, n);
            boolean[] used = new boolean[len];
            List<Integer> ret = new ArrayList<>();
            ret.add(0);
            used[0] = true;
            int prev = 0;
            while (ret.size() < len) {
                int num = prev;
                int base = 1;
                for (int j = 0; j < n; j++) {
                    int bit = num % 2;
                    int match = bit == 1 ? prev - base : prev + base;
                    if (!used[match]) {
                        used[match] = true;
                        ret.add(match);
                        prev = match;
                        break;
                    }
                    base *= 2;
                    num /= 2;
                }
            }
            return ret;
        }
    }

    /**
     * 直接排列
     * <p>
     * 以二进制为0值的格雷码为第零项，第一项改变最右边的位元，第二项改变右起第一个为1的位元的左边位元，第三、四项方法同第一、二项，如此反复，即可排列出n个位元的格雷码。
     */
    public static class Solution2 {
        public List<Integer> grayCode(int n) {
            List<Integer> ret = new ArrayList<>();
            if (n == 0) {
                ret.add(0);
                return ret;
            }

            int last = 0;
            while (last < Math.pow(2, n)) {
                ret.add(last);
                last += last % 2 == 0 ? 1 : -1;
                ret.add(last);

                int temp = last;
                int base = 1;
                int bit;
                do {
                    bit = temp % 2;
                    temp /= 2;
                    base *= 2;
                } while (bit != 1);

                last += temp % 2 == 0 ? base : -base;
            }
            return ret;
        }
    }

    /**
     * 镜射排列
     * <p>
     * 二进制格雷码镜射建构法
     * n位元的格雷码可以从n-1位元的格雷码以上下镜射后加上新位元的方式快速的得到，
     */
    public static class Solution3 {
        public List<Integer> grayCode(int n) {
            List<Integer> result = new ArrayList<>();
            result.add(0);
            for (int i = 0; i < n; i++) {
                int base = (int) Math.pow(2, i);
                for (int j = result.size() - 1; j >= 0; j--) {
                    result.add(result.get(j) + base);
                }
            }
            return result;
        }
    }

    /**
     * 提交记录汇总看到的最快的解法
     *
     * 原理：
     * 二进制数转格雷码
     * （假设以二进制为0的值做为格雷码的0）
     * G：格雷码 B：二进制码 n：正在计算的位
     * 根据格雷码的定义可得：
     * G(n) = B(n+1) XOR B(n)
     * 即
     * G(n) = B(n+1) + B(n)
     * 自低位至高位运算即可，无需考虑进位。
     */
    public static class Solution4 {
        public List<Integer> grayCode(int n) {
            List<Integer> ret = new ArrayList<>();
            for (int i = 0; i < 1 << n; ++i)
                ret.add(i ^ i >> 1);
            return ret;
        }
    }
}
