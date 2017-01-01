<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <title>File contents</title>
</head>
    <body>
<jsp:useBean id="propReader" scope="application" class="com.company.foo.PropertyReader"/>
<c:set var="file" value="${propReader.getFile(param.name)}"/>

        <a href="config.jsp">Back</a>
        <br/>
        <h3>Viewing <c:out value="${file.toString()}"/></h3>
        <br/>
        <code>
            <pre>
              <c:out value="${file.getContents()}"/>
            </pre>
        </code>
    </body>
</html>