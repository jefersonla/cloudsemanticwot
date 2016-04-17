<%@taglib uri="http://java.sun.com/jsp/jstl/fmt"  prefix="fmt"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!-- Header -->
<header id="header">
    <a href="index.html" class="title"></a>
    <nav>
        <ul>
            <li><a href="<c:url value="/"/>">Home</a></li>
        </ul>
    </nav>
</header>