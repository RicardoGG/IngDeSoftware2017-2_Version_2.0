    <%-- 
    Document   : verinformacionpuesto
    Created on : 5/04/2017, 08:58:16 PM
    Author     : Manuel & Servando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Ver Informacion</title>

        <style type="text/css">
            html, body{ height: 100%; margin: 0; padding: 0;}
            #map {height: 100%; top: 0%; margin-left: 25%; margin-right: 25%; margin-top: 2%; margin-bottom: 2% }
        </style>

    </head>
    <body>
        <h1>Puestos:</h1>
        <form method ="POST" action="/PrimerProyecto/calificacionPuesto">
            <button>Calificar Puesto</button>
        </form>
         <form method ="POST" action="/PrimerProyecto/cancelar">
            <button>Volver a pantalla de Administrador</button>
        </form>
        <!--<form action="." oninput="range_control_value.value = range_control.valueAsNumber">-->
        <h2>
            <c:forEach var="puesto" items="${puestos}">
                <B>Nombre: </B>${puesto.idNombre}<br>
                <B>Ubicacion: </B>${puesto.ubicacion}<br>
                <B>Calificacion:</B>${puesto.calificacion}<br>
                    <form method="POST" action="/PrimerProyecto/verComentariosAdmin">
                        <input type="HIDDEN" name="comentariosDe" value="${puesto.idNombre}">
                        <button>Ver Comentarios</button>
                    </form>
                <br>
            </c:forEach>
        </h2>
        <!--</form>-->


    </body>
</html>