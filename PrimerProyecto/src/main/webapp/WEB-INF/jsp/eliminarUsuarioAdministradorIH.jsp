<%-- 
    Document   : eliminarUsuarioAdministradorIH
    Created on : 22/05/2017, 12:31:48 AM
    Author     : Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Usuarios</title>
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
                
                <form method ="POST" action="/PrimerProyecto/eliminarUsuarioAdministradorIH">
                    <br>
                    <button>Ver Usuarios</button>
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
        <c:forEach var="usuario" items="${usuarios}">
            <pre>   ${usuario.nombre} ${usuario.apPaterno} ${usuario.apMaterno}</pre> 
            <pre>   ${usuario.correo}</pre>
            <form method="POST" action="/PrimerProyecto/eliminarUsuarioAdministrador1IH">
                <input type="HIDDEN" name="correo" value="${usuario.correo}">
                <button>Eliminar Usuario</button>
            </form>
        </c:forEach>
        </div>
        </div>
        </div
    </body>
</html>
