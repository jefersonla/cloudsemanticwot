<%-- 
    Document   : left_menu
    Created on : Jan 9, 2016, 1:19:18 PM
    Author     : nailton
--%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!-- Sidebar -->
<section id="sidebar">
    <div class="inner">
        <nav>
            <ul>
                <li><img src="<c:url value="/resources/images/logo_pswot.png"/>" /></li>
                <li><a href="#intro">Welcome</a></li>
                <sec:authorize access="isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->      <li><a href="#install">Install WoT Gateway</a></li>
                    <li><a href="#three">My SWoT APP</a></li>
                    <li><a href="<c:url value="/user/form"/>">Settings</a></li>
                    <li><a href="<c:url value="/logout"/>">Logout</a></li>
                </sec:authorize>

                <sec:authorize access="!isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->
                    <sec:authentication property="principal" var="user"/> <!-- Pega dados do usuário -->                
                    <li><a href="#one">Features</a></li>
                    <li><a href="#two">Technologies used</a></li>
                    <li><a href="#three">Docs</a></li>
                    <!--<li><a target="_blank" href="https://github.com/nailtonvieira/cloudsemanticwot">Github project</a></li>-->
                    <li><a href="<c:url value="/login"/>">Login</a></li>
                </sec:authorize>
            </ul>
        </nav>
    </div>
</section>
