package com.deerhunter.topic;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.function.IntConsumer;

/**
 * 1195. 交替打印字符串
 * <p>
 * https://leetcode.cn/problems/fizz-buzz-multithreaded/description/
 * <p>
 * Copyright (c) @2024 deerhunter0837@gmail.com All Rights Reserved.
 *
 * @author xuejunc
 * @createTime 2024/7/27 下午10:54
 */
public class Topic1195 {
    static class FizzBuzz {
        private int n;
        private final AtomicInteger number = new AtomicInteger(1);

        public FizzBuzz(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            Printer printer = Printer.FIZZ;
            while (number.get() <= n) {
                if (getPrinter(number.get()) == printer) {
                    printFizz.run();
                    number.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            while (number.get() <= n) {
                if (getPrinter(number.get()) == Printer.BUZZ) {
                    printBuzz.run();
                    number.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            while (number.get() <= n) {
                if (getPrinter(number.get()) == Printer.FIZZ_BUZZ) {
                    printFizzBuzz.run();
                    number.incrementAndGet();
                } else {
                    Thread.yield();
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            while (number.get() <= n) {
                if (getPrinter(number.get()) == Printer.NORMAL) {
                    printNumber.accept(number.get());
                    number.getAndIncrement();
                } else {
                    Thread.yield();
                }
            }
        }

        private Printer getPrinter(int i) {
            if (number.get() == n + 1) {
                return null;
            }
            boolean triple = i % 3 == 0;
            boolean fiveTimes = i % 5 == 0;
            if (triple && fiveTimes) {
                return Printer.FIZZ_BUZZ;
            }
            if (triple) {
                return Printer.FIZZ;
            }
            if (fiveTimes) {
                return Printer.BUZZ;
            }
            return Printer.NORMAL;
        }

        private enum Printer {
            NORMAL, FIZZ, BUZZ, FIZZ_BUZZ
        }
    }

    static class FizzBuzz2 {
        private int n;
        private int current;

        public FizzBuzz2(int n) {
            this.n = n;
            this.current = 1;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            doPrint(printFizz, Printer.FIZZ);
        }

        private void doPrint(Runnable print, Printer printer) throws InterruptedException {
            synchronized (this) {
                for (int i = 1; i <= n; i++) {
                    if (getPrinter(i) == printer) {
                        print.run();
                        current++;
                        notifyAll();
                    } else {
                        while (current == i) {
                            wait();
                        }
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            doPrint(printBuzz, Printer.BUZZ);
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            doPrint(printFizzBuzz, Printer.FIZZ_BUZZ);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                for (int i = 1; i <= n; i++) {
                    if (getPrinter(i) == Printer.NORMAL) {
                        printNumber.accept(i);
                        current++;
                        notifyAll();
                    } else {
                        while (current == i) {
                            wait();
                        }
                    }
                }
            }
        }

        private static Printer getPrinter(int i) {
            boolean triple = i % 3 == 0;
            boolean fiveTimes = i % 5 == 0;
            if (triple && fiveTimes) {
                return Printer.FIZZ_BUZZ;
            }
            if (triple) {
                return Printer.FIZZ;
            }
            if (fiveTimes) {
                return Printer.BUZZ;
            }
            return Printer.NORMAL;
        }

        private enum Printer {
            NORMAL, FIZZ, BUZZ, FIZZ_BUZZ
        }
    }

    static class FizzBuzz3 {
        private int n;
        private final AtomicInteger number = new AtomicInteger(1);

        public FizzBuzz3(int n) {
            this.n = n;
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            synchronized (this) {
                while (number.get() <= n) {
                    if (getPrinter(number.get()) == Printer.FIZZ) {
                        printFizz.run();
                        number.incrementAndGet();
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            synchronized (this) {
                while (number.get() <= n) {
                    if (getPrinter(number.get()) == Printer.BUZZ) {
                        printBuzz.run();
                        number.incrementAndGet();
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            synchronized (this) {
                while (number.get() <= n) {
                    if (getPrinter(number.get()) == Printer.FIZZ_BUZZ) {
                        printFizzBuzz.run();
                        number.incrementAndGet();
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            synchronized (this) {
                while (number.get() <= n) {
                    if (getPrinter(number.get()) == Printer.NORMAL) {
                        printNumber.accept(number.get());
                        number.getAndIncrement();
                        notifyAll();
                    } else {
                        wait();
                    }
                }
            }
        }

        private Printer getPrinter(int i) {
            boolean triple = i % 3 == 0;
            boolean fiveTimes = i % 5 == 0;
            if (triple && fiveTimes) {
                return Printer.FIZZ_BUZZ;
            }
            if (triple) {
                return Printer.FIZZ;
            }
            if (fiveTimes) {
                return Printer.BUZZ;
            }
            return Printer.NORMAL;
        }

        private enum Printer {
            NORMAL, FIZZ, BUZZ, FIZZ_BUZZ
        }
    }

    static class FizzBuzz4 {
        private int n;
        private AtomicInteger current;

        public FizzBuzz4(int n) {
            this.n = n;
            this.current = new AtomicInteger(1);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            doPrint(printFizz, Printer.FIZZ);
        }

        private void doPrint(Runnable print, Printer printer) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (getPrinter(i) == printer) {
                    print.run();
                    current.getAndIncrement();
                } else {
                    while (current.get() == i) {
                        Thread.yield();
                    }
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            doPrint(printBuzz, Printer.BUZZ);
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            doPrint(printFizzBuzz, Printer.FIZZ_BUZZ);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (getPrinter(i) == Printer.NORMAL) {
                    printNumber.accept(i);
                    current.getAndIncrement();
                } else {
                    while (current.get() == i) {
                        Thread.yield();
                    }
                }
            }
        }

        private static Printer getPrinter(int i) {
            boolean triple = i % 3 == 0;
            boolean fiveTimes = i % 5 == 0;
            if (triple && fiveTimes) {
                return Printer.FIZZ_BUZZ;
            }
            if (triple) {
                return Printer.FIZZ;
            }
            if (fiveTimes) {
                return Printer.BUZZ;
            }
            return Printer.NORMAL;
        }

        private enum Printer {
            NORMAL, FIZZ, BUZZ, FIZZ_BUZZ
        }
    }

    static class FizzBuzz5 {
        private int n;
        private CyclicBarrier barrier;

        public FizzBuzz5(int n) {
            this.n = n;
            this.barrier = new CyclicBarrier(4);
        }

        // printFizz.run() outputs "fizz".
        public void fizz(Runnable printFizz) throws InterruptedException {
            doPrint(printFizz, Printer.FIZZ);
        }

        private void doPrint(Runnable print, Printer printer) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (getPrinter(i) == printer) {
                    print.run();
                }
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        // printBuzz.run() outputs "buzz".
        public void buzz(Runnable printBuzz) throws InterruptedException {
            doPrint(printBuzz, Printer.BUZZ);
        }

        // printFizzBuzz.run() outputs "fizzbuzz".
        public void fizzbuzz(Runnable printFizzBuzz) throws InterruptedException {
            doPrint(printFizzBuzz, Printer.FIZZ_BUZZ);
        }

        // printNumber.accept(x) outputs "x", where x is an integer.
        public void number(IntConsumer printNumber) throws InterruptedException {
            for (int i = 1; i <= n; i++) {
                if (getPrinter(i) == Printer.NORMAL) {
                    printNumber.accept(i);
                }
                try {
                    barrier.await();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
            }
        }

        private static Printer getPrinter(int i) {
            boolean triple = i % 3 == 0;
            boolean fiveTimes = i % 5 == 0;
            if (triple && fiveTimes) {
                return Printer.FIZZ_BUZZ;
            }
            if (triple) {
                return Printer.FIZZ;
            }
            if (fiveTimes) {
                return Printer.BUZZ;
            }
            return Printer.NORMAL;
        }

        private enum Printer {
            NORMAL, FIZZ, BUZZ, FIZZ_BUZZ
        }
    }

}
