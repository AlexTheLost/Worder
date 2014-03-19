package domain.word;

import java.util.Locale;

public interface Translation {
    void setWord(String word);

    String getWord();

    void setLocale(Locale locale);

    Locale getLocale();
}
