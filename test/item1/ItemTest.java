package item1;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.Instant;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;

class ItemTest {
    @Test
    @DisplayName("생성자 대신 정적 팩터리 메서드를 고려하라.")
    void test1() {
        Item item1 = new Item(); // X
        Item item2 = Item.create();

        assertEquals(item2.getClass(), item1.getClass());
    }

    @Test
    @DisplayName("이름을 가질 수 있다. -> 의도표현이 가능하다.")
    void test2() {
        BigInteger prime = BigInteger.probablePrime(512, new Random()); // 512비트짜리 "소수일 가능성이 매우 높은" 수

        int a = 3;
    }

    @Test
    @DisplayName("호출될 때마다 인스턴스를 새로 생성하지는 않아도 된다.")
    void test3() {
        // 플라위웨이트 패턴도 이와 비슷한 기법이다.
        Boolean trust = Boolean.valueOf(true);

        assertTrue(trust);
    }

    @Test
    @DisplayName("반환 타입의 화위 타입 객체를 반환할 수 있다.")
    void test4() {
        // 인터페이스 기반 프로그램을 만들 수 있다.
        IItem item = Item.createInterface();

        assertInstanceOf(Item.class, item);
    }

    @Test
    @DisplayName("입력 매개변수에 따라 매번 다른 클래스의 객체를 반환할 수 있다.")
    void test5() {
        // EnumSet는 public 생성자 없이 오직 정적팩터리만 제공하는데,
        // 원소가 64개 이하면 long 변수 하나로 관리하는 RegularEnumSet
        // 원소가 65개 이상이면 long 배열로 관리하는 JumboEnumSet
        // 64개 enum (RegularEnumSet)
        enum Feature64 {
            F00, F01, F02, F03, F04, F05, F06, F07,
            F08, F09, F10, F11, F12, F13, F14, F15,
            F16, F17, F18, F19, F20, F21, F22, F23,
            F24, F25, F26, F27, F28, F29, F30, F31,
            F32, F33, F34, F35, F36, F37, F38, F39,
            F40, F41, F42, F43, F44, F45, F46, F47,
            F48, F49, F50, F51, F52, F53, F54, F55,
            F56, F57, F58, F59, F60, F61, F62, F63
        }

        // 65개 enum (JumboEnumSet)
        enum Feature65 {
            F00, F01, F02, F03, F04, F05, F06, F07,
            F08, F09, F10, F11, F12, F13, F14, F15,
            F16, F17, F18, F19, F20, F21, F22, F23,
            F24, F25, F26, F27, F28, F29, F30, F31,
            F32, F33, F34, F35, F36, F37, F38, F39,
            F40, F41, F42, F43, F44, F45, F46, F47,
            F48, F49, F50, F51, F52, F53, F54, F55,
            F56, F57, F58, F59, F60, F61, F62, F63,
            F64
        }

        EnumSet<Feature64> set1 = EnumSet.allOf(Feature64.class);
        EnumSet<Feature65> set2 = EnumSet.allOf(Feature65.class);
        String c1 = set1.getClass().getName();
        String c2 = set2.getClass().getName();

        assertTrue(c1.contains("RegularEnumSet"));
        assertTrue(c2.contains("JumboEnumSet"));
    }

    @Test
    @DisplayName("정적 팩터리 메서드를 작성하는 시점에는 반환할 객체의 클래스가 존재하지 않아도 된다.")
    void test6() {
        List<Integer> list = Collections.emptyList(); // 구현 클래스(EmptyList)는 숨겨짐
        assertTrue(list.isEmpty());
    }


    @Test
    @DisplayName("잘 알려진 정적 팩터리 메서드 명명 규약")
    void test7() throws IOException {
        // from: 매개변수 하나를 받아 해당 타입의 인스턴스를 반환하는 '변환(conversion)' 메서드
        Date d = Date.from(Instant.now());
        assertNotNull(d);

        // of: 여러 매개변수를 받아 적합한 타입의 인스턴스를 반환하는 '집계(aggregation)' 메서드
        enum Rank { JACK, QUEEN, KING }
        EnumSet<Rank> faceCards = EnumSet.of(Rank.JACK, Rank.QUEEN, Rank.KING);
        assertEquals(3, faceCards.size());

        // valueOf: from/of의 더 자세한 버전(주로 "문자열/기본형 -> 해당 타입" 변환에 많이 쓰임)
        Integer n = Integer.valueOf("123");
        assertEquals(123, n);

        // instance / getInstance: (매개변수가 있다면) 매개변수로 기술된 인스턴스를 반환하지만, 같은 인스턴스임을 보장하지는 않음
        StackWalker sw = StackWalker.getInstance();
        assertNotNull(sw);

        // create / newInstance: 매번 새로운 인스턴스를 생성해 반환함을 암시(보장)
        Object newArray = Array.newInstance(String.class, 10);
        assertTrue(newArray.getClass().isArray());
        assertEquals(10, Array.getLength(newArray));

        // getType: getInstance와 같으나, 팩터리 메서드를 '반환 타입과 다른 클래스'에 정의할 때
        Path path = Path.of(".");
        FileStore fs = Files.getFileStore(path);
        assertNotNull(fs);

        // newType: newInstance와 같으나, 반환 타입과 다른 클래스에 정의할 때
        BufferedReader br = Files.newBufferedReader(path.resolve("build.gradle")); // 예시: 존재하는 파일로 바꿔서 사용
        assertNotNull(br);
        br.close();

        // type: getType/newType의 간결한 버전
        // Collections.list(Enumeration) -> List 로 변환
        Vector<Integer> v = new Vector<>();
        v.add(1); v.add(2); v.add(3);
        List<Integer> litany = Collections.list(v.elements());
        assertEquals(List.of(1, 2, 3), litany);
    }
}