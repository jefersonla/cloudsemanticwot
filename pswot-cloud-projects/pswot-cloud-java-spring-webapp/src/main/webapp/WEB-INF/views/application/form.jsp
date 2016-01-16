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
<customTags:page_generic bodyClass="" title="Create application">
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
            
            $("#nodered_button").click(function(){
                var iframe = document.getElementById("iframenode");
                iframe.src = iframe.src;
            });

        </script>
    </jsp:attribute>
    <jsp:body>
        
        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Main -->
            <section id="main" class="wrapper style5">
                <div class="inner">
                    <h2>Create SWoT Application</h2>
                    <p>Edit text.</p>
                    <div class="split style1">
                        <section id="done">
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
                            <form:form servletRelativeAction="/application" method="post" 
                                       commandName="swotApplicationForm" enctype="multipart/form-data">
                                <div class="field">
                                    <label for="name">Name:</label>
                                    <form:input path="name" placeholder="My application" required="true"/>
                                    <form:errors path="name"/>
                                </div>
                                <div class="field">
                                    <label for="description">Description:</label>
                                    <form:input path="description" placeholder="What makes my application?" required="true"/>
                                    <form:errors path="description"/>
                                </div>
                                <ul class="actions">
                                    <li><input type="submit" value="Save">
                                    <li><input type="reset" value="Reset">
                                    <li><a class="button" href="<c:url value="/#three"/>">Back</a></li>
                                    <li><a class="button special" href="<c:url value="/docs"/>">Tutorial</a></li>
                                </ul>
                            </form:form>
                        </section>
                        <section>
                            <ul class="contact">
                                <li>
                                    <h3>Info</h3>
                                    <span style="color: deeppink">${infonode}</span>

                                    <br/>
                                    <br/>
                                    <a id="nodered_button" class="button fit special" href="<c:url value="#nodered"/>">Reload Node-RED</a>
                                </li>
                            </ul>
                        </section>
                    </div>

                </div>
            </section>

            <!-- Node-RED -->
            <section id="main" class="wrapper">
                <div class="inner">
                    <div>
                        <section id="nodered">

                            <iframe id="iframenode" width="100%" height="800" 
                                    src="http://localhost:${noderedport}/" frameborder="0" allowfullscreen></iframe>
                            <a class="button fit" href="<c:url value="#done"/>">Back to form</a>
                        </section>
                    </div>

                </div>
            </section>

        </div>
        <div class="modal"><!-- Place at bottom of page --></div>

    </jsp:body>


</customTags:page_generic>
