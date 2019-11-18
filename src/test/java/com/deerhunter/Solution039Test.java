package com.deerhunter;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Oops, forgot to write comment. Good luck, bro.
 * <p>
 * Copyright (c) 2019, deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @date 2019-11-18
 */
class Solution039Test {

    @Test
    void combinationSum() {
        Solution039 solution = new Solution039();
        int[] cadinates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> combinations = solution.combinationSum(cadinates, target);
        List<List<Integer>> expect = new ArrayList<>();
        expect.add(Arrays.asList(2, 2, 3));
        expect.add(Arrays.asList(7));
        assertEquals(expect, combinations);

        solution = new Solution039();
        cadinates = new int[]{2, 3, 5};
        target = 8;
        combinations = solution.combinationSum(cadinates, target);
       expect = new ArrayList<>();
        expect.add(Arrays.asList(2, 2,2,2 ));
        expect.add(Arrays.asList(2,3,3));
        expect.add(Arrays.asList(3,5));
        assertEquals(expect, combinations);

    }
}