package item3;

/**
 * private 생성자와 public static 메소드를 사용하는 방법
 * 장점.
 * 1. 지연 초기화: 실제로 필요할 때까지 객체 생성 안 함(무거운 객채일수록 이득)
 * 2. 구현이 매우 간단
 * 3. 정적 팩터리 메서드라 확장 여지가 있음. (item1 참고)
 * <p>
 * 단점
 * 1. 스레드 안전하지 않음: 두 스레드가 동시에 instance == null을 통과하면 인스턴스가 2개 생성될 수 있음.
 * 2. 리플렉션으로 깨질 수 있음: setAccessible(true)로 private 생성자 호출 가능 -> 인스턴스 여러 개 생성 가능
 * 3. 직렬화(Serializable)로 꺠질 수 있음.
 */
public class Settings1 {
    private static Settings1 instance;

    private Settings1() {
    }

    public static Settings1 getInstance() {
        if (instance == null) {
            instance = new Settings1();
        }

        return instance;
    }
}
