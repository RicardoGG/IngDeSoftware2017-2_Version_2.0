<%-- 
    Document   : AdministradorIH
    Created on : 20/04/2017, 09:03:48 AM
    Author     : jchav
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Administrador</title>
            <link rel="stylesheet" type="text/css" href="css/administrador.css">
    </head>
    
    <body>
        <div class="box">
            <p class="titles" <h1>Lista de Puestos:</h1> </p></div>  
        <div class="blur">
            <div class="zoom">    <body class="bgcolor" style="display: table">
        <form method="submit" action="/PrimerProyecto/ActualizarPuestoIH">
        <c:forEach var="puesto" items="${p}">
             ${puesto.nombre}>
            <button>Actualizar</button> 
        </form></div>
        </c:forEach></div>
            
    </body>
</html>
