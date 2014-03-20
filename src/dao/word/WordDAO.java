package dao.word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import db.connecter.Connector;
import domain.word.Word;

public class WordDAO {

    public void create(String word, float complexity) throws SQLException {
	PreparedStatement ps = null;
	String query = "INSERT INTO words (word, complexity) VALUES (?, ?)";
	try {
	    ps = Connector.getConnection().prepareStatement(query);
	    ps.setString(1, word);
	    ps.setFloat(2, complexity);
	    ps.executeUpdate();
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    if (ps != null)
		ps.close();
	}
    }

    public List<Word> readAll() throws SQLException {
	Statement stmt = Connector.getConnection().createStatement();
	ResultSet rs = null;
	try {
	    rs = stmt.executeQuery("SELECT * FROM words");
	    return assembleWords(rs);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	    rs.close();
	}
    }

    private List<Word> assembleWords(ResultSet rs) throws SQLException {
	List<Word> words = new LinkedList<>();
	Word word;
	while (rs.next()) {
	    word = new Word(rs.getString("word"), rs.getFloat("complexity"));
	    word.setTranslations(getTranslation(rs.getString("word")));
	    words.add(word);
	}
	return words;
    }

    private List<String> getTranslation(String word) throws SQLException {
	String select = "SELECT translation FROM translations WHERE id_translation IN "
		+ "(SELECT translation_id FROM words_translations wt INNER JOIN words w "
		+ "ON wt.word_id = w.id_word WHERE word = '" + word + "')";
	Statement stmt = Connector.getConnection().createStatement();
	ResultSet rs = null;
	try {
	    rs = stmt.executeQuery(select);
	    return asembleTranslations(rs);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	    rs.close();
	}
    }

    private List<String> asembleTranslations(ResultSet rs) throws SQLException {
	List<String> translations = new LinkedList<>();
	while (rs.next()) {
	    translations.add(rs.getString("translation"));
	}
	return translations;
    }

    public void addTranslation(String word, String translation)
	    throws SQLException {
	createTranslation(translation);
	String insert = "INSERT INTO words_translations (word_id, translation_id) VALUES ("
		+ "(SELECT id_word FROM words WHERE word='"
		+ word
		+ "'),"
		+ "(SELECT id_translation FROM translations WHERE translation='"
		+ translation + "'))";
	Statement stmt = Connector.getConnection().createStatement();
	try {
	    stmt.executeUpdate(insert);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	}
    }

    private void createTranslation(String translation) throws SQLException {
	String insert = "INSERT INTO translations (translation) VALUES ('"
		+ translation + "')";
	Statement stmt = Connector.getConnection().createStatement();
	try {
	    stmt.executeUpdate(insert);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	}
    }

    public void update(Word Word) throws SQLException {
    }

    public void delete(Word word) throws SQLException {
    }

    public static void main(String[] args) {
	WordDAO wordDAO = new WordDAO();
	try {
	    // wordDAO.addTranslation("Test1", "тест1");
	    wordDAO.create("Test2", 1);
	    // for (Word _w : wordDAO.readAll()) {
	    // System.out.println(_w.getWord());
	    // System.out.println(_w.getComplexity());
	    // System.out.println(_w.getStingComplexity());
	    // }
	} catch (SQLException e) {
	}
    }

}
