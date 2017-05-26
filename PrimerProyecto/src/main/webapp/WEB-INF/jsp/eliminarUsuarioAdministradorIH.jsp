<%-- 
    Document   : eliminarUsuarioAdministradorIH
    Created on : 22/05/2017, 12:31:48 AM
    Author     : Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eliminar Usuario</title>
    </head>
    <body>
        <c:forEach var="usuario" items="${usuarios}">
                  <B >Correo: </B>${usuario.correo_us}<br>
        </c:forEach>
        <form method="POST" action="/PrimerProyecto/eliminarUsuarioAdministrador1IH">
                    <p class="title_page">Correo del usuario a eliminar</p>
                    <input class="login_input1" id="nombre2" name ="usuario" type="text" placeholder="Correo_us"><br>
                    <button>Aceptar</button>
        </form> 
    </body>
</html>
