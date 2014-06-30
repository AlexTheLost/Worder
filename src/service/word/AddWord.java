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
	WordDAO dao = new WordDAO();
	try {
	    dao.create(word, 0);
	} catch (SQLException e) {
	    // TODO error message to user on dictionary.jsp
	}
	response.sendRedirect(request.getContextPath()
		+ "/pages/dictionary.jsp");
    }

}
