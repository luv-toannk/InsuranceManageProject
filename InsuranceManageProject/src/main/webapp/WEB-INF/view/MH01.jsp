<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>InsuranceManage</title>
</head>
<body>
<center>
    <h2>Đăng nhập hệ thống</h2>
    <form:form action="doLogin" method="POST" modelAttribute="admin">
        <form:errors path="username" cssStyle="color:red"></form:errors><br>
        <form:errors path="password" cssStyle="color:red"></form:errors>
        <font color="red">${errorMessage}</font>
        <table>
            <tr>
                <td><form:label path="username">Tên đăng nhập</form:label></td>
                <td><form:input path="username"/></td>
            </tr>
            <tr>
                <td><form:label path="password">Mật khẩu</form:label></td>
                <td><form:password path="password"/></td>
            </tr>
            <tr>
                <td colspan="1"><input type="submit" value="Đăng nhập">
            </tr>
        </table>
    </form:form>
</center>
</body>
</html>