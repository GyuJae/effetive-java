package item3;

/**
 * static inner 클래스 홀더
 * 장점.
 * 1. 스레드 안전
 * 2. 지연 초기화
 * 3. 성능 좋음
 * 4. 구현깔끔
 * <p>
 * 단점.
 * 1. 리플랙션/직렬화로 꺠질 수 있음
 * 2. 전역상태 문제
 */
public class Settings4 {
    private Settings4() {
    }

    public static Settings4 getInstance() {
        return Settings4Holder.INSTANCE;
    }

    private static class Settings4Holder {
        private static final Settings4 INSTANCE = new Settings4();
    }

}
