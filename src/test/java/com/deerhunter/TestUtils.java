package com.deerhunter;

import junit.framework.AssertionFailedError;

import java.util.List;
import java.util.Objects;

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
    public static <T> void checkTwoDimensionalListEqual(List<List<T>> expected, List<List<T>> actual) {
        if (expected.size() != actual.size()) {
            throw new AssertionFailedError(String.format("wrong array size, expected: %d, actual: %d", expected.size(), actual.size()));
        }
        for (List<T> expectedSub : expected) {
            boolean foundEqual = false;
            for (List<T> actualSub : actual) {
                if (expectedSub.equals(actualSub)) {
                    foundEqual = true;
                    break;
                }
            }
            if (!foundEqual) {
                throw new AssertionFailedError("No matched sublist for expected: " + expectedSub);
            }
        }
    }

}
