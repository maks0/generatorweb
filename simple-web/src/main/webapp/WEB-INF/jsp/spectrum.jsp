<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- 
    Document   : Spectrum
    Created on : 15.04.2015, 17:04:24
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spectrum</title>
    </head>
    <body>
        <h1>Spectrum</h1>
        <table>
            <thead>
            <td>time</td>
            <td>voltage</td>

        </thead>
        <tbody>
            <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}" varStatus = "loopCounter">

                <tr>
                    <td>${loopCounter.count}</td>
                    <td>${array.voltage}</td>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
</body>
</html>
