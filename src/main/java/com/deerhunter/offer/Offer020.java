package com.deerhunter.offer;

import java.util.HashMap;
import java.util.Map;

/**
 * 剑指 Offer 20. 表示数值的字符串
 * 请实现一个函数用来判断字符串是否表示数值（包括整数和小数）。
 * <p>
 * 数值（按顺序）可以分成以下几个部分：
 * <p>
 * 若干空格
 * 一个 小数 或者 整数
 * （可选）一个 'e' 或 'E' ，后面跟着一个 整数
 * 若干空格
 * 小数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 下述格式之一：
 * 至少一位数字，后面跟着一个点 '.'
 * 至少一位数字，后面跟着一个点 '.' ，后面再跟着至少一位数字
 * 一个点 '.' ，后面跟着至少一位数字
 * 整数（按顺序）可以分成以下几个部分：
 * <p>
 * （可选）一个符号字符（'+' 或 '-'）
 * 至少一位数字
 * 部分数值列举如下：
 * <p>
 * ["+100", "5e2", "-123", "3.1416", "-1E-16", "0123"]
 * 部分非数值列举如下：
 * <p>
 * ["12e", "1a3.14", "1.2.3", "+-5", "12e+5.4"]
 * <p>
 * <p>
 * 示例 1：
 * <p>
 * 输入：s = "0"
 * 输出：true
 * 示例 2：
 * <p>
 * 输入：s = "e"
 * 输出：false
 * 示例 3：
 * <p>
 * 输入：s = "."
 * 输出：false
 * 示例 4：
 * <p>
 * 输入：s = "    .1  "
 * 输出：true
 * <p>
 * <p>
 * 提示：
 * <p>
 * 1 <= s.length <= 20
 * s 仅含英文字母（大写和小写），数字（0-9），加号 '+' ，减号 '-' ，空格 ' ' 或者点 '.' 。
 * 通过次数104,859提交次数417,488
 * <p>
 * Copyright (c) @2022 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2022/5/13 下午12:36
 */
public class Offer020 {
    /**
     * 比较粗放的优先状态自动机
     *
     * @param s
     * @return
     */
    public boolean isNumber(String s) {
        // stage表示当前的阶段，取值范围【0，3】，分别表示开头空白，e左边部分，e右边部分,剩余空白
        int stage = 0, start = -1, pivot = -1, start2 = -1, dots = 0;
        int len = s.length();
        for (int i = 0; i < len; i++) {
            char c = s.charAt(i);
            if (stage == 0) {
                if (c != ' ') {
                    stage = 1;
                    start = i;
                    i--;
                }
            } else if (stage == 1) {
                if (c == '+' || c == '-') {
                    if (i != start) {
                        return false;
                    }
                    start = i + 1;
                } else if (c == '.') {
                    dots++;
                    if (dots > 1) {
                        return false;
                    }
                } else if (c == 'e' || c == 'E') {
                    pivot = i;
                    stage = 2;
                    start2 = i + 1;
                    // stage1部分没有数字是非法的
                    if (i - start - dots < 1) {
                        return false;
                    }
                } else if (c == ' ') {
                    stage = 3;
                    // stage1部分没有数字是非法的
                    if (i - start - dots < 1) {
                        return false;
                    }
                } else if (c < '0' || c > '9') {
                    return false;
                }
            } else if (stage == 2) {
                if (c == '+' || c == '-') {
                    if (i != pivot + 1) {
                        return false;
                    }
                    start2 = i + 1;
                } else if (c == ' ') {
                    stage = 3;
                    // e后面没有数字，非法
                    if (i - pivot - 1 < 1) {
                        return false;
                    }
                } else if (c < '0' || c > '9') {
                    return false;
                }
            } else {
                if (c != ' ') {
                    return false;
                }
            }
        }
        if (stage == 0) {
            return false;
        }
        if (stage == 1) {
            return len - start - dots > 0;
        }
        if (stage == 2) {
            return len - start2 > 0;
        }
        return true;
    }

    /**
     * 简易的做法，但是效率很低
     *
     * @param s
     * @return
     */
    public boolean isNumber2(String s) {
        s = s.trim();
        String[] parts = s.split("[eE]", -1);
        if (parts.length > 2) {
            return false;
        }
        int dots = 0;
        int sign = 0;
        for (int i = 0; i < parts[0].length(); i++) {
            char c = parts[0].charAt(i);
            if (c == '+' || c == '-') {
                if (i != 0) {
                    return false;
                }
                sign = 1;
            } else if (c == '.') {
                if (++dots > 1) {
                    return false;
                }
            } else if (c < '0' || c > '9') {
                return false;
            }
        }
        if (parts[0].length() - dots - sign == 0) {
            return false;
        }
        if (parts.length > 1) {
            sign = 0;
            for (int i = 0; i < parts[1].length(); i++) {
                char c = parts[1].charAt(i);
                if (c == '+' || c == '-') {
                    if (i != 0) {
                        return false;
                    }
                    sign = 1;
                } else if (c < '0' || c > '9') {
                    return false;
                }
            }
            if (parts[1].length() - sign == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 有限状态自动机，参考官方题解
     *
     * @param s
     * @return
     */
    public boolean isNumber3(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();

        Map<CharType, State> initial = new HashMap<CharType, State>() {{
            put(CharType.SPACE, State.INITIAL);
            put(CharType.SIGN, State.SIGN);
            put(CharType.NUMBER, State.INTEGER);
            put(CharType.POINT, State.POINT);
        }};
        transfer.put(State.INITIAL, initial);

        Map<CharType, State> baseSign = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.INTEGER);
            put(CharType.POINT, State.POINT);
        }};
        transfer.put(State.SIGN, baseSign);

        Map<CharType, State> point = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.FRACTION);
        }};
        transfer.put(State.POINT, point);

        Map<CharType, State> baseInteger = new HashMap<CharType, State>() {{
            put(CharType.POINT, State.FRACTION);
            put(CharType.EXPONENT, State.EXP);
            put(CharType.SPACE, State.END);
            put(CharType.NUMBER, State.INTEGER);
        }};
        transfer.put(State.INTEGER, baseInteger);

        Map<CharType, State> pointWithLeadingInteger = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.FRACTION);
            put(CharType.EXPONENT, State.EXP);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.POINT_WITH_LEADING_INTEGER, pointWithLeadingInteger);

        Map<CharType, State> fraction = new HashMap<CharType, State>() {{
            put(CharType.SPACE, State.END);
            put(CharType.EXPONENT, State.EXP);
            put(CharType.NUMBER, State.FRACTION);
        }};
        transfer.put(State.FRACTION, fraction);

        Map<CharType, State> exponent = new HashMap<CharType, State>() {{
            put(CharType.SIGN, State.EXP_SIGN);
            put(CharType.NUMBER, State.EXP_INTEGER);
        }};
        transfer.put(State.EXP, exponent);

        Map<CharType, State> exponentSign = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.EXP_INTEGER);
        }};
        transfer.put(State.EXP_SIGN, exponentSign);

        Map<CharType, State> exponentInteger = new HashMap<CharType, State>() {{
            put(CharType.NUMBER, State.EXP_INTEGER);
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.EXP_INTEGER, exponentInteger);

        Map<CharType, State> tail = new HashMap<CharType, State>() {{
            put(CharType.SPACE, State.END);
        }};
        transfer.put(State.END, tail);

        State state = State.INITIAL;
        for (char c : s.toCharArray()) {
            CharType type = charType(c);
            if (transfer.get(state).containsKey(type)) {
                state = transfer.get(state).get(type);
            } else {
                return false;
            }
        }
        return state == State.INTEGER || state == State.POINT_WITH_LEADING_INTEGER || state == State.FRACTION || state == State.EXP_INTEGER || state == State.END;
    }

    private CharType charType(char c) {
        if (c == ' ') {
            return CharType.SPACE;
        } else if (c == '+' || c == '-') {
            return CharType.SIGN;
        } else if (c >= '0' && c <= '9') {
            return CharType.NUMBER;
        } else if (c == '.') {
            return CharType.POINT;
        } else if (c == 'e' || c == 'E') {
            return CharType.EXPONENT;
        } else {
            return CharType.ILLEGAL;
        }
    }

    private enum CharType {
        SPACE,
        SIGN,
        NUMBER,
        POINT,
        EXPONENT,
        ILLEGAL
    }

    private enum State {
        INITIAL,
        SIGN,
        INTEGER,
        POINT_WITH_LEADING_INTEGER,
        POINT,
        FRACTION,
        EXP,
        EXP_SIGN,
        EXP_INTEGER,
        END
    }

    /**
     * 简洁完备的有限状态自动机
     *
     * @param s
     * @return
     */
    public boolean isNumber4(String s) {
        State state = State.INITIAL;
        for (char c : s.toCharArray()) {
            CharType type = charType(c);
            if (type == CharType.ILLEGAL) {
                return false;
            }
            if (state == State.INITIAL) {
                switch (type) {
                    case SPACE:
                        break;
                    case SIGN:
                        state = State.SIGN;
                        break;
                    case NUMBER:
                        state = State.INTEGER;
                        break;
                    case POINT:
                        state = State.POINT;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.SIGN) {
                switch (type) {
                    case NUMBER:
                        state = State.INTEGER;
                        break;
                    case POINT:
                        state = State.POINT;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.POINT) {
                if (type == CharType.NUMBER) {
                    state = State.FRACTION;
                } else {
                    return false;
                }
            } else if (state == State.INTEGER) {
                switch (type) {
                    case NUMBER:
                        break;
                    case SPACE:
                        state = State.END;
                        break;
                    case EXPONENT:
                        state = State.EXP;
                        break;
                    case POINT:
                        state = State.POINT_WITH_LEADING_INTEGER;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.POINT_WITH_LEADING_INTEGER) {
                switch (type) {
                    case SPACE:
                        state = State.END;
                        break;
                    case EXPONENT:
                        state = State.EXP;
                        break;
                    case NUMBER:
                        state = State.FRACTION;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.FRACTION) {
                switch (type) {
                    case NUMBER:
                        break;
                    case SPACE:
                        state = State.END;
                        break;
                    case EXPONENT:
                        state = State.EXP;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.EXP) {
                switch (type) {
                    case SIGN:
                        state = State.EXP_SIGN;
                        break;
                    case NUMBER:
                        state = State.EXP_INTEGER;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.EXP_SIGN) {
                if (type == CharType.NUMBER) {
                    state = State.EXP_INTEGER;
                } else {
                    return false;
                }
            } else if (state == State.EXP_INTEGER) {
                switch (type) {
                    case NUMBER:
                        break;
                    case SPACE:
                        state = State.END;
                        break;
                    default:
                        return false;
                }
            } else if (state == State.END) {
                if (type != CharType.SPACE) {
                    return false;
                }
            }
        }
        return state == State.INTEGER || state == State.POINT_WITH_LEADING_INTEGER || state == State.FRACTION || state == State.EXP_INTEGER || state == State.END;
    }
}
