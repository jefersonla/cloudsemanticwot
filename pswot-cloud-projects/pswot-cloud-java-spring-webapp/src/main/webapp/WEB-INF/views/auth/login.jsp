<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<!DOCTYPE HTML>
<!--
        Hyperspace by HTML5 UP
        html5up.net | @n33co
        Free for personal and commercial use under the CCA 3.0 license (html5up.net/license)
-->
<html>
    <head>
        <title>Generic - Hyperspace by HTML5 UP</title>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1" />
        <!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
        <link rel="stylesheet" href="<c:url value="/resources/assets/css/main.css"/>" />
        <!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
        <!--[if lte IE 8]><link rel="stylesheet" href="assets/css/ie8.css" /><![endif]-->
    </head>
    <body>

        <!-- Header 
        <header id="header">
            <a href="index.html" class="title">PSWoT: A Platform as a Service for Semantic Web of Things applications.</a>
            <nav>
                <ul>
                </ul>
            </nav>
        </header>-->

        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Main -->
            <section id="main" class="wrapper">
                <div class="inner">
                    <div class="split style1">
                        <section>
                            <img src="<c:url value="/resources/images/logo_pswot.png"/>" />

                            <c:if test="${not empty SPRING_SECURITY_LAST_EXCEPTION}">
                                <div style="color: red">
                                    Your login attempt was not successful, try again.<br /> 
                                    Caused : 
                                    ${sessionScope["SPRING_SECURITY_LAST_EXCEPTION"].message}
                                </div>
                            </c:if>
                            <div style="color: green">

                                ${info}
                            </div>

                            <form:form servletRelativeAction="/login">
                                <div class="field">
                                    <label for="name">User:</label>
                                    <input type='email' required placeholder="Email" name='username' value=''>
                                </div>
                                <div class="field">
                                    <label for="email">Password:</label>
                                    <input type='password' required name='password' />
                                </div>
                                <ul class="actions">
                                    <li><input class="button fit" name="submit" type="submit"
                                               value="Login" /></li>
                                    <li><a href="<c:url value="/user/form"/>" class="button fit">Register</a></li>
                                </ul>
                            </form:form>
                        </section>
                        <section>
                            <ul class="contact">
                                <li>
                                    <h3>About</h3>
                                    <span>PSWoT: A Platform as a Service for Semantic Web of Things applications. System developed as part of the Master's thesis in Computer Science at the Federal University of Bahia.</span>
                                </li>
                                <li>
                                    <h3>Email</h3>
                                    <a href="#">nailtonjr@dcc.ufba.br</a>
                                </li>
                                <li>
                                    <h3>Social</h3>
                                    <ul class="icons">
                                        <li><a href="#" class="fa-facebook"><span class="label">Facebook Wiser Group</span></a></li>
                                        <li><a href="#" class="fa-github"><span class="label">GitHub</span></a></li>

                                        <li><a href="#" class="fa-linkedin"><span class="label">LinkedIn</span></a></li>
                                    </ul>
                                </li>
                            </ul>
                        </section>
                    </div>

                </div>
            </section>

        </div>

        <!-- Footer -->
        <footer id="footer" class="wrapper alt">
            <div class="inner">
                <ul class="menu">
                    <li>&copy; Untitled. All rights reserved.</li><li>Design: <a href="http://html5up.net">HTML5 UP</a></li>
                </ul>
            </div>
        </footer>

        <!-- Scripts -->
        <script src="<c:url value="/resources/assets/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/skel.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrolly.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrollex.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/main.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/util.js"/>"></script>

    </body>
</html>