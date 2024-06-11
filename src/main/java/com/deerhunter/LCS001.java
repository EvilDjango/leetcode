package com.deerhunter;

import java.util.ArrayList;
import java.util.List;

/**
 * LCS 01. 下载插件
 * 小扣打算给自己的 VS code 安装使用插件，初始状态下带宽每分钟可以完成 1 个插件的下载。假定每分钟选择以下两种策略之一:
 * <p>
 * 使用当前带宽下载插件
 * 将带宽加倍（下载插件数量随之加倍）
 * 请返回小扣完成下载 n 个插件最少需要多少分钟。
 * <p>
 * 注意：实际的下载的插件数量可以超过 n 个
 * <p>
 * 示例 1：
 * <p>
 * 输入：n = 2
 * <p>
 * 输出：2
 * <p>
 * 解释：
 * 以下两个方案，都能实现 2 分钟内下载 2 个插件
 * <p>
 * 方案一：第一分钟带宽加倍，带宽可每分钟下载 2 个插件；第二分钟下载 2 个插件
 * 方案二：第一分钟下载 1 个插件，第二分钟下载 1 个插件
 * 示例 2：
 * <p>
 * 输入：n = 4
 * <p>
 * 输出：3
 * <p>
 * 解释：
 * 最少需要 3 分钟可完成 4 个插件的下载，以下是其中一种方案:
 * 第一分钟带宽加倍，带宽可每分钟下载 2 个插件;
 * 第二分钟下载 2 个插件;
 * 第三分钟下载 2 个插件。
 * <p>
 * 提示：
 * <p>
 * 1 <= n <= 10^5
 * 通过次数8,526提交次数15,591
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/6/4 下午1:58
 */
public class LCS001 {
    /**
     * 易得n分钟的最大数量为2^(n-1),那么从n=1开始计算直到总数大于要下载的查件数即可
     *
     * @param n
     * @return
     */
    public int leastMinutes(int n) {
        int mins = 1;
        int total = 1;
        while (total < n) {
            mins++;
            total <<= 1;
        }
        return mins;
    }

    /**
     * 直接取对数
     *
     * @param n
     * @return
     */
    public int leastMinutes2(int n) {
        if (n == 1) {
            return 1;
        }
        return (int) (Math.log(n - 1) / Math.log(2) + 1);
    }

    public static void main(String[] args) {
        List<?> l = new ArrayList<>();
        Object o = l.get(0);
        if(false||true&&false){
            System.out.println("True");
        }
    }
}
