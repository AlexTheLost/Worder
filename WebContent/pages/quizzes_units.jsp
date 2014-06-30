<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.word.Word, dao.word.WordDAO, java.util.List, java.net.URLEncoder"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Dictionary</title>
<link type="text/css" href="../include/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="../include/bootstrap/js/bootstrap.js"></script>

<style type="text/css">
.CRUD {
	padding: 10px;
	margin: 10px;
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
            <li>
                <a href="<%=request.getContextPath()%>/pages/quiz.jsp">Quiz</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/dictionary.jsp">Dictionary</a>
            </li>
            <li class="active">
                <a href="<%=request.getContextPath()%>/pages/quizzes_units.jsp">Quizzes</a>
            </li>
        </ul>

        <br />

        <div class="tab-content">
            <div class="tab-pane active" id="add">
                <div class="CRUD">                  
                    <!-- Create new Quiz -->
                    <form action="<%=request.getContextPath()%>/CreateNewQuiz" method="post">
                        <input type="text" name="title" placeholder="Quiz Title">
                        <input type="submit" class="" value="Add">
                    </form>
                </div>
            </div>
        </div>

        <%
            WordDAO wordDAO = new WordDAO();
            List<Word> words = wordDAO.readAll();
        %>
        
        <div class="panel panel-default">
            <div class="panel-heading">Quiz</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>№</th>
                        <th>Title</th>
                        <th>Description</th>
                    </tr>
                </thead>
                <%
                    int i = 0;
                    for (Word w : words) {
                %>
                <tbody>
                    <tr>
                        <td>
                            <%=++i%>
                        </td>
                        <td>
                            <form action="<%=request.getContextPath()%>/delete_word" method="post">
                                <%=w.getWord()%>
                                <input type="submit" class="btn btn-default btn-xs" value="Del">
                                <input type="hidden" name="word" value="<%=w.getWord()%>">
                            </form>
                        </td>
                        <td>
                            <i><%=w.getStingComplexity()%>&nbsp;(<%=w.getComplexity()%>%)
                            </i>
                        </td>
                        <td>
                            <div class="row">
                                <div class="col-md-6">
                                    <form action="<%=request.getContextPath()%>/add_translation" method="post">
                                        <input type="submit" class="btn btn-default btn-xs" value="Add">
                                        <input type="hidden" name="word" value="<%=w.getWord()%>">
                                        <input type="text" name="translation" placeholder="Translate">
                                    </form>
                                </div>
                                <div class="col-md-6">
                                    <%
                                        for (String translation : w.getTranslations()) {
                                    %>
                                    <form action="<%=request.getContextPath()%>/del_translation" method="post"
                                        class="form-inline">
                                        <%=translation%>
                                        <input type="submit" class="btn btn-default btn-xs" value="Del">
                                        <input type="hidden" name="word" value="<%=w.getWord()%>">
                                        <input type="hidden" name="translation" value="<%=translation%>">
                                    </form>
                                    <%
                                        }
                                    %>
                                </div>
                            </div>
                        </td>
                    </tr>
                </tbody>
                <%
                    }
                %>
            </table>
        </div>
    </div>
</body>
</html>