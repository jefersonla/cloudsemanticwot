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
<customTags:page_generic bodyClass="Install gateway" title="Install gateway">
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

        <!-- install -->
        <section  class="wrapper style2 fade-up">
            <div class="inner" id="install">
                <h2>Install WoT Gateway</h2>
                <p>Edit text.</p>
                <div class="split style1">
                    <section>
                        <div style="color: darkorange">

                            ${info}
                        </div>
                        <!--commandName tem que ser o mesmo nome da po**a do parametro do metodo form-->                
                        <img id="loading" style="
                             display: none; 
                             position:relative;
                             top:50%;
                             left:50%;
                             margin-top:-50px;
                             margin-left:-50px;" 
                             src="<c:url value="/resources/images/gears.svg"/>"/>
                        <form:form id="form_gateway" servletRelativeAction="/gateway"
                                   method="post" 
                                   commandName="gateway" 
                                   enctype="multipart/form-data">
                            <div class="field">
                                <label for="ip">IP:</label>
                                <form:input path="ip" 
                                            pattern="((^|\.)((25[0-5])|(2[0-4]\d)|(1\d\d)|([1-9]?\d))){4}$" 
                                            placeholder="0.0.0.0/24"
                                            type="text" required="true"/>
                                <form:errors path="ip"/>
                            </div>
                            <div class="field">
                                <label for="user">User:</label>
                                <form:input path="user" type="text" required="true"/>
                                <form:errors path="user"/>
                            </div>
                            <div class="field">
                                <label for="password">Password:</label>
                                <form:input path="password" 
                                            type="password" required="true"/>
                                <form:errors path="password"/>
                            </div>
                            <div class="field">
                                <label for="port">Port</label>
                                <form:input path="port"
                                            placeholder="22" required="true"/>
                                <form:errors path="port"/>
                            </div>
                            <ul class="actions">
                                <li><input id="submit_loading" type="submit" value="Save">
                                <li><input type="reset" value="Reset">
                                <li><a class="button" href="<c:url value="/#install"/>">Back</a></li>
                                <li><a class="button special" href="<c:url value="/docs"/>">Tutorial</a></li>
                            </ul>
                        </form:form>
                    </section>
                    <section>
                        <ul class="contact">
                            <li>
                                <h3>Info</h3>
                                <span>12345 Somewhere Road #654<br />
                                    Nashville, TN 00000-0000<br />
                                    USA</span>
                            </li>
                        </ul>
                    </section>
                </div>
            </div>
        </section>

    </div>

</jsp:body>


</customTags:page_generic>
