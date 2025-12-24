package item3;

/**
 * double checked locking
 * 장점.
 * 1. 스레드 안전
 * 2. 성능 개선 Settings2처럼 매 호출마다 synchronized를 타지 않음.
 * 3. 지연 초기화
 * <p>
 * 단점.
 * 1. 복잡함.
 * 2. 리플렉션/직렬화 문제
 */
public class Settings3 {
    private static Settings3 instance;

    private Settings3() {
    }

    public static Settings3 getInstance() {
        if (instance == null) {
            synchronized (Settings3.class) {
                if (instance == null) {
                    instance = new Settings3();
                }
            }
        }

        return instance;
    }
}
