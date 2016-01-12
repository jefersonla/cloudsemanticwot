<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<header id="layout-header">
    <div class="clearfix container">
        <a href="/" id="logo"> 
        </a>
        <div id="header-content">
            <nav id="main-nav">
                <ul class="clearfix">

                    <li><a href="${spring:mvcUrl('SCC#items').build()}" rel="nofollow">Seu carrinho (${shoppingCart.quantity}) </a></li>

                    <li><a href="/pages/sobre-a-casa-do-codigo" rel="nofollow">Sobre
                            nós </a></li>

                    <li><a href="/pages/perguntas-frequentes" rel="nofollow">Perguntas
                            Frequentes </a></li>

                    <li>
                        <a href="<c:url value="/produtos?locale=pt"/>">Português</a>
                    </li>		
                    <li>
                        <a href="<c:url value="/produtos?locale=en_US"/>">Inglês</a>
                    </li>		

                </ul>
            </nav>
        </div>
    </div>
</header>
<!-- Sidebar -->
        <section id="sidebar">
            <div class="inner">
                <nav>
                    <ul>
                        <li><img src="https://raw.githubusercontent.com/nailtonvieira/cloudsemanticwot/master/others/README-Elements/logo.png"/></li>
                        <li><a href="#intro">Welcome</a></li>
                        <sec:authorize access="isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->
                            <li><a href="#three">My SWoT APPs</a></li>
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