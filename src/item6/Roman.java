package item6;

import java.util.regex.Pattern;

public class Roman {
    // 좋은 예
    private static final Pattern ROMAN = Pattern.compile(
            "^(?=.)M*(C[MD]|D?C{0,3})(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$"
    );

    static boolean isRomanNumeral2(String s) {
        return ROMAN.matcher(s).matches();
    }

    // 나쁜 예
    static boolean isRomanNumeral1(String s) {
        return s.matches("^(?=.)M*(C[MD]|D?C{0,3})"
                + "(X[CL]|L?X{0,3})(I[XV]|V?I{0,3})$");
    }
}
