<%-- 
    Document   : editarComentarioIH
    Created on : 28/05/2017, 06:18:24 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Editar Comentario</title>
    </head>
    <body>
        <form method="POST" action="/PrimerProyecto/guardarComentario">
            <input name="comentario_nuevo" value="${comentario}">
            <input type="HIDDEN" name="comentario_viejo" value="${comentariol}">
            <input type="HIDDEN" name="persona" value="${email}">
            <input type="HIDDEN" name="puesto" value="${puesto}">
            <button>Aceptar</button>
        </form>
    </body>
</html>
