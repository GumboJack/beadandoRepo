<%@ taglib prefix="th" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: lmarc
  Date: 2020. 12. 01.
  Time: 19:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="en">
<head>
    <title>Login</title>
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no" />
    <meta charset="utf-8" />
</head>
<body>

<jsp:include page="../navbar.jsp" />
<jsp:include page="../modal.jsp" />

<div class="container">
    <th:form action="/login" modelAttribute="credentials" method="post">
        <div class="form-group">
            <label for="email">Email address</label>
            <th:input type="email" class="form-control" id="email" value="" path="username" />
        </div>
        <div class="form-group">
            <label for="password">Password</label>
            <th:input type="password" class="form-control" value ="" id="password" path="password" />
        </div>
        <th:button type="submit" class="btn btn-primary" id="btn_submit" disabled="true"></th:button>
        <button type="button" class="btn btn-primary" id="btnSubmit">Login</button>
    </th:form>
</div>

<script>
    $(document).ready(function(){
        
        $('#btnSubmit').off('click').on('click', function () {

            var email = $('#email').val();
            var password = $('#password').val();

            if (email.length === 0){
                showErrorModal("Missing email address!")
            } else if (password.length < 8){
                showErrorModal("Password at least 8 character long")
            } else {
                var obj = {};

                obj.email = email;
                obj.password = password;

                $.ajax({
                    method: 'POST',
                    url: 'http://localhost:8081/rest/login',
                    contentType: 'application/json;charset=UTF-8',
                    data: JSON.stringify(obj),
                    async: true,
                    success: function (data){
                        console.log(data);

                        if (data.error === '0'){
                            showSuccessModal(data.msg);
                        } else {
                            showErrorModal(data.msg);
                        }
                    },
                    error: function (xhr) {
                        console.log(xhr.responseText);
                        showErrorModal("Unknown error!")
                    }
                });
            }
        });
    });
</script>
</body>
</html>
