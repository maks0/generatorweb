<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%-- 
    Document   : Spectrum
    Created on : 15.04.2015, 17:04:24
    Author     : maks
--%>

<!DOCTYPE html>
<style type="text/css">
    thead{
        font-style: italic;
        background-color: paleturquoise;
        /*        border: 1px blue solid;*/
    }
    .tableborder{
        border: 1px #d9edf7 solid;
    }
    .smalltext{
        font-size: 75%
    }
    html {
        height: 100%;
        width: 97%;
    }
    body {
        font-family: "Open Sans","Helvetica Neue",Arial,sans-serif;
        height: 100%;
        margin-bottom: 30px;
        
    }
    tr{
        font-size: 120%;
    }
    .footer {
        position: absolute;
        bottom: 0;
        width: 100%;
        /* Set the fixed height of the footer here */
        height: 30px;
        width: 99%;
        background-color: #d9edf7;
    }

</style>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Spectrums</title>
        <!--        <link href="assets/css/bootstrap.min.css" rel="stylesheet">-->
        <link rel="stylesheet" href="//netdna.bootstrapcdn.com/bootstrap/3.3.2/css/bootstrap.min.css">
        <!--        <link href="assets/css/font-awesome.min.css" rel="stylesheet">-->
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <link rel="icon" href="http://www.veryicon.com/icon/ico/System/Multipurpose%20Alphabet/Letter%20M%20orange.ico">
    </head>
    <body>

        <div class="row row-fix">
            <div class="col-md-offset-1 col-md-10 ">
                <!--                <div class="text-center">-->
                <h1 class="text-center">
                    Experiments
                </h1>
                <h3 class="text-center">Експерименти</h3>
                <!--                </div>-->

                <div class="panel panel-default">
                    <div class="panel-body">
                        <div class="col-md-1">
                            <a href="exp?action=exp-to-xls" class="btn btn-default">
                                Export to excel file <i class="fa fa-download"></i>
                            </a>
                        </div>
                        <!--                        <div class="col-md-offset-7 col-md-4">
                                                    <form action="/admin/group" method="get">
                                                        <div class="input-group">
                                                            <input type="text" class="form-control" name="groupname" placeholder="Search by group name" value="${param.groupname}">
                                                            <input type="hidden" name="action" value="search">
                                                            <span class="input-group-btn">
                                                                <button class="btn btn-default" type="submit"><i class="fa fa-search"></i></button>
                                                            </span>
                                                        </div>
                                                    </form>
                                                </div>-->


                        <form action = "exp" class="col-md-offset-8 col-md-2" >
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

                <!--        <form action="controller" method="post">
                            <p>
                                <input type="file" name="inputedfile"/>
                                <input type="submit" value="send">
                            </p>
                        </form>-->



                <table class="table table-striped tableborder col-md-10 text-center">
                    <thead>
                    <th class="col-md-2 text-center">
                        Start date and time
                        <div class="smalltext">Початок експеременту</div>
                    </th>
                    <th class="col-md-2 text-center">
                        Measurement Device
                        <div class="smalltext">Вимірювальний пристрій</div>
                    </th>
                    <th class="col-md-5 text-center">
                        Comment
                        <div class="smalltext">Коментар</div>
                    </th>
                    <th class="col-md-1 text-center smalltext">
                        Показати результат
                    </th>
                    </thead>

                    <tbody>
                        <c:forEach var = "array" items = "${requestScope.experiments}">
                            <tr>
                                <td>                                
                                    <fmt:formatDate type="both" 
                                                    dateStyle="short" timeStyle="short" 
                                                    value="${array.beginTime}" />
                                </td>

                                <td>${array.measurementDeviceModel}</td>
                                <td>${array.comment}</td>
                                <td> 
                                    <a href="exp?action=results&expid=${array.id}" class="btn btn-default">View results</a>
                                </td>
                            </tr>
                        </c:forEach> 
                    </tbody>
                </table>

                <div class="col-md-offset-1 col-md-10">
                    <%@ include file="../partials/pagination.jspf" %>
                </div>



            </div>
        </div>
        <%--        <footer class="footer">
                    <%@ include file="../partials/footer.jspf" %>
        </footer>--%>

        <script src="assets/js/jquery-1.11.2.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>

    </body>
</html>
