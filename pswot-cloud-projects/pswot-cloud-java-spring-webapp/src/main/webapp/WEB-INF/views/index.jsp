<%-- 
    Document   : index
    Created on : Sep 5, 2015, 12:45:25 AM
    Author     : nailton
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c"%>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib tagdir="/WEB-INF/tags" prefix="customTags"%>
<customTags:page_left_menu bodyClass="" title="PSWoT">
    <jsp:attribute name="extraScripts">
        <!-- Scripts -->
        <!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
        <script src="<c:url value="/resources/assets/js/jquery.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/skel.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrolly.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/jquery.scrollex.min.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/main.js"/>"></script>
        <script src="<c:url value="/resources/assets/js/util.js"/>"></script>
    </jsp:attribute>
    <jsp:body>
        <!-- Wrapper -->
        <div id="wrapper">

            <!-- Intro -->
            <section id="intro" class="wrapper style1 fullscreen fade-up">
                <div class="inner">
                    <h1>Welcome to PSWoT <sec:authorize access="isAuthenticated()">
                            <sec:authentication property="principal" var="user"/>
                            <spring:message code="users.welcome" arguments="${user.name}"/>
                        </sec:authorize></h1>
                    <p>PSWoT: A Platform as a Service for Semantic Web of Things applications.<br />
                        A platform designed to facilitate the creation and implementation of semantic applications for the Web of Things.</p>

                    <sec:authorize access="!isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->
                        <sec:authentication property="principal" var="user"/> <!-- Pega dados do usuário -->                
                        <iframe width="854" height="480" src="https://www.youtube.com/embed/P7_gNgMf2Sk" frameborder="0" allowfullscreen></iframe>
                    </sec:authorize>
                    <ul class="actions">
                        <li><a href="#one" class="button special fit scrolly">More tutorial videos</a></li>
                    </ul>
                </div>
            </section>
            <sec:authorize access="!isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->
                <!-- One -->
                <section id="one" class="wrapper style2 spotlights">
                    <section>
                        <!--<a href="#" class="image"><img src="<c:url value="/resources/images/node-red.png"/>" alt="" data-position="center center" /></a>-->
                        <div class="content">
                            <div class="inner">
                                <h2>Discovery semantic services and store semantic data</h2>
                                <p>Discover services and stored data in a Triple Data Store automatically, through nodes to Node-RED. <b>For example</b>, with a simple drag and drop you can discover all temperature sensors in an location, calculate the average between them, store your data, and access them with SPARQL queries.</p>

                                <ul class="actions">
                                    <li><a href="#" class="button">View the documents</a></li>
                                </ul>
                            </div>
                        </div>
                    </section>
                    <section>
                        <!--<a href="#" class="image"><img src="<c:url value="/resources/images/logo_arduino.png"/>" alt="" data-position="top center" /></a>-->
                        <div class="content">
                            <div class="inner">
                                <h2>Automatically provide the device data on the Web</h2>
                                <p>Automatic deployment of RESTFul services to devices on your local network. With Avahi, a Zeroconf implementation, yours gateway can discover the type of device. <b>For example</b>, when connecting the device on your network, a service and compatible driver will be found in the PSWoT cloud and made available on your local gateway, so the data be available through Web protocols.</p>
                                <ul class="actions">
                                    <li><a href="#" class="button">View the documents</a></li>
                                </ul>
                            </div>
                        </div>
                    </section>
                    <section>
                        <!--<a href="#" class="image"><img style="background-color: #ffffff" src="<c:url value="/resources/images/docker-logo.png"/>" alt="" data-position="25% 25%" /></a>-->
                        <div class="content">
                            <div class="inner">
                                <h2>Simple install with Docker</h2>
                                <p>With some simple Docker commands you can install e configure a PSWoT gateway in your Raspberry PI and the PSWoT Cloud in your remote server.</p>
                                <ul class="actions">
                                    <li><a href="#" class="button">View the documents</a></li>
                                </ul>
                            </div>
                        </div>
                    </section>
                </section>

                <!-- Two -->
                <section id="two" class="wrapper style3 fade-up">
                    <div class="inner">
                        <h2>Technologies used</h2>
                        <p>Phasellus convallis elit id ullamcorper pulvinar. Duis aliquam turpis mauris, eu ultricies erat malesuada quis. Aliquam dapibus, lacus eget hendrerit bibendum, urna est aliquam sem, sit amet imperdiet est velit quis lorem.</p>
                        <div class="features">
                            <section>
                                <span class="icon major fa-desktop"></span>
                                <h3>Avahi</h3>
                                <p>Avahi is a system which facilitates service discovery on a local network via the mDNS/DNS-SD protocol suite. This enables you to plug your laptop or computer into a network and instantly be able to view other people who you can chat with, find printers to print to or find files being shared.</p>
                                <ul class="actions">
                                    <li><a href="http://www.avahi.org/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-code"></span>
                                <h3>Swagger</h3>
                                <p>Swagger is a simple yet powerful representation of your RESTful API. With the largest ecosystem of API tooling on the planet, thousands of developers are supporting Swagger in almost every modern programming language and deployment environment.</p>
                                <ul class="actions">
                                    <li><a href="http://swagger.io/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-cog"></span>
                                <h3>iServe</h3>
                                <p>iServe is what we refer to as service warehouse which unifies service publication, analysis, and discovery through the use of lightweight semantics as well as advanced discovery and analytic capabilities.</p>
                                <ul class="actions">
                                    <li><a href="http://iserve.kmi.open.ac.uk/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-archive"></span>
                                <h3>MQTT</h3>
                                <p>MQTT is a machine-to-machine (M2M)/"Internet of Things" connectivity protocol. It was designed as an extremely lightweight publish/subscribe messaging transport.</p>
                                <ul class="actions">
                                    <li><a href="http://mqtt.org/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-lock"></span>
                                <h3>Apache Karaf</h3>
                                <p>Apache Karaf is a modern and polymorphic container.
                                    It's a lightweight, powerful, and enterprise ready container powered by OSGi.</p>
                                <ul class="actions">
                                    <li><a href="http://karaf.apache.org/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-chain"></span>
                                <h3>Fuseki</h3>
                                <p>Fuseki is a SPARQL server. It provides REST-style SPARQL HTTP Update, SPARQL Query, and SPARQL Update using the SPARQL protocol over HTTP.</p>
                                <ul class="actions">
                                    <li><a href="https://jena.apache.org/documentation/serving_data/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-coffee"></span>
                                <h3>Jena</h3>
                                <p>Apache Jena (or Jena in short) is a free and open source Java framework for building semantic web and Linked Data applications.</p>
                                <ul class="actions">
                                    <li><a href="https://jena.apache.org/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                            <section>
                                <span class="icon major fa-diamond"></span>
                                <h3>OWL Ontologies</h3>
                                <p>The OWL Web Ontology Language is designed for use by applications that need to process the content of information instead of just presenting information to humans.</p>
                                <ul class="actions">
                                    <li><a href="http://www.w3.org/TR/owl-features/" class="button">Learn more</a></li>
                                </ul>
                            </section>
                        </div>

                        <ul class="actions">
                            <li><a href="<c:url value="/user/form"/>" class="button big fit special">Register</a></li>
                        </ul>

                        <!--<ul class="actions">
                            <li><a href="#" class="button">Learn more</a></li>
                        </ul>-->
                    </div>
                </section>
            </sec:authorize>


            <sec:authorize access="isAuthenticated()"> <!-- Define quem pode ver esse conteudo -->

                <!-- Colocar o formulário para installar o gateway -->

                <sec:authentication property="principal" var="user"/> <!-- Pega dados do usuário -->    

                <!-- install -->
                <section  class="wrapper style2 fade-up">
                    <div class="inner" id="install">
                        <h2>Install WoT Gateway</h2>
                        <p>Edit text.</p>
                        <div style="color: greenyellow; padding-bottom: 10px;">

                            ${info}
                        </div>
                        <div class="split style1">
                            <section>
                                <ul class="actions">
                                        <li><a class="button special" href="<c:url value="/gateway/form"/>">Auto install with SSH</a></li>
                                        <li><a class="button" href="<c:url value="/docs"/>">Manual install</a></li>
                                    </ul>
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

                <!-- Three -->
                <section id="three" class="wrapper style1 fade-up">
                    <div class="inner">
                        <h2>My SWoT APP</h2>
                        <p>Edit text.</p>
                        <div class="style1">
                            <section>
                                <div class="table-wrapper">
                                    <table class="alt">
                                        <thead>
                                            <tr>
                                                <th>Name</th>
                                                <th>Description</th>
                                                <th>Last update</th>
                                            </tr>
                                        </thead>
                                        <tbody> <!-- Iterar aqui com as APPs do usuário -->
                                            <tr>
                                                <td>${swotapplication.name}</td>
                                                <td>${swotapplication.description}</td>
                                                <!--<td>${swotapplication
                                                          .releaseDate.time}</td>-->
                                                <td><fmt:formatDate 
                                                        value="${swotapplication
                                                                 .releaseDate.
                                                                 time}" /></td>
                                            </tr>
                                        </tbody>
                                        <tfoot>
                                        </tfoot>
                                    </table>
                                </div>
                                <ul class="actions"> <!-- Aqui ir para a página de criar APP, lá vai aparecer 3 campos de formulário em cima, um com o nome,outra com a descrição e em baixo pedindo o exporte do script do node-red, depois aparece o frame do node-red em baixo-->
                                    <li>
                                        <a href="<c:url value="/application/form"/>" class="button fit icon fa-edit">Edit SWoTAPP</a></li>
                                    <li>
                                        <a href="<c:url value="/application/deploy"/>" class="button fit icon fa-play">Start SWoTAPP</a></li>
                                    <li> <a href="<c:url value="/application/stop"/>" class="button fit icon fa-stop">Stop SWoTAPP</a></li>
                                </ul>
                            </section>
                            <!--<section>
                                <ul class="contact">
                                    <li>
                                        <h3>Address</h3>
                                        <span>12345 Somewhere Road #654<br />
                                            Nashville, TN 00000-0000<br />
                                            USA</span>
                                    </li>
                                    <li>
                                        <h3>Email</h3>
                                        <a href="#">user@untitled.tld</a>
                                    </li>
                                    <li>
                                        <h3>Phone</h3>
                                        <span>(000) 000-0000</span>
                                    </li>
                                    <li>
                                        <h3>Social</h3>
                                        <ul class="icons">
                                            <li><a href="#" class="fa-twitter"><span class="label">Twitter</span></a></li>
                                            <li><a href="#" class="fa-facebook"><span class="label">Facebook</span></a></li>
                                            <li><a href="#" class="fa-github"><span class="label">GitHub</span></a></li>
                                            <li><a href="#" class="fa-instagram"><span class="label">Instagram</span></a></li>
                                            <li><a href="#" class="fa-linkedin"><span class="label">LinkedIn</span></a></li>
                                        </ul>
                                    </li>
                                </ul>
                            </section>-->
                        </div>
                    </div>
                </section>
            </sec:authorize>

        </div>
    </jsp:body>

</customTags:page_left_menu>
