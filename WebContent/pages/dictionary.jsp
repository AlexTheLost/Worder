<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="domain.word.Word, dao.word.WordDAO, java.util.List;"%>
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
            <li class="active">
                <a href="<%=request.getContextPath()%>/pages/dictionary.jsp">Dictionary</a>
            </li>
        </ul>

        <br />

        <div class="tab-content">
            <div class="tab-pane active" id="add">
                <div class="CRUD">
                    <!-- ADD -->
                    <form action="<%=request.getContextPath()%>/add_word" method="post">
                        <input type="text" name="word" placeholder="Word">
                        <input type="submit" class="" value="Add">
                        <br />
                        Complexity:&nbsp;
                        <input type="radio" name="complexity" value="low">
                        Low&nbsp;
                        <input type="radio" name="complexity" value="medium">
                        Medium&nbsp;
                        <input type="radio" name="complexity" value="hard" checked>
                        Hard&nbsp;

                    </form>
                </div>
            </div>
        </div>

        <%
            WordDAO wordDAO = new WordDAO();
            List<Word> words = wordDAO.readAll();
        %>
        <div class="panel panel-default">
            <div class="panel-heading">Dictionary</div>
            <table class="table">
                <thead>
                    <tr>
                        <th>№</th>
                        <th>EN Word</th>
                        <th>Complexity</th>
                        <th>Ru Translate</th>
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
                            <%=w.getWord()%>
                        </td>
                        <td>
                            <i><%=w.getStingComplexity()%>&nbsp;(<%=w.getComplexity()%>%)
                            </i>
                        </td>
                        <td>
                            <form action="<%=request.getContextPath()%>/add_translation" method="post">
                                <input type="submit" class="" value="Add">
                                <input type="hidden" name="word" value="<%=w.getWord()%>">
                                <input type="text" name="translation" placeholder="Translate">
                            </form>
                            <%
                                for (String translation : w.getTranslations()) {
                            %>
                            <form action="/del_translation" method="post">
                                <%=translation%>
                                <input type="submit" value="Del">
                                <input type="hidden" name="word" value="<%=w.getWord()%>">
                                <input type="hidden" name="translate" value="<%=translation%>">
                            </form>
                            <%
                                }
                            %>
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