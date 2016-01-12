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
            setTimeout(function () {
                var iframe = document.getElementById("iframenode");
                iframe.src = iframe.src;
            }, 5000);

        </script>

        <script>
            $body = $("main");

            $(document).on({
                ajaxStart: function () {
                    $body.addClass("loading");
                },
                ajaxStop: function () {
                    $body.removeClass("loading");
                }
            });
        </script>
    </jsp:attribute>
    <jsp:body>
        <style>
            /* Start by setting display:none to make this hidden.
   Then we position it in relation to the viewport window
   with position:fixed. Width, height, top and left speak
   for themselves. Background we set to 80% white with
   our animation centered, and no-repeating */
            .modal {
                display:    none;
                position:   fixed;
                z-index:    1000;
                top:        0;
                left:       0;
                height:     100%;
                width:      100%;
                background: rgba( 255, 255, 255, .8 ) 
                    url('http://i.stack.imgur.com/FhHRx.gif') 
                    50% 50% 
                    no-repeat;
            }

            /* When the body has the loading class, we turn
               the scrollbar off with overflow:hidden */
            body.loading {
                overflow: hidden;   
            }

            /* Anytime the body has the loading class, our
               modal element will be visible */
            body.loading .modal {
                display: block;
            }
        </style>

        <!-- Wrapper -->
        <div id="wrapper">
            <!-- Main -->
            <section id="main" class="wrapper style5">
                <div class="inner">
                    <div class="split style1">
                        <section id="done">
                            <div style="color: red">

                                ${info}
                            </div>
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
                                    <a class="button fit special" href="<c:url value="#nodered"/>">Node-RED Editor</a>
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
