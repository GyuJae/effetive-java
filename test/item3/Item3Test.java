package item3;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

import static org.junit.jupiter.api.Assertions.assertNotSame;
import static org.junit.jupiter.api.Assertions.assertSame;

@DisplayName("item3: private 생성자나 열거 타입으로 싱글턴임을 보증하라")
class Item3Test {
    @Test
    @DisplayName("private 생성자와 public static 메소드를 사용하는 방법")
    void test1() {
        Settings1 s1 = Settings1.getInstance();
        Settings1 s2 = Settings1.getInstance();

        assertSame(s1, s2);
    }

    @Test
    @DisplayName("private 생성자와 public static 메소드를 사용하는 방법: 리플렉션으로 깨질 수 있음.")
    void test2() throws NoSuchMethodException, InvocationTargetException, InstantiationException, IllegalAccessException {
        Settings1 s1 = Settings1.getInstance();

        Constructor<Settings1> ctor = Settings1.class.getDeclaredConstructor();
        ctor.setAccessible(true);

        Settings1 s2 = ctor.newInstance();

        assertNotSame(s1, s2);
    }


    @Test
    @DisplayName("synchronized 사용해서 동기화 처리")
    void test3() {
        Settings2 s1 = Settings2.getInstance();
        Settings2 s2 = Settings2.getInstance();

        assertSame(s1, s2);
    }

    @Test
    @DisplayName("double checked locking")
    void test4() {
        Settings3 s1 = Settings3.getInstance();
        Settings3 s2 = Settings3.getInstance();

        assertSame(s1, s2);
    }

    @Test
    @DisplayName("static inner 클래스 홀더")
    void test5() {
        Settings4 s1 = Settings4.getInstance();
        Settings4 s2 = Settings4.getInstance();

        assertSame(s1, s2);
    }

    @Test
    @DisplayName("Enum을 사용해서 싱글톤 만들기")
    void test6() {
        Settings5 s1 = Settings5.INSTANCE;
        Settings5 s2 = Settings5.INSTANCE;

        assertSame(s1, s2);
    }
}