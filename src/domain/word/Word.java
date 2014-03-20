package domain.word;

import java.util.LinkedList;
import java.util.List;

public class Word {
    private String word;
    private float complexity;
    private List<String> translations;

    public Word(String word, float complexity) {
	this.word = word;
	this.complexity = complexity;
	translations = new LinkedList<String>();
    }

    public void setWord(String w) {
	word = w;
    }

    public String getWord() {
	return word;
    }

    public void setComplexity(float c) {
	complexity = c;
    }

    public float getComplexity() {
	return complexity;
    }

    public String getStingComplexity() {
	if (complexity <= 0.33f) {
	    return "Low";
	} else if (complexity > 0.33f && complexity < 0.66f) {
	    return "Medium";
	} else if (complexity >= 0.66) {
	    return "Hard";
	}
	return "undefined";
    }

    public void setTranslations(List<String> t) {
	translations = t;
    }

    public List<String> getTranslations() {
	return translations;
    }
}
