<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>学生管理系统 - 主页</title>
    <style>
        body { font-family: Arial, sans-serif; text-align: center; margin-top: 50px; }
        .container { width: 400px; margin: 0 auto; padding: 20px; border: 1px solid #ddd; border-radius: 5px; }
        .user-info { margin: 20px 0; }
        .logout { margin-top: 20px; }
        .logout a { color: #0066cc; text-decoration: none; }
        .logout a:hover { text-decoration: underline; }
    </style>
</head>
<body>
<div class="container">
    <h2>欢迎使用学生管理系统</h2>

    <div class="user-info">
        <p>账号：${user.username}</p>
        <p>身份：${user.role}</p>
    </div>

    <div class="logout">
        <a href="logout">退出登录</a>
    </div>
</div>
</body>
</html>