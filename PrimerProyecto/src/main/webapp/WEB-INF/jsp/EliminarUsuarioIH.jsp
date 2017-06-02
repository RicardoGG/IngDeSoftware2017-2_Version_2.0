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

         
         <div class="content">
            <div class="scroll">    
                <div class="titles">
                        <div class="element">
                            <div class="first_section_2" >
                                <pre style="color:black">   ${usuario.nombre} ${usuario.apPaterno} ${usuario.apMaterno}</pre> 
                                <pre style="color:black">   ${usuario.correo}</pre>                                
                            </div>
                            <div class="second_section_2">
                                <form method="POST" action="/PrimerProyecto/eliminarUsuarioAdministrador1IH">
                                    <input type="HIDDEN" name="correo" value="${usuario.correo}">
                                    <button class="boton2 center" style="bottom:0; margin: 0" > Eliminar Cuenta </button>
                                </form>                             
                            </div>
                        </div>
                </div>
            </div>
        </div>
    </body>
</html>
