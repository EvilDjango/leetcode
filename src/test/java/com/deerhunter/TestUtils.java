package com.deerhunter;

import junit.framework.AssertionFailedError;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * 测试用的工具类
 * <p>
 * Copyright (c) 20120 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2020/9/12 11:10
 */
public class TestUtils {
    public static void checkTwoDimensionalListEqual(List<List<Integer>> expected, List<List<Integer>> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionFailedError(String.format("wrong array size, expected: %d, actual: %d", expected.size(), actual.size()));
        }
        for (int i = 0; i < expected.size(); i++) {
            List<Integer> expectedSubList = expected.get(i);
            List<Integer> actualSubList = actual.get(i);
            if (expectedSubList.size() != actualSubList.size()) {
                throw new AssertionFailedError(String.format("wrong array size, expected: %d, actual: %d", expectedSubList.size(), actualSubList.size()));
            }
            for (int j = 0; j < expectedSubList.size(); j++) {
                assertEquals(expectedSubList.get(j), actualSubList.get(j));
            }
        }
    }
}
