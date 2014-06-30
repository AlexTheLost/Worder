package dao.word;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import db.connecter.Connector;
import domain.word.Quiz;
import domain.word.Word;

public class QuizDAO {

    public void create(String title) throws SQLException {
	Statement stmt = Connector.getConnection().createStatement();
	try {
	    stmt.executeQuery("INSERT INTO quizzes (title) VALUES (" + title
		    + ")");
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	}
    }

    public List<Quiz> readAll() throws SQLException {
	Statement stmt = Connector.getConnection().createStatement();
	ResultSet rs = null;
	try {
	    rs = stmt.executeQuery("SELECT * FROM quizzes");
	    return assembleQuizzes(rs);
	} catch (SQLException exc) {
	    // TODO logger
	    exc.printStackTrace();
	    throw new SQLException();
	} finally {
	    stmt.close();
	    rs.close();
	}
    }

    private List<Quiz> assembleQuizzes(ResultSet rs) throws SQLException {
	List<Quiz> quizzes = new LinkedList<>();
	Quiz quiz;
	while (rs.next()) {
	    quiz = new Quiz();
	    quiz.setTitle(rs.getString("title"));
	    quiz.setDescription(rs.getString("description"));
	    quizzes.add(quiz);
	}

	return quizzes;
    }

}
