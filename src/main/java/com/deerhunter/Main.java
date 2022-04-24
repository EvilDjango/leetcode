package com.deerhunter;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Scanner;
import java.util.TreeMap;

// 华为面试笔试题

//public class Main {
//    public static void main(String[] args) {
//        // 参数解析
//        String[] strs = new Scanner(System.in).nextLine().split(";");
//        double[][] ratios = new double[3][5];
//        for (int i = 0; i < 3; i++) {
//            String[] data = strs[i].split(",");
//            for (int j = 0; j < 5; j++) {
//                ratios[i][j] = Double.parseDouble(data[j]);
//            }
//        }
//        String[] vStrs = strs[3].split(",");
//        int[] variables = new int[5];
//        for (int i = 0; i < 5; i++) {
//            variables[i] = Integer.parseInt(vStrs[i]);
//        }
//        String[] vTargests = strs[4].split(",");
//        double[] targets = new double[3];
//        for (int i = 0; i < 3; i++) {
//            targets[i] = Double.parseDouble(vTargests[i]);
//        }
//
//        String[] operands = strs[5].split(",");
//
//        // 计算结果
//        boolean correct = true;
//        int maxDiff = Integer.MIN_VALUE;
//        for (int i = 0; i < 3; i++) {
//            double left = 0;
//            for (int j = 0; j < 5; j++) {
//                left += ratios[i][j] * variables[j];
//            }
//            Result res = calculate(left, targets[i], operands[i]);
//            if (!res.correct) {
//                correct = false;
//            }
//            maxDiff = Math.max(maxDiff, res.diff);
//        }
//
//        //输出结果
//        System.out.print(correct + " " + maxDiff);
//    }
//
//    /**
//     * 计算不等式是否成立，并求出差值（整数部分）
//     *
//     * @param left
//     * @param right
//     * @param operand
//     * @return
//     */
//    private static Result calculate(double left, double right, String operand) {
//        boolean correct;
//        switch (operand) {
//            case "=":
//                correct = left - right == 0;
//                break;
//            case ">":
//                correct = left > right;
//                break;
//            case "<":
//                correct = left < right;
//                break;
//            case ">=":
//                correct = left >= right;
//                break;
//            case "<=":
//                correct = left <= right;
//                break;
//            default:
//                correct = false;
//        }
//        int diff = (int) (left - right);
//        return new Result(correct, diff);
//    }
//
//
//    /**
//     * 计算结果类
//     */
//    private static class Result {
//        /**
//         * 不等式是否成立
//         */
//        boolean correct;
//        /**
//         * 左右差值
//         */
//        int diff;
//
//        public Result(boolean correct, int diff) {
//            this.correct = correct;
//            this.diff = diff;
//        }
//    }
//}

//public class Main {
//    public static void main(String[] args) {
//        // 参数解析
//        String[] strs = new Scanner(System.in).nextLine().split(",");
//        int n = strs.length;
//        if (n == 0) {
//            return;
//        }
//        int[] nums = new int[n];
//        for (int i = 0; i < n; i++) {
//            nums[i] = Integer.parseInt(strs[i]);
//        }
//
//        // 统计每个数字出现的次数
//        Map<Integer, Integer> counts = new HashMap<>();
//        for (int num : nums) {
//            counts.merge(num, 1, Integer::sum);
//        }
//
//        // 利用TreeMap来进行排序
//        TreeMap<Key, Integer> treeMap = new TreeMap<>((o1, o2) -> o1.count != o2.count ? o2.count - o1.count : o1.index - o2.index);
//        for (int i = 0; i < n; i++) {
//            if (!counts.containsKey(nums[i])) {
//                continue;
//            }
//            Key key = new Key(counts.get(nums[i]), i);
//            treeMap.put(key, nums[i]);
//            counts.remove(nums[i]);
//        }
//
//        // 输出结果
//        StringBuilder sb = new StringBuilder();
//        for (Integer i : treeMap.values()) {
//            sb.append(i);
//            sb.append(",");
//        }
//        sb.deleteCharAt(sb.length() - 1);
//        System.out.print(sb.toString());
//    }
//
//    /**
//     * 用于TreeMap的键。
//     */
//    private static class Key {
//        /**
//         * 数字出现次数
//         */
//        int count;
//        /**
//         * 数字首次出现的位置
//         */
//        int index;
//
//        public Key(int count, int index) {
//            this.count = count;
//            this.index = index;
//        }
//
//        @Override
//        public boolean equals(Object o) {
//            if (this == o) return true;
//            if (o == null || getClass() != o.getClass()) return false;
//            Key key = (Key) o;
//            return count == key.count &&
//                    index == key.index;
//        }
//
//        @Override
//        public int hashCode() {
//            return Objects.hash(count, index);
//        }
//    }
//}

public class Main {
    public static void main(String[] args) {
        // 参数解析和预处理
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        int m = scanner.nextInt();
        // dishes[i]表示第i秒可以吃的
        boolean[] dishes = new boolean[2000];
        while (scanner.hasNextInt()) {
            dishes[scanner.nextInt() + scanner.nextInt()] = true;
        }

        // 利用动态规划解题
        // dp[i]表示吃到i最多可以吃的菜的数量
        int[] dp = new int[2000];
        for (int i = 0; i < 1998; i++) {
            if (!dishes[i]) {
                continue;
            }
            int max = 0;
            for (int j = 0; j <= i - m; j++) {
                max = Math.max(dp[j], max);
            }
            dp[i] = max + 1;
        }
        int max = 0;
        for (int i : dp) {
            max = Math.max(i, max);
        }

        //输出结果
        System.out.print(max);
    }
}
