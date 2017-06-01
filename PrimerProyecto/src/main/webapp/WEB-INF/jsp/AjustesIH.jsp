<%-- 
    Document   : AjustesIH
    Created on : 31/05/2017, 07:35:05 PM
    Author     : jchav
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
                <form method="submit" action="/PrimerProyecto/PerfilIH">
                    <button> Regresar </button>
                </form>
        </div>     
        </div>
       
</body>
</html>
