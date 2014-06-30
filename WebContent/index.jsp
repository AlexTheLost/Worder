<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Home</title>
<link type="text/css" href="include/bootstrap/css/bootstrap.min.css" rel="stylesheet" media="screen">
<script type="text/javascript" src="http://code.jquery.com/jquery-latest.js"></script>
<script type="text/javascript" src="include/bootstrap/js/bootstrap.js"></script>

</head>
<body>

    <div class="container">
        <br />

        <ul class="nav nav-tabs">
            <li class="active">
                <a href="<%=request.getContextPath()%>/index.jsp">Home</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/quiz.jsp">Quiz</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/dictionary.jsp">Dictionary</a>
            </li>
            <li>
                <a href="<%=request.getContextPath()%>/pages/quizzes_units.jsp">Quizzes</a>
            </li>
        </ul>

    </div>

</body>
</html>