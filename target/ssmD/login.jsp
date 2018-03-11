<%--
  Created by IntelliJ IDEA.
  User: WangXinYu
  Date: 2018/2/24
  Time: 09:13
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jstl/core_rt" prefix="c" %>
<c:set var="ctx" value="${pageContext.request.contextPath}"></c:set>
<!doctype html>
<html>
<head>
    <title>管理系统·登录</title>
    <meta content="text/html;charset=utf-8" http-equiv="Content-Type">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=no" name="viewport">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,Chrome=1">
    <meta name="renderer" content="webkit">
    <link rel="stylesheet" href="${ctx}/css/login.css">
    <link rel="stylesheet" href="${ctx}/components/semantic/semantic.min.css">
</head>
<body>
<div class="lg-container">
    <div class="lg-title">
    </div>
    <div class="lg-main">
        <form class="ui form" method="post">
            <div class="field">
                <label>用户名</label>
                <input type="text" id="first-name" name="first-name" placeholder="UserName">
            </div>
            <div class="field">
                <label>密码</label>
                <input type="password" id="last-name" name="last-name" placeholder="PassWord">
            </div>
        </form>
        <button class="ui blue button" style="width: 100%;margin-top: 3vh" onclick="login()">登录</button>
    </div>
    <div class="lg-footer">

    </div>
</div>
</body>
<script src="${ctx}/js/jquery-3.2.1.min.js"></script>
<script src="${ctx}/components/semantic/semantic.min.js"></script>
<script src="${ctx}/js/login.js"></script>
</html>
