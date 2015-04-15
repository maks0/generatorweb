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
        border: 1px blue solid;
    }
    table{
        margin: auto;     
        font-family: Colibri;       
/*        width: 95%;*/
        text-align: center;
    }
    .spectrumtable{
        background-color: lightblue;
        border: 1px blue solid;
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
    </head>
    <body class="spectrumtable">
        <h1>Spectrum</h1>
        <table>
            <thead>
            <td>time</td>
            <td>voltage</td>

        </thead>
        <tbody>
            <c:forEach var = "array" items = "${requestScope.spectrum.recordsList}">

                <tr>
                    <td>${array.time}</td>
                    <td>${array.voltage}</td>
                </tr>
            </c:forEach> 
        </tbody>
    </table>
        <table>
            <td class="navigationButtonsTable">
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
