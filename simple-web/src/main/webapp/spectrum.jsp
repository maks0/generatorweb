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
        ok
        <c:set var="usr" value="maks" scope="page"/>
        ${2+5}
        ${usr}
        ${param.action}
        
     
        
              <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}" varStatus = "loopCounter">

${loopCounter.count}
<!--                    <tr>
                        <td>${loopCounter.count}</td>

                    </tr>-->
</c:forEach> 

    </body>
</html>
