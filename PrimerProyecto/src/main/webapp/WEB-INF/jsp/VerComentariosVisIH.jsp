<%-- 
    Document   : VerComentariosVisIH
    Created on : 30/05/2017, 06:58:28 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/general_style.css">
        <link rel="stylesheet" type="text/css" href="css/verPuestoDetalle.css">
        <title>Comentarios</title>
    </head>
    
    <body class="bgimage1">
        <div class="left">
        </div>
        <div class="middle">
            <h1>Comentarios para ${nombre}</h1>
            <c:forEach var="comentario" items="${comentarios}">
                <pre><font size="4" style="font-family: verdana;"><B>${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}</B>  dijo:</font></pre>
                <pre><font size="4" style="font-family: courier;">${comentario.comentario}</font></pre>
                <br>
            </c:forEach>            
        </div>
        <div class="right">
        </div>
        </div>
    </body>
</html>
