<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="//code.jquery.com/jquery-1.10.2.js"></script>
    <script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
    <link rel="stylesheet"
          href="//code.jquery.com/ui/1.11.4/themes/smoothness/jquery-ui.css">
    <c:if test="${status==null}">
        <c:set var="status" value="have"></c:set>
    </c:if>
    <script>
        $(function () {
            $(".datepicker").datepicker({
                showOn: "button",
                buttonImage: "//jqueryui.com/resources/demos/datepicker/images/calendar.gif",
                buttonImageOnly: true,
                dateFormat: 'dd/mm/yy',
                changeYear: true,
                changeMonth: true
            });
        });
    </script>
    <script>
        function checkRadio(source) {
            var id = source.id;
            var oldCompany = document.getElementById("oldCompany");
            var newCompany = document.getElementById("newCompany");
            if (id == "notHave") {
                oldCompany.style.display = "none";
                newCompany.style.display = "block";
            }
            if (id == "have") {
                newCompany.style.display = "none";
                oldCompany.style.display = "block";

            }
        }

        function loadAjax() {
            var companyId = $('#companyCheck').val();
            $.ajax({
                url: "/ajax/" + companyId,
                success: function (data) {
                    var company = data.split(",");
                    var tblResult = "<table class='tbl-ajax'>"
                        + "<tr>"
                        + "<td>Tên công ty: </td>"
                        + "<td>" + company[0] + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Địa chỉ: </td>"
                        + "<td>" + company[1] + "</td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Email: </td>"
                        + "<td><a href='#'>" + company[2] + "</a></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td>Điện thoại: </td>"
                        + "<td>" + company[3] + "</td>"
                        + "</tr>"
                        + "</table><br/>";
                    $('#ajax-response').html(tblResult);
                }
            });
        }
    </script>
    <title>InsuranceManage</title>
</head>
<body>
<div align="right">
    <a href="doLogout">Logout</a>
</div>
</body>
<center>
    <h2>Thêm mới thông tin thẻ bảo hiểm</h2>
    <form:form action="/addAction" method="POST" modelAttribute="user" id="addForm">
        <table>
           <tr>
               <td><form:errors path="insuranceID" cssStyle="color: red"></form:errors></td>
           </tr>
            <tr>
                <td><form:errors path="userName" cssStyle="color: red"></form:errors></td>
            </tr>
            <tr>
                <td><form:errors path="userBirth" cssStyle="color: red"></form:errors></td>
            </tr>
            <tr>
                <td><form:errors path="insuranceID.isrPlaceReg" cssStyle="color: red"></form:errors></td>
            </tr>
            <tr>
                <td><form:errors path="insuranceID.isrStartDate" cssStyle="color : red"></form:errors></td>
            </tr>
            <tr>
                <td><form:errors path="insuranceID.isrEndDate" cssStyle="color: red"></form:errors></td>
            </tr>
            <c:forEach var="error" items="${errorList}">
                <tr>
                    <td style="color: red">${error}</td>
                </tr>
            </c:forEach>
        </table>
        <table>
            <tr>
                <td><form:label path="insuranceID">Mã số thẻ bảo hiểm</form:label></td>
                <td><form:input path="insuranceID.isrNumber"></form:input>(format : 10 chữ số)</td>
            </tr>
            <tr>
                <td><form:label path="userName">Họ và tên</form:label></td>
                <td><form:input path="userName"></form:input></td>
            </tr>
            <tr>
                <td><form:label path="userSex">Giới tính</form:label></td>
                <td>
                    <form:select path="userSex">
                        <form:option value="01" seleted = "selected">Nam</form:option>
                        <form:option value="02">Nữ</form:option>
                    </form:select>
                </td>
            </tr>
            <tr>
                <td><form:label path="userBirth">Ngày sinh</form:label></td>
                <td><form:input path="userBirth" cssClass="datepicker" readonly="true"></form:input></td>
            </tr>

            <tr>
                <td><form:label path="companyID">Công ty</form:label></td>
                <td>
                    <div><input id="have" type="radio" name="radioCheckCompany" value="have" checked="checked"
                        ${status eq 'have'?'checked':''} onclick="checkRadio(this)"> Công ty đã
                        có <p>

                        <div id="oldCompany"   ${status eq 'have'?'style = "display:"':'style = "display:none"'} >
                            <form:select path="companyID.id" onchange="loadAjax()" id="companyCheck">
                                <c:forEach var="company" items="${listCompany}">
                                    <option value="${company.id}" ${company.id == companyID ? 'selected':''}>${company.companyName}</option>
                                </c:forEach>
                            </form:select>
                        </div>
                        <div id="ajax-response">

                        </div>
                    </div>

                    <div><input id="notHave" type="radio" name="radioCheckCompany" ${status eq 'nothave'?'checked':''}
                                value="nothave" onclick="checkRadio(this)"> Đăng ký
                        theo công ty mới<p>
                        <div id="newCompany"
                            ${status eq 'nothave'?'style = "display:"':'style = "display:none"'} ">
                        <!--Table bi cut-->
                        <table>
                            <tr>
                                <td>Tên công ty</td>
                                <td><input type="text" name="cName"></td>
                            </tr>
                            <tr>
                                <td>Địa chỉ</td>
                                <td><input type="text" name="cAdd"></td>
                            </tr>
                            <tr>
                                <td>Email</td>
                                <td><input type="text" name="cEmail"></td>
                            </tr>
                            <tr>
                                <td>Điện thoại</td>
                                <td><input type="text" name="cTel"></td>
                            </tr>
                        </table>
                    </div>
                    </div>
                </td>
            </tr>
            <tr>
                <td><form:label path="insuranceID">Nơi đăng kí KCB</form:label></td>
                <td><form:input path="insuranceID.isrPlaceReg"></form:input></td>
            </tr>
            <tr>
                <td><form:label path="insuranceID">Ngày bắt đầu thẻ BH</form:label></td>
                <td><form:input path="insuranceID.isrStartDate" cssClass="datepicker"></form:input></td>
            </tr>
            <tr>
                <td><form:label path="insuranceID">Ngày kết thúc thẻ BH</form:label></td>
                <td><form:input path="insuranceID.isrEndDate" cssClass="datepicker"></form:input></td>
            </tr>
            <tr>
                <td></td>
                <td><a href="listUser?case=back"><input type="button" value="Hủy"></a></td>
                <td><input type="submit" value="Đăng kí"></td>
            </tr>

        </table>
    </form:form>
</center>
</html>