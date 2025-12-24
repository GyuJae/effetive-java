package item6;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("item6: 불필요한 객체 생성을 피하라")
class Item6Test {
    @Test
    @DisplayName("new String 하지 마라")
    void test1() {
        // 문자열 리터얼은 JVM이 intern해서 재사용하는데, new String은 굳이 새 객체를 만듦.
        String s1 = "hello";
        String s2 = "hello";

        assertEquals(s1, s2);
    }

    @Test
    @DisplayName("비싼 객체는 캐싱해서 재사용하라 (Pattern이 대표적 예시)")
    void test2() {
        int warmup = 20_000;
        int iters = 200_000;

        String[] inputs = {
                "MCMLXXVI", "MCMXCIV", "MMXXV", "XLII", "IX", "I", "V", "X",
                "not-roman", "ABC", "MMMMMMMMMMMM", "VX", "IC"
        };

        for (int i = 0; i < warmup; i++) {
            Roman.isRomanNumeral1(inputs[i % inputs.length]);
            Roman.isRomanNumeral2(inputs[i % inputs.length]);
        }

        long t1 = this.timeNanos(() -> {
            for (int i = 0; i < iters; i++) {
                Roman.isRomanNumeral1(inputs[i % inputs.length]);
            }
        });

        long t2 = this.timeNanos(() -> {
            for (int i = 0; i < iters; i++) {
                Roman.isRomanNumeral2(inputs[i % inputs.length]);
            }
        });

        System.out.printf("isRomanNumeral1: %d ms%n", TimeUnit.NANOSECONDS.toMillis(t1));
        System.out.printf("isRomanNumeral2: %d ms%n", TimeUnit.NANOSECONDS.toMillis(t2));

        assertTrue(t2 < t1);
    }

    @Test
    @DisplayName("오토박싱을 피하라 (Long vs long)")
    void test3() {
        int warmup = 5;
        int iters = 5;

        for (int i = 0; i < warmup; i++) {
            this.sumWithBoxing(1_000_000);
            this.sumWithoutBoxing(1_000_000);
        }

        long t1 = timeNanos(() -> {
            for (int i = 0; i < iters; i++) {
                long r = this.sumWithBoxing(5_000_000);
                assertTrue(r > 0);
            }
        });

        long t2 = timeNanos(() -> {
            for (int i = 0; i < iters; i++) {
                long r = this.sumWithoutBoxing(5_000_000);
                assertTrue(r > 0);
            }
        });

        System.out.printf("sumWithBoxing: %d ms%n", TimeUnit.NANOSECONDS.toMillis(t1));
        System.out.printf("sumWithoutBoxing: %d ms%n", TimeUnit.NANOSECONDS.toMillis(t2));

        assertTrue(t2 < t1);
    }

    private long timeNanos(Runnable r) {
        long start = System.nanoTime();
        r.run();
        return System.nanoTime() - start;
    }

    private long sumWithBoxing(long n) {
        Long sum = 0L;
        for (long i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    private long sumWithoutBoxing(long n) {
        long sum = 0L;
        for (long i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }
}
