<%-- 
    Document   : verComentario
    Created on : 23/05/2017, 06:58:37 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Comentarios</title>
    </head>
    <body>
        <h1>Comentarios de: ${nombre}</h1>
        <h2>
            <c:forEach var="comentario" items="${comentarios}">
                <B>${comentario.comentario} escrito por:  ${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}</B>
                <br>
                <br>
            </c:forEach>
        </h2>
    </body>
</html>
