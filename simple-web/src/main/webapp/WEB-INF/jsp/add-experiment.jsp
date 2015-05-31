<%-- 
    Document   : add-experiment
    Created on : 24.05.2015, 12:48:47
    Author     : maks
--%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Experiment</title>
        <link rel="icon" href="http://www.veryicon.com/icon/ico/System/Multipurpose%20Alphabet/Letter%20M%20orange.ico">
        <link href="assets/css/custom.css" rel="stylesheet">
    </head>
    <body>
        <h1 class="text-center">Add Experiment Form</h1>
        <h3 class="text-center">Додати Експеримент</h3>

        <c:if test="${not empty errorMessage}">
            <div class="row row-fix">
                <div class="col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                    <div class="alert alert-danger alert-dismissible text-center" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                        ${errorMessage}
                    </div>
                </div>
            </div>
        </c:if>
        <c:if test="${not empty infoMessage}">
            <div class="row row-fix">
                <div class="col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                    <div class="alert alert-info alert-dismissible text-center" role="alert">
                        <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                            ${infoMessage}
                    </div>
                </div>
            </div>
        </c:if>

        <div class="row row-fix" id="wait_msg" style="visibility: hidden">
            <div class="col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">
                <div class="alert alert-info alert-dismissible text-center" role="alert">
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    Please, wait while system save your data.
                </div>
            </div>
        </div>


        <form class=" col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8 panel panel-default"
              method="post" action="add" enctype="multipart/form-data" id="form">
            <div class="panel-body col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8">



                <div class="col-xs-12">
                    <p class="text-center" >
                        <a id="show_model" style="visibility: visible">To add new device click here (Натисніть тут щоб додати новий пристрій)</a>
                    </p>


                    <div class="control-group" style="visibility: hidden" id="model">
                    <label class="control-label col-sm-6 text-right" for="device">
                        <div>Measurement device model:</div>
                        <div>Модель вимірювального пристрою:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="text" id="device" name="device" placeholder="Device model (isn't mandatory)" maxlength="100"
                               class="form-control" value="${param.device}" minlength="3">
                    </div>
                    <div class="help-block"></div>
                        </div>
                </div>

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="device_sn">
                        <div>Measurement device serial number:</div>
                        <div>Серійний номер вимірювального пристрою:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="text" id="device_sn" name="device_sn" placeholder="Device SN" maxlength="100" required
                               class="form-control" value="${param.device_sn}" minlength="3" autofocus>
                    </div>
                    <div class="help-block"></div>
                </div>

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="begin">
                        <div>Begin of experiment:</div>
                        <div>Початок експерименту:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="text" id="begin" name="begin" placeholder="Click here to choose date"
                               class="form-control" value="${param.begin}">
                    </div>
                </div>

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="comment">
                        <div>Comment:</div>
                        <div>Коментар:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="text" id="comment" name="comment" placeholder="Comment" maxlength="250" 
                               class="form-control" value="${param.comment}">
                    </div>
                </div>
                                   

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="file">
                        <div>File:</div>
                        <div>Файл:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="file" id="file" name="file" placeholder="Data file" required
                               class="form-control" value="${param.file}" style="height: auto">
                    </div>
                </div>

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="input_email">
                        <div>Your email:</div>
                        <div>Ваша адреса електронної пошти:</div>
                    </label>

                    <div class="col-sm-6">
                        <input type="email" data-validation-email-message="Incorrect email address. Неправильна адреса."
                               class="form-control" id="input_email" placeholder="Email" name="email"
                               minlength="5" data-validation-minlength-message="Incorrect email address. Неправильна адреса."
                               maxlength="40" required value="${param.email}"
                               data-validation-maxlength-message="Incorrect email address. Неправильна адреса. (It's too long)"/>
                        <p class="help-block text-warning"></p>
                    </div>

                </div>

                <div class="control-group col-xs-12">
                    <label class="control-label col-sm-6 text-right" for="inputPassword">
                        <div>Password:</div>
                        <div>Пароль:</div>
                    </label>
                    <div class="col-sm-6">
                        <input type="password" id="inputPassword" name="password" class="form-control"
                               placeholder="Password" required  maxlength="60">
                    </div>
                </div>


                <input class="btn btn-primary col-xs-offset-2 col-xs-8" type="submit" id="ok" onclick="show()" value="Ok">
            </div>
        </form>



        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!--            <link href="assets/css/signin.css" rel="stylesheet">-->
        <!--                <link href="assets/css/font-awesome.min.css" rel="stylesheet">-->
        <%--<link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">--%>
        <link href="assets/css/font-awesome.min.css" rel="stylesheet">
        <script src="assets/js/jquery-1.11.2.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <script src="assets/js/jqBootstrapValidation.js"></script>
        <script>
            $(function () {
                $("input").not("[type=submit]").jqBootstrapValidation();
            });
        </script>
        <script src="assets/js/custom.js"></script>

        <script>
//            $('#button').click(function() {
//                $('#wait_msg').css('visibility', 'visible');
//            });
            $('#show_model').click(function() {
                $('#model').css('visibility', 'visible');
                $('#show_model').css('visibility', 'hidden');
            });
            $('#form').submit(function() {
                $('#wait_msg').css('visibility', 'visible');
            });
        </script>




        <link href="assets/css/anytime.5.1.0.css"
              rel="stylesheet">
        <script src="assets/js/anytime.5.1.0.js"></script>


        <script>
            AnyTime.picker("begin", {
                latest: new Date(),
                format: "%H-%i_%d-%m-%Y",
                firstDOW: 1
            });
        </script>
    </body>

</html>
