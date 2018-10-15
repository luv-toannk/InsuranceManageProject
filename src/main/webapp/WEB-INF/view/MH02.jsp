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
<div align="right">
    <a href="doLogout">Logout</a>
</div>
<div align="center">
    <table border="0" width="70%">
        <tbody>
        <tr>
            <td>
                <h3 align="left">Danh sách thẻ bảo hiểm</h3>
            </td>
        </tr>
        <form action="listUser?case=search" method="POST">
            <tr>
                <td><b align="left">Tên công ty</b></td>
            <tr>

                <td><select name="companyID">

                    <c:forEach var="company" items="${listCompany}">
                        <option value="${company.id}"
                            ${company.id == companyID ? 'selected':''}>${company.companyName}</option>
                    </c:forEach>
                </select></td>

            </tr>

            <tr>
                <td><br></td>
            </tr>
            <tr>
                <td><b>Thông tin tìm kiếm</b></td>

            </tr>

            <tr>
                <td width=35%>
                    <fieldset>
                        <table align="left">

                            <tr cellpadding="10">
                                <td>
                                    <p>Tên người sử dụng</p>
                                </td>

                                <td><input type="text" name="userName"
                                           value="${userName}"></td>
                            </tr>

                            <tr>
                                <td>
                                    <p>Mã số thẻ bảo hiểm</p>
                                </td>

                                <td><input type="text" name="insuranceID"
                                           value="${insuranceNumber}"></td>
                            </tr>
                            <tr>
                                <td>
                                    <p>Nơi đăng ký</p>
                                </td>
                                <td><input type="text" name="insurancePlaceReg"
                                           value="${placeReg}"></td>
                            </tr>
                            <tr>
                                <td colspan="3" align="center"><input type="submit"
                                                                      size="50" value="Tìm kiếm"></td>
                            </tr>
                        </table>
                    </fieldset>
                </td>
                <td width=65%></td>
            </tr>
        </form>
        <tr>
            <td><br></td>
        </tr>
        <tr>
            <td width="35%"><a href="doAdd"><input type="button" value="Đăng ký"></a></td>
            <c:if test="${not empty listUserInfo}">
                <td><a href="exportCSV"><input type="button"
                                               value="Export CSV"></a></td>
            </c:if>
        </tr>
        <tr>
            <td>
                <c:if test="${empty listUserInfo}">
                <font color="red">${listNull}</font>
                </c:if>
            <td>
        </tr>
        <tr>
            <td><br></td>
        </tr>
        <tr>
            <td colspan="3">
                <table border="1" cellspacing="0" cellpadding="2" width="70%">
                    <tr align="center">
                        <c:choose>
                            <c:when test="${sortStatus eq 'ASC'}">
                                <td>Tên người sử dụng <label>▲</label> <a
                                        href="listUser?case=sort&typeSort=sortByName">▽</td>
                            </c:when>
                            <c:when test="${sortStatus eq 'DESC'}">
                                <td>Tên người sử dụng <a
                                        href="listUser?case=sort&typeSort=sortByName">△ </a><label>▼</label></td>
                            </c:when>
                        </c:choose>
                        <td>Giới tính</td>
                        <td>Ngày sinh</td>
                        <td>Mã số thẻ bảo hiểm</td>
                        <td>Kỳ hạn</td>
                        <td>Nơi đăng ký KCB</td>
                    </tr>
                    <c:forEach var="userInfo" items="${listUserInfo}">
                        <tr>
                            <td>${userInfo.userName}</td>
                            <td>${userInfo.userSex}</td>
                            <td>${userInfo.userBirthFormat}</td>
                            <td>${userInfo.insuranceID}</td>
                            <td>${userInfo.insuranceStartDateFormat}~
                                    ${userInfo.insuranceEndDateFormat}</td>
                            <td>${userInfo.insurancePlaceReg}</td>
                        </tr>
                    </c:forEach>
                </table>
            </td>
        </tr>
        </tbody>
    </table>
    <table>
        <tr>
            <c:if test="${currentPage > 1}">
                <a href="listUser?case=paging&currentPage=${currentPage-1}">&lt;&lt;</a>
            </c:if>
            <c:forEach var="page" items="${listPaging}">
                <c:choose>
                    <c:when test="${page == currentPage}">
                        <c:out value="${page}"></c:out>|
                    </c:when>
                    <c:when test="${page != currentPage}">
                        <a href="listUser?case=paging&currentPage=${page}"> <c:out
                                value="${page}"></c:out>
                        </a>|
                    </c:when>
                </c:choose>
            </c:forEach>
            <c:if test="${lastPage < totalPage}">
                <a href="listUser?case=paging&currentPage=${currentPage+1}">&gt;&gt;</a>
            </c:if>
        </tr>
    </table>
</div>
</body>
</html>