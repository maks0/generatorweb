<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%-- 
    Document   : Spectrum
    Created on : 15.04.2015, 17:04:24
    Author     : maks
--%>

<!DOCTYPE html>
<%--<style type="text/css">--%>
    <%--thead{--%>
        <%--font-style: italic;--%>
        <%--background-color: paleturquoise;--%>
        <%--/*        border: 1px blue solid;*/--%>
    <%--}--%>
    <%--.tableborder{--%>
        <%--border: 1px #d9edf7 solid;--%>
    <%--}--%>
    <%--.smalltext{--%>
        <%--font-size: 75%--%>
    <%--}--%>
    <%--html {--%>
        <%--height: 100%;--%>
        <%--width: 97%;--%>
    <%--}--%>
    <%--body {--%>
        <%--font-family: "Open Sans","Helvetica Neue",Arial,sans-serif;--%>
        <%--height: 100%;--%>
        <%--margin-bottom: 30px;--%>

    <%--}--%>
    <%--tr{--%>
        <%--font-size: 120%;--%>
    <%--}--%>

<%--</style>--%>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Spectrums</title>
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <%--<link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">--%>
        <!--        <link href="assets/css/font-awesome.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="assets/css/custom.css">
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <link rel="icon" href="http://www.veryicon.com/icon/ico/System/Multipurpose%20Alphabet/Letter%20M%20orange.ico">
    </head>
    <body>

        <div class="row row-fix">
            <div class="col-md-offset-1 col-md-10">
                <!--                <div class="text-center">-->
                <h1 class="text-center">Spectrum</h1>
                <h3 class="text-center">Спектр</h3>
                <!--                </div>-->

                <div class="panel panel-default">
                    <div class="panel-body">

                        <div class="col-md-2 col-sm-6 col-xs-6">
                            <a href="exp" class="btn btn-default">
                                <i class="fa fa-angle-left fa-lg"></i> Back
                            </a>
                        </div>
                        <div class="col-md-1 col-sm-6 co-xs-6 text-right">
                            <a href="exp?action=results-to-excel&expid=${param.expid}" class="btn btn-default">
                                Export to excel file <i class="fa fa-download"></i>
                            </a>
                        </div>

                        <form action = "exp" class="col-md-offset-5 col-md-4 col-lg-offset-6 col-lg-3 text-center" style="margin-top: 5px">
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
                            <input type="hidden" name="action" value="${param.action}"/>
                            <input type="hidden" name="expid" value="${param.expid}"/>
                        </form>
                            <%--
                        <div class="col-md-4">
                            <form action="/exp" method="get">
                                <div class="input-group">
                                    <input type="number" class="form-control" name="freq" min="0" max="10E50"
                                           placeholder="Search by frequncy" value="${param.freq}">
                                    <input type="hidden" name="action" value="search-by-freq"/>
                                    <input type="hidden" name="expid" value="expid"/>
                                    <span class="input-group-btn">
                                        <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                    </span>
                                </div>
                            </form>
                        </div>

                            --%>
                    </div>
                </div>

                <%--
<c:choose>
<c:when test="${sessionScope.user == null}"> 
    <p class="text-center">
        Hi, quest! Would you like to 
        <a href="controller?action=login&page=${param.page}&paginationstep=${param.paginationstep}">login</a> 
or <a href="controller?action=register&page=${param.page}&paginationstep=${param.paginationstep}">register</a> 
    </p> 
</c:when>
<c:otherwise>
    <p class="text-center">
        Hi, ${sessionScope.user.name} 
        (<a href="controller?action=logout&page=${param.page}&paginationstep=${param.paginationstep}">logout</a>)
    </p>
</c:otherwise>
</c:choose>
                --%>

                <c:choose>
                    <c:when test="${not empty requestScope.spectrum}">
                        <table class="table table-striped tableborder col-md-10 text-center">
                            <thead>
                            <th class="text-center">
                                Frequency, Hz
                            <div class="smalltext">Частота, Гц</div>
                            </th>
                            <th class="text-center">
                                Magnitude, dB
                            <div class="smalltext">Величина, дБ</div>
                            </th>
                            </thead>

                            <tbody>
                                <c:forEach var = "array" items = "${requestScope.spectrum}">
                                    <tr>
                                        <td>${array.frequency}</td>
                                        <td>${array.magnitude}</td>
                                    </tr>
                                </c:forEach> 
                            </tbody>
                        </table>


                    </c:when>
                    <c:otherwise>
                        <h1 class="text-center">No Results Found</h1>
                    </c:otherwise>
                </c:choose>
                        
                <div class=" col-md-12">
                    <%@ include file="../partials/pagination.jspf" %>
                </div>
            </div>
        </div>
        <script src="assets/js/jquery-1.11.2.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

    </body>
</html>
