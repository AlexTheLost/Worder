package service.word;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.word.WordDAO;

@WebServlet("/add_translation")
public class AddTranstation extends HttpServlet {
    private static final long serialVersionUID = 1L;

    WordDAO wordDAO;

    public AddTranstation() {
	super();
	wordDAO = new WordDAO();
    }

    protected void doPost(HttpServletRequest request,
	    HttpServletResponse response) throws ServletException, IOException {
	request.setCharacterEncoding("UTF-8");
	try {
	    wordDAO.addTranslation(request.getParameter("word"),
		    request.getParameter("translation"));
	} catch (SQLException e) {
	    // TODO Auto-generated catch block
	    e.printStackTrace();
	}
	response.sendRedirect(request.getContextPath()
		+ "/pages/dictionary.jsp");
    }
}
