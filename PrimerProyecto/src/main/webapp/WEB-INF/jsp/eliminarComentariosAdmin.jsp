<%-- 
    Document   : eliminarComentariosAdmin
    Created on : 25/05/2017, 06:41:51 PM
    Author     : rgarciag
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Comentario</title>
    </head>
    <body>
        <h1>Comentario a eliminar</h1>
        <form method="POST" action="/PrimerProyecto/eliminarComentario">
            Usuario:<br> <input id="usr" name="usuario" type="text" placeholder="Usuario"/><br>
            Comentario: <br> <input id="comentario" name="comentario" type="text" placeholder="Escribe un comentario a eliminar"/><br>
            Puesto:<br> <input id ="puesto" name ="puesto" type="text" placeholder="Nombre del puesto donde esta el comentario"/><br>
            <button> Eliminar</button>
        </form>
    </body>
</html>
