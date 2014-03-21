package dao.word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

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
	} finally {
	    stmt.close();
	}
    }

    public void deleteTranslations(String word, String translation)
	    throws SQLException {
	String delete = "DELETE FROM words_translations "
		+ "WHERE word_id = (SELECT id_word FROM words WHERE word = '"
		+ word
		+ "') "
		+ "and translation_id = (SELECT id_translation FROM translations WHERE translation = '"
		+ translation + "')";
	Statement stmt = Connector.getConnection().createStatement();
	try {
	    stmt.executeUpdate(delete);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	}
    }

    public void deleteWord(String word) throws SQLException {
	String delete_fk = "DELETE FROM words_translations WHERE word_id = (SELECT id_word FROM words WHERE word = ?)";
	String delete_word = "DELETE FROM words WHERE word = ?";
	PreparedStatement ps1 = null;
	PreparedStatement ps2 = null;
	try {
	    ps1 = Connector.getConnection().prepareStatement(delete_fk);
	    ps1.setString(1, word);
	    ps1.executeUpdate();
	    ps2 = Connector.getConnection().prepareStatement(delete_word);
	    ps2.setString(1, word);
	    ps2.executeUpdate();
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    ps1.close();
	    ps2.close();
	}
    }

    public List<Word> getRandomWords() throws SQLException {
	// pass
	List<Word> words = new LinkedList<>();
	List<Word> _words = readAll();
	Random rand = new Random();
	int index;
	int n = 5;
	if (n > _words.size()) {
	    n = _words.size();
	}
	for (int i = 0; i < n; i++) {
	    index = rand.nextInt(_words.size());
	    words.add(_words.get(index));
	    _words.remove(index);
	}
	return words;
    }

    public static void main(String[] args) {
	WordDAO wordDAO = new WordDAO();
	try {
	    // wordDAO.addTranslation("Test1", "тест1");
	    for (Word _w : wordDAO.readAll()) {
		System.out.print(_w.getWord());
		System.out.print(_w.getComplexity());
		System.out.println(_w.getStingComplexity());
	    }

	    for (Word _w : wordDAO.getRandomWords()) {
		System.out.print(_w.getWord());
		System.out.print(_w.getComplexity());
		System.out.println(_w.getStingComplexity());
	    }
	} catch (SQLException e) {
	}
    }

}
