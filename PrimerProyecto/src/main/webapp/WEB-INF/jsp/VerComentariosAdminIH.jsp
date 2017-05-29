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
        <h1>Comentarios para ${nombre}</h1>
            <c:forEach var="comentario" items="${comentarios}">
                    <form method ="POST" action="/PrimerProyecto/cancelar">
                        <button>Volver a pantalla de Administrador</button>
                    </form>
                <pre><font size="4" style="font-family: verdana;"><B>${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}<br>${comentario.persona.correo}<br></B>dijo:</font></pre>
                <pre><font size="4" style="font-family: courier;">${comentario.comentario}</font></pre>
                
                <form method="POST" action="/PrimerProyecto/eliminarComentario">
                    <input type="HIDDEN" name="persona" value="${comentario.persona.correo}">
                    <input type="HIDDEN" name="puesto" value="${comentario.puesto.idNombre}">
                    <input type="HIDDEN" name="comentario" value="${comentario.comentario}">
                    <button name="delete">Eliminar</button>
                </form>

                <br>
                <br>
                <br>
            </c:forEach>
    </body>
</html>
