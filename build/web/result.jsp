<%-- 
    Document   : result
    Created on : Sep 23, 2024, 12:31:21 AM
    Author     : Admin
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Password Reset Result</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
</head>
<body>
    <div class="container mt-5">
        <div class="alert alert-info">
            <h4 class="alert-heading">Result</h4>
            <p>${message}</p>
            <a href="${pageContext.request.contextPath}/home" class="btn btn-primary">Home</a>
        </div>
    </div>
</body>
</html>

