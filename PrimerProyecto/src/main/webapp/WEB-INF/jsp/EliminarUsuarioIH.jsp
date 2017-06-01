<%-- 
    Document   : EliminarUsuarioIH
    Created on : 31/05/2017, 07:36:00 PM
    Author     : Manuel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title> Configuracion de la Cuenta </title>
    </head>
     <body class="bgimage">
        <div class="header">
            <p class="title_page">Configuracion</p>
        </div>
        <div style="height: 90%;width: 100%; position: relative">
        <div class="sidemenu">
                <img class="logo" src="css/logo.png">

                <form method="submit" action="/PrimerProyecto/ModificarUsuarioIH">
                    <button> Modificar Datos del Usuario </button>
                </form>

                <form method="submit" action="/PrimerProyecto/EliminarUsuarioIH">
                    <button> Eliminar Cuenta </button>
                </form>
         <body>
         <div class="content">
            <h2> ¿Estás seguro de eliminar tu cuenta? </h2>
        
            <form method ="POST" action="/PrimerProyecto/eliminarUsuarioAdministrador1IH">
                    <input type="HIDDEN" name="usuario" value="${usuario.correo_us}">
                    <button> Eliminar Cuenta </button>
                    <br>
            </form>
            <form method="POST" action="/PrimerProyecto/cancelar2">
                <button class="login_button"style="color: white;">Cancelar</button>
            </form>
        </div>
    </body>
</html>
