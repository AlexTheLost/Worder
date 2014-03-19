package domain.word;

import java.util.LinkedList;
import java.util.List;

import dao.word.Lang;

public class WordImpl implements Word {
    private String word;
    private Lang lang;
    private List<Translation> translations;

    public WordImpl(String word, Lang lang) {
	this.word = word;
	this.lang = lang;
	translations = new LinkedList<Translation>();
    }

    @Override
    public void setWord(String word) {
	this.word = word;

    }

    @Override
    public String getWord() {
	return word;
    }

    @Override
    public void setLang(Lang lang) {
	this.lang = lang;

    }

    @Override
    public Lang getLang() {
	return lang;
    }

    @Override
    public void setTransalte(Translation translation) {
	translations.add(translation);
    }

    @Override
    public void setTranslations(List<Translation> translations) {
	this.translations.addAll(translations);
    }

    @Override
    public List<Translation> getTranslations() {
	return translations;
    }

}
