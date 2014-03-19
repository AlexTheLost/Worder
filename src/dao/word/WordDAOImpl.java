package dao.word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import db.connecter.Connector;
import domain.word.Word;
import domain.word.WordImpl;

public class WordDAOImpl implements WordDAO {

    @Override
    public void create(Word word) throws SQLException {
	PreparedStatement ps = null;
	String query = "INSERT INTO words (word, lang) VALUES (?, ?)";
	try {
	    ps = Connector.getConnection().prepareStatement(query);
	    ps.setString(1, word.getWord());
	    ps.setString(2, word.getLang().toString());
	    ps.executeUpdate();
	} catch (SQLException exc) {
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    if (ps != null)
		ps.close();
	}
    }

    @Override
    public Word read(Word word) throws SQLException {
	Statement stmt = null;
	ResultSet rs = null;
	Word result = null;
	String query = "SELECT * FROM words WHERE words.word =\'" + word.getWord() + "\'";
	try {
	    stmt = Connector.getConnection().createStatement();
	    rs = stmt.executeQuery(query);
	    rs.next();
	    result = assembleWord(rs, null);
	} catch (SQLException exc) {
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    if (stmt != null)
		stmt.close();
	    if (rs != null)
		rs.close();
	}
	return result;
    }

    private Word assembleWord(ResultSet rs1, ResultSet rs2) throws SQLException {
	String w = rs1.getString("word");
	Lang l = Lang.valueOf(rs1.getString("lang"));
	Word word = new WordImpl(w, l);
	return word;
    }

    @Override
    public void update(Word Word) throws SQLException {
	// TODO Auto-generated method stub

    }

    @Override
    public void delete(Word word) throws SQLException {
	// TODO Auto-generated method stub
    }

    public static void main(String[] args) {

	Word w = new WordImpl("Test", Lang.EN);
	WordDAO wordDAO = new WordDAOImpl();
	try {
//	    wordDAO.create(w);
	    w = wordDAO.read(w);
	    System.out.println(w.getWord());
	    System.out.println(w.getLang());
	    System.out.println(w.getTranslations());
	} catch (SQLException e) {
	}
    }

}
