<%@ page import="lib.Model.User" %>
<%@ page import="java.sql.SQLException" %>
<%@ page import="sun.awt.SunHints" %>
<%--
  Created by IntelliJ IDEA.
  User: ttop5
  Date: 16-4-21
  Time: 下午6:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@include file="base.jsp"%>
<html>
<head>
    <title></title>
</head>
<body>

  <div class="container">
    <h5><b>当前位置</b>：信息查询 > 成绩查询</h5>
    <hr>
    <div>
      <%
        StudentDAO studentDAO = new StudentDAO();
      %>

<%--      <%=--%>
<%--      studentDAO.getScore(((User)session.getAttribute("currentUser")).getEmail())--%>
<%--      %>--%>

      <%
        String score = "";
        try {
          score = studentDAO.getScore( ((User)session.getAttribute("currentUser")).getEmail() );
        } catch (SQLException e) {
          e.printStackTrace();
        }
      %>

      <%= score %>    <-- 展示分数

    </div>
  </div>

</body>
</html>
