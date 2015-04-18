<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- 
    Document   : Spectrum
    Created on : 15.04.2015, 17:04:24
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<style type="text/css">
    p { 
        text-align: center;
        font-family: Colibri;
    }
    h1 {
        text-align: center;
        font-family: Colibri;
    }
    thead{
        font-style: italic;
        background-color: paleturquoise;
        /*        border: 1px blue solid;*/
    }
    table{
        margin: auto;     
        font-family: Colibri;       
/*        width: 75%;*/
        text-align: center;
    }
    .spectrumtable{
/*        background-color: lightblue;*/
        width: 75%;

    }
    .tableborder{
        border: 1px #d9edf7 solid;
    }
    .center{
        text-align: center;
    }

    .navigationButtonsTable {
        width: 20%;
        /*        margin: auto;*/
        text-align: center;
    }
    .smalltext{
        font-size: 75%
    }
    .righttextalign { text-align: right; }

</style>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Spectrum</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
    </head>
    <body>
        <h1>Spectrum</h1>

        <c:choose>
            <c:when test="${sessionScope.user == null}"> 
                <p>
                    Hi, quest! Would you like to 
                    <a href="controller?action=login&page=${param.page}&paginationstep=${param.paginationstep}">login</a> 
                    <%--         or <a href="controller?action=register&page=${param.page}&paginationstep=${param.paginationstep}">register</a> --%>
                </p> 
            </c:when>
            <c:otherwise>
                <p>
                    Hi, ${sessionScope.user.name} 
                    (<a href="controller?action=logout&page=${param.page}&paginationstep=${param.paginationstep}">logout</a>)
                </p>
            </c:otherwise>
        </c:choose>
        <form action = "controller" >
            <p> Show 
                <select name ="paginationstep" id="myselect" onchange="this.form.submit()" >
                    <option value="5" ${5 == param.paginationstep ? 'selected="selected"' : ''}> 5 </option>
                    <option value="10" ${10 == param.paginationstep ? 'selected="selected"' : ''}> 10 </option>
                    <option value="20" ${20 == param.paginationstep ? 'selected="selected"' : ''}> 20 </option>
                    <option value="40" ${40 == param.paginationstep ? 'selected="selected"' : ''}> 40 </option>
                    <option value="60" ${60 == param.paginationstep ? 'selected="selected"' : ''}> 60 </option>
                </select>
                records by a page
            </p>
            <input type="hidden" name="page" value="1"/>
        </form>

<!--        <form action="controller" method="post">
            <p>
                <input type="file" name="inputedfile"/>
                <input type="submit" value="send">
            </p>
        </form>-->



        <table class="table-striped spectrumtable tableborder">
            <thead>
            <td>frequency, Hz</td>
            <td>voltage, V</td>
        </thead>

        <tbody>
            <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}">
                <tr>
                    <td>${array.frequency}</td>
                    <td>${array.voltage}</td>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
    <table>
        <td class="pagination">
            <a href="controller?page=1&paginationstep=${param.paginationstep}" > First </a>
        </td>
        <td class="navigationButtonsTable">
            <a href="controller?page=${param.page - 1}&paginationstep=${param.paginationstep}" > Previous </a>
        </td>
        <td class="navigationButtonsTable"> ${param.page} </td>
        <td class="navigationButtonsTable">
            <a href="controller?page=${param.page + 1}&paginationstep=${param.paginationstep}"> Next</a>
        </td>
        <td class="navigationButtonsTable">
            <a href="controller?page=last&paginationstep=${param.paginationstep}" > Last </a>
        </td>
    </table>
</body>
</html>
