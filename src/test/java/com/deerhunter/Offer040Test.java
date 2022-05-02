package com.deerhunter;

import org.junit.jupiter.api.Test;

import java.util.function.BiFunction;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/2 下午5:39
 */
class Offer040Test {
    private Offer040 instance=new Offer040();
    void test(BiFunction<int[],Integer,int[]> function){
        TestUtils.assertEqualsIgnoreOrder(new int[]{1,2},function.apply(new int[]{1,2,3},2));
    }

    @Test
    void getLeastNumbers2() {
        test(instance::getLeastNumbers2);
    }
}
