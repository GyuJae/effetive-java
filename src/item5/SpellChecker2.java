package item5;

import java.util.Objects;
import java.util.function.Supplier;

public class SpellChecker2 {
    private final Supplier<? extends Lexicon> dictionaryFactory;

    public SpellChecker2(Supplier<? extends Lexicon> dictionaryFactory) {
        this.dictionaryFactory = Objects.requireNonNull(dictionaryFactory);
    }

    public boolean isValid(String word) {
        Lexicon dict = this.dictionaryFactory.get();
        return true;
    }
}
