package service.word;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.word.WordDAO;

@WebServlet("/add_word")
public class AddWord extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddWord() {
	super();
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	String word = request.getParameter("word");
	float complexity = getComplexity(request.getParameter("complexity"));
	WordDAO dao = new WordDAO();
	try {
	    dao.create(word, complexity);
	} catch (SQLException e) {
	    e.printStackTrace();
	}
	response.sendRedirect(request.getContextPath()
		+ "/pages/dictionary.jsp");
    }

    private float getComplexity(String complexity) {
	switch (complexity) {
	case "low":
	    return 0f;
	case "medium":
	    return 0.5f;
	case "hard":
	    return 1f;
	default:
	    return 0.5f;
	}
    }
}
