package domain.word;

import java.util.List;

import dao.word.Lang;

public interface Word {
    
    void setWord(String word);

    String getWord();
    
    void setLang(Lang lang);
    
    Lang getLang();
    
    void setTransalte(Translation Translation);

    void setTranslations(List<Translation> Translations);

    List<Translation> getTranslations();
}
