package item4;

public class UtilityClass {
    // 하위 클래스가 상위 클래스의 생정자에 접근할 길이 막혀버린다.
    private UtilityClass() {
        throw new AssertionError();
    }
}
