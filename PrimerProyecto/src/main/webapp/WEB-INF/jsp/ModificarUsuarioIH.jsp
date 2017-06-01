<%-- 
    Document   : ModificarUsuarioIH
    Created on : 31/05/2017, 07:35:48 PM
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

                <form method="submit" action="/PrimerProyecto/actualizarUsuario">
                    <button> Eliminar Cuenta </button>
                </form>
                <input type="hidden" name="correoViejo" value="${correo_us}">
                <div id="form_create_1"> Nombre nuevo<br>
                <input id="name" name ="nombre" type="text" placeholder = "${nombre}"><br></div>
                <div id="form_create_2" > Apellido Paterno <br> 
                <input id="apPaterno" name ="paterno" type="text" placeholder = "${apPaterno}"><br></div>
                <div id="form_create_3"> Apellido Materno<br>
                <input id="apMaterno" name ="materno" type="text" placeholder = "${apMaterno}"><br></div>
                <div id="form_create_4" > Correo <br> 
                <input id="correo" name ="email" type="text" placeholder = "${correo}"><br></div>
                <div id="form_create_5" > Contraseña <br> 
                <input id="contrasena" name ="password" type="text" placeholder = "${Contrasena}"><br></div>
        </div>
            <button id="button_modify_p1" style="color: white;">Modificar</button>
        
            <form method="POST" action="/PrimerProyecto/AjustesIH">
                <button id="button_modify_p2"style="color: white;">Cancelar</button>
            </form>
    </body>
</html>
