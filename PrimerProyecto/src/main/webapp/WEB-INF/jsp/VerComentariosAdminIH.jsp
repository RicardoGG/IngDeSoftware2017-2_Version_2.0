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
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>JSP Page</title>
    </head>
    <body class="bgimage">
        <div class="header">
            <p class="title_page">Modificando puesto</p>
        </div>
        <div style="height: 90%;width: 100%; position: relative">
        <div class="sidemenu">
                <img class="logo" src="css/logo.png">

                <form method="submit" action="/PrimerProyecto/CrearPuestoIH">
                    <br>
                    <button>Crear Puesto</button>
                    <br>
                </form>

                <form method="submit" action="/PrimerProyecto/LeerPuestoIH">
                    <br>
                    <button>Ver Puestos</button>
                    <br>
                </form>
                
                <form method="submit" action="/PrimerProyecto/verComentariosAdmin">
                    <br>
                    <button>Ver Comentarios</button>
                    <br>
                </form>

                <form method ="POST" action="/PrimerProyecto/cerrarSesion">
                    <br>
                    <button> Cerrar Sesión</button>
                    <br>
                </form>
        </div>     
        <div class="content">
        <div class="titles">
        <h1>Comentarios</h1>
            <c:forEach var="comentario" items="${comentarios}">
                <pre><font size="4" style="font-family: verdana;"><B>${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}<br>${comentario.persona.correo}<br>Comento en:</B> ${comentario.puesto.idNombre}</font></pre>
                <pre><font size="4" style="font-family: courier;"><i>${comentario.comentario}</i></font></pre>
                
                <form method="POST" action="/PrimerProyecto/eliminarComentario">
                    <input type="HIDDEN" name="persona" value="${comentario.persona.correo}">
                    <input type="HIDDEN" name="puesto" value="${comentario.puesto.idNombre}">
                    <input type="HIDDEN" name="comentario" value="${comentario.comentario}">
                    <button name="delete">Eliminar</button>
                </form>

                <br>
            </c:forEach>
        </div>
        </div>
        </div>
    </body>
</html>
