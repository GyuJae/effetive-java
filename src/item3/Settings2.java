package item3;

/**
 * synchronized 사용해서 동기화 처리
 * <p>
 * 장점.
 * 1. 스레드 안전
 * 2. 구현이 단순
 * 3. 지연 초기화 유지
 * <p>
 * 단점.
 * 1. 성능 오버헤드
 * 2. 리플렉션/직렬화 문제는 그대로
 * 3. 테스트 격리 문제
 */
public class Settings2 {
    private static Settings2 instance;

    private Settings2() {
    }

    public static synchronized Settings2 getInstance() {
        if (instance == null) {
            instance = new Settings2();
        }

        return instance;
    }
}
