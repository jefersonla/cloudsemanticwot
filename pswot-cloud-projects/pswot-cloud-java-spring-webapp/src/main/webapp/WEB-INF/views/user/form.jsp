<%-- 
    Document   : form
    Created on : Jan 8, 2016, 9:49:30 PM
    Author     : nailton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<customTags:page_generic bodyClass="Register user" title="Register user">
    <jsp:attribute name="extraScripts">
        <!-- Scripts -->
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="<c:url value="/resources/assets/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/skel.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrolly.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrollex.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/main.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/util.js"/>"></script>
        
        <script>
            $("form:first").submit(function () {
                $("#loading").show();
                $("form:first").hide();
            });

        </script>
        
    </jsp:attribute>
    <jsp:body>

        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Main -->
            <section id="main" class="wrapper">
                <div class="inner">
                    <h2>Create PSWoT user</h2>
                    <p>Edit text.</p>
                    <div class="split style1">
                        <section>
                            <div style="color: red">

                                ${info}
                            </div>
                            <img id="loading" style="
                             display: none; 
                             position:relative;
                             top:50%;
                             left:50%;
                             margin-top:-50px;
                             margin-left:-50px;" 
                             src="<c:url value="/resources/images/gears.svg"/>"/>
                            <form:form servletRelativeAction="/user" method="post" 
                                       commandName="user" enctype="multipart/form-data">
                                <div class="field">
                                    <label for="login">Email:</label>
                                    <form:input path="login" placeholder="you@mail.com" required="true"/>
                                    <form:errors path="login"/>
                                </div>
                                <div class="field">
                                    <label for="password">Password:</label>
                                    <form:input path="password" type="password" required="true"/>
                                    <form:errors path="password"/>
                                </div>
                                <div class="field">
                                    <label for="name">Name:</label>
                                    <form:input path="name" required="true"/>
                                    <form:errors path="name"/>
                                </div>
                                <div class="field">
                                    <label for="perfilstatus">Your services will be public?</label>
                                    <form:radiobuttons path="perfilstatus" 
                                                       items="${enums}" />
                                    <form:errors path="perfilstatus"/>
                                </div>
                                <ul class="actions">
                                    <li><input type="submit" value="Save">
                                    <li><input type="reset" value="Reset">   
                                    <li><a class="button" href="<c:url value="/"/>">Back</a></li>
                                </ul>
                            </form:form>
                        </section>
                        <section>
                            <ul class="contact">
                                <li>
                                    <h3>Info</h3>
                                    <span>You will receive an email confirmation and informations about the terms of tool use.</span>
                                </li>
                            </ul>
                        </section>
                    </div>

                </div>
            </section>

        </div>

    </jsp:body>


</customTags:page_generic>
