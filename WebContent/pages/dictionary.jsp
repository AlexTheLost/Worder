<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
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

        <div>

            <ul class="nav nav-tabs">
                <li class="active">
                    <a href="#add" data-toggle="tab">Add Word</a>
                </li>
                <li>
                    <a href="#update" data-toggle="tab">Update Word</a>
                </li>
                <li>
                    <a href="#delete" data-toggle="tab">Delete Word</a>
                </li>
            </ul>

            <div class="tab-content">
                <div class="tab-pane active" id="add">
                    <div class="CRUD">
                        <!-- ADD -->
                        <form action="" method="post">
                            <input type="text" class="" placeholder="En word">
                            <br />
                            <input type="text" class="" placeholder="Complexity">
                            <br />
                            <input type="text" class="" placeholder="Translate via ';'">
                            <br />
                            <input type="submit" class="" placeholder="Submit">
                        </form>
                    </div>
                </div>
                <div class="tab-pane" id="update">
                    <div class="CRUD">
                        <!-- UPDATE -->
                        <form action="" method="post">
                            <input type="text" class="" placeholder="En word">
                            <br />
                            <input type="text" class="" placeholder="Complexity">
                            <br />
                            <input type="text" class="" placeholder="Translate via ';'">
                            <br />
                            <input type="submit" class="" placeholder="Submit">
                        </form>
                    </div>
                </div>
                <div class="tab-pane" id="delete">
                    <div class="CRUD">
                        <!-- DELETE -->
                        <form action="" method="post">
                            <input type="text" class="" placeholder="En word">
                            <input type="submit" class="" placeholder="Submit">
                        </form>
                    </div>
                </div>
            </div>

        </div>

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
                <tbody>
                    <tr>
                        <td>1</td>
                        <td>Word_1</td>
                        <td>easily</td>
                        <td>слово_1.0; слово_1.1</td>
                    </tr>
                    <tr>
                        <td>2</td>
                        <td>Word_2</td>
                        <td>medium</td>
                        <td>слово_2.0; слово_2.1</td>
                    </tr>
                    <tr>
                        <td>3</td>
                        <td>Word_3</td>
                        <td>hard</td>
                        <td>слово_3.0; слово_3.1</td>
                    </tr>
                </tbody>
            </table>
        </div>
    </div>
</body>
</html>