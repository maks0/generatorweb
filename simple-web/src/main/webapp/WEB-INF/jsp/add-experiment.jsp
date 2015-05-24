<%-- 
    Document   : add-experiment
    Created on : 24.05.2015, 12:48:47
    Author     : maks
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Experiment</title>
        <link rel="icon" href="http://www.veryicon.com/icon/ico/System/Multipurpose%20Alphabet/Letter%20M%20orange.ico">

    </head>
    <body>
        <h1 class="text-center">Add Experiment Form</h1>

        <form class=" col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8 panel panel-default" method="post" action="add" enctype="multipart/form-data">
            <div class="panel-body col-md-offset-1 col-md-10 col-lg-offset-2 col-lg-8 panel">
                <div class="control-group col-sm-12">
                    <label class="control-label col-md-6 text-right" for="begin">
                        Begin of experiment: 
                        <br/> 
                        Початок експерименту:
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="begin" name="begin" placeholder="Click here to choose date"
                               class="form-control" value="${param.begin}" required>
                    </div>
                </div>


                <div class="control-group col-sm-12">
                    <label class="control-label col-md-6 text-right" for="device">
                        Measurement device model: 
                        <br/> 
                        Модель вимірювального пристрою:
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="device" name="device" placeholder="Device model" maxlength="100" required
                               class="form-control" value="${param.device}">
                    </div>
                </div>
                                    
                    <div class="control-group col-sm-12">
                    <label class="control-label col-md-6 text-right" for="comment">
                        Comment: 
                        <br/> 
                        Коментар:
                    </label>
                    <div class="col-md-6">
                        <input type="text" id="comment" name="comment" placeholder="Comment" maxlength="250" 
                               class="form-control" value="${param.comment}">
                    </div>
                </div>
                                   
                    <div class="control-group col-sm-12">
                    <label class="control-label col-md-6 text-right" for="file">
                        File: 
                        <br/> 
                        Файл:
                    </label>
                    <div class="col-md-6">
                        <input type="file" id="file" name="file" placeholder="Device model" required
                               class="form-control" value="${param.file}">
                    </div>
                </div>

                <input class="btn btn-primary col-xs-offset-2 col-xs-8" type="submit" value="Ok">
            </div>
        </form>



        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <!--            <link href="assets/css/signin.css" rel="stylesheet">-->
        <!--                <link href="assets/css/font-awesome.min.css" rel="stylesheet">-->
        <link href="//netdna.bootstrapcdn.com/font-awesome/4.0.3/css/font-awesome.css" rel="stylesheet">
        <script src="assets/js/jquery-1.11.2.min.js"></script>
        <script src="assets/js/bootstrap.min.js"></script>
        <link href="assets/css/anytime.5.1.0.css"
              rel="stylesheet">
        <script src="assets/js/anytime.5.1.0.js"></script>


        <script>
            AnyTime.picker("begin", {
                format: "%H-%i_%d-%m-%Y",
                firstDOW: 1
            });
        </script>
    </body>

</html>
