<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <jsp:include page="head.jsp">
        <jsp:param name="title" value="Chat" />
    </jsp:include>

<body>
    <jsp:include page="header.jsp">
        <jsp:param name="title" value="Chat" />
    </jsp:include>

    <main>
        <c:if test="${user != null}">

            <h2 id="status"></h2>
            <div>
                <label for="statustext">Status</label>
                <input type="text" id="statustext"/>
                <input type="button" id="button" value="Change Status" />
            </div>
        </c:if>
    </main>

    <jsp:include page="footer.jsp">
        <jsp:param name="title" value="Home" />
    </jsp:include>
</body>

<script type="text/javascript" src="js/script.js"></script>
</html>