<%-- 
    Document   : LeerPuesto
    Created on : 20/04/2017, 08:01:31 AM
    Author     : Manuel & Servando
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Puestos</title>
    </head>
    <body class="bgimage">
        <div class="header">
            <p class="title_page">Puestos</p>
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
                <div class="scroll">
                <div class="titles">
                    <pre><h2 style="text-align:center;"> Puestos registrados</h2></pre>
                    <c:forEach var="puesto" items="${puestos}">
                        <div class="element">
                            <div class="first_section">
                                <p class="center">${puesto.idNombre}</p>
                            </div>
                            <div class="second_section">
                                <img style="height: 100%; width: 100%;object-fit: contain" src="${puesto.getUrl()}">
                            </div>
                            <div class="third_section">
                                <form method="POST" action="/PrimerProyecto/editPuesto">
                                    <input type="HIDDEN" name="puesto" value="${puesto.idNombre}">
                                    <button class="boton1">Modificar Puesto</button>
                                </form>
                                <form method="POST" action="/PrimerProyecto/eliminarPuesto">
                                    <input type="HIDDEN" name="puesto" value="${puesto.idNombre}">
                                    <button class="boton2">Eliminar Puesto</button>
                                </form>
                            </div>
                        </div>
                    </c:forEach>
                </div>
                </div>
            </div>
             
        </div>
        
    </body>
</html>
