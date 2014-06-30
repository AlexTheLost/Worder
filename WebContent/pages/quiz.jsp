<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.word.Word, dao.word.WordDAO, java.util.List;"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Quiz</title>
<link type="text/css" href="../include/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../include/bootstrap/js/bootstrap.js"></script>

<style type="text/css">
.word {
	margin: 5px;
	padding: 15px;
	border-radius: 5px;
	border-color: rgba(0, 0, 0, 0.1);
	border-style: solid;
	background-color: #eee;
}
</style>
</head>
<body>

    <div class="container">
        <br />

        <ul class="nav nav-tabs">
            <li>
                <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
            </li>
            <li class="active">
                <a href="<%=request.getContextPath()%>/pages/quiz.jsp">Quiz</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/dictionary.jsp">Dictionary</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/quizzes_units.jsp">Quizzes Units</a>
            </li>
        </ul>

        <br />

        <%
            WordDAO wDAO = new WordDAO();
            List<Word> words = wDAO.getRandomWords();
        %>

        <br />

        <%
            int i = 1;
            for (Word w : words) {
        %>
        <b><%=i++%></b>
        &nbsp;<%=w.getWord()%>
        <br />
        <%
            }
        %>

        <div class="container">
            <div class="row">
                <div class="col-md-4 col-md-offset-4">
                    <div class="word">
                        <div align="center">
                            <b>Word</b>
                        </div>
                    </div>
                </div>

                <div class="col-md-4 col-md-offset-4">
                    <form class="navbar-form navbar-left">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="translate">
                        </div>
                        <button type="submit" class="btn btn-default">Submit</button>
                    </form>
                </div>

            </div>
        </div>


    </div>
</body>
</html>