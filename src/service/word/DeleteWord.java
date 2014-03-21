package service.word;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.word.WordDAO;

@WebServlet("/delete_word")
public class DeleteWord extends HttpServlet {
    private static final long serialVersionUID = 1L;
    WordDAO wordDAO;

    public DeleteWord() {
	super();
	wordDAO = new WordDAO();
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	try {
	    wordDAO.deleteWord(request.getParameter("word"));
	} catch (SQLException e) {
	    // TODO error message to user on dictionary.jsp
	}
	response.sendRedirect(request.getContextPath()
		+ "/pages/dictionary.jsp");
    }
}
