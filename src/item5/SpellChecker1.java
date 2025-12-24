package item5;

import java.util.Objects;

public class SpellChecker1 {
    private final Lexicon dictionary;

    public SpellChecker1(Lexicon dictionary) {
        this.dictionary = Objects.requireNonNull(dictionary);
    }
}
