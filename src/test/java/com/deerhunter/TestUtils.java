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

    public static void equalsIgnoreOrder(List<?> expect, List<?> actual) {
        if (expect.size() != actual.size()) {
            throw new AssertionFailedError(String.format("Size dose not match, expect: %d, actual: %d\n expect: %s\n actual: %s",
                    expect.size(), actual.size(), expect, actual));
        }
        for (Object o1 : expect) {
            boolean findMatch = false;
            for (Object o2 : actual) {
                if (Objects.equals(o1, o2)) {
                    findMatch = true;
                    break;
                }
            }
            if (!findMatch) {
                throw new AssertionFailedError(String.format("No match found in list 2 for %s in list 1", o1));
            }
        }
    }

    public static void assertListEquals(List<?> expect, List<?> actual) {
        if (expect.size() != actual.size()) {
            throw new AssertionFailedError(String.format("Wrong size: expect %d, actual %d", expect.size(), actual.size()));
        }
        for (int i = 0; i < expect.size(); i++) {
            if (!Objects.equals(expect.get(i), actual.get(i))) {
                throw new AssertionFailedError(String.format("Expected %s, actually %s at position %d", expect.get(i), actual.get(i), i));
            }
        }
    }

}
