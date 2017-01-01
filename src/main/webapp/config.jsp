<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>Files</title>
</head>
<body>
<jsp:useBean id="propReader" scope="application" class="com.company.foo.PropertyReader"/>
<c:set var="files" value="${propReader.getFiles()}" scope="application"/>

<h3>Found <c:out value="${files.size()}"/> file(s)</h3>
<ul>
    <c:forEach var="file" items="${files}">
        <c:url value="configview.jsp" var="url">
            <c:param name="name" value="${file.name}"/>
        </c:url>
        <li><a href="${url}">${file.name}</a></li>
    </c:forEach>
</ul>
<br/>
</body>
</html>