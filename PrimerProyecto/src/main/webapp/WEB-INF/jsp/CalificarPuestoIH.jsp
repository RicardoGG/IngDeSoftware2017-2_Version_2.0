<%-- 
    Document   : crearPuesto
    Created on : 5/04/2017, 09:05:38 PM
    Author     : Manuel & Servando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Calificar Puesto</title>
    </head>
    <body>
        <h1>Calificar</h1>
        <form method="POST" action="/PrimerProyecto/calificarPuesto2">
            <!--Nombre: <br> <input id="name" name="nombre" type="text" placeholder="Nombre"/><br>-->
            <h3>${puesto}</h3>
            <input type="HIDDEN" name="puesto" value="${puesto}">
            <br>
            Calificacion: <br> <input id="calificacion" name="Calificacion" type="text" placeholder="Da una calificacion" /><br>
            <br>
            <br>
            Comentario: <br> <input id="comentario" name="Comentario" type="text" placeholder="Escribe un comentario"/>
            <br>
            <br>
            <button>Aceptar</button>
        </form>
    </body>
