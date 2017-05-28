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
        <!--<h2>-->
            <c:forEach var="comentario" items="${comentarios}">
                <pre><font size="6">${comentario.comentario}</font> <font size="3">    escrito por:    </font>  <font size="6">${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}</font></pre>
                
                <br>
            </c:forEach>
        <!--</h2>-->
    </body>
</html>
