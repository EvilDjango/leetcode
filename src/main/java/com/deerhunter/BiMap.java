package com.deerhunter;

import java.util.HashMap;
import java.util.Map;

/**
 * Oops, forgot to write comments. Good luck, bro.
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/4/19 下午9:48
 */
public class BiMap<K, V> {
    private final Map<K, V> keyValue = new HashMap<>();
    private final Map<V, K> valueKey = new HashMap<>();

    public boolean put(K key, V value) {
        boolean keyExists = keyValue.containsKey(key);
        boolean valueExists = valueKey.containsKey(value);
        if (keyExists && valueExists) {
            return keyValue.get(key) == value;
        }
        if (keyExists) {
            valueKey.remove(keyValue.get(key));
        }
        if (valueExists) {
            keyValue.remove(valueKey.get(value));
        }
        keyValue.put(key, value);
        valueKey.put(value, key);
        return true;
    }

    public V getByKey(K key) {
        return keyValue.get(key);
    }

    public K getByValue(V value) {
        return valueKey.get(value);
    }
}
