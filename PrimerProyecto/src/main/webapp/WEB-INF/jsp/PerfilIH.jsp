<%-- 
    Document   : perfil
    Created on : 5/04/2017, 09:29:10 PM
    Author     : Manuel & Servando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/general_style.css">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <link rel="stylesheet" type="text/css" href="css/verInformacion.css">
        <link rel="stylesheet" type="text/css" href="css/verPuestoDetalle.css">
        <title>Perfil Usuario</title>
    </head>
    <body>
        <div class="left">
        
        </div>
        <div class="middle bgimage">
           
            <h1 style="text-aling:center">Bienvenido</h1> 
            <form method ="POST" action="/PrimerProyecto/cerrarSesion">
                <button style="background-color: lightcyan; text-align: center"> Cerrar Sesión</button>
            </form>
            <!--<form method="POST" action="/PrimerProyecto/AjustesIH">
                <button> Configuracion de la cuenta </button>
            </form>-->
            <form method ="POST" action="/PrimerProyecto/verInfoRegistrado">
                <button  style="background-color: lightcyan; text-align: center"> Ver informacion de puestos</button>
            </form>
                <h2> 
                    <br> ${nombre}
                    <br> ${apellidoPat}
                    <br> ${apellidoMat}
                    <br> ${correo}
                    </form>
                </h2>
            
        </div>
        <div class="right">
            
        </div>
    </body>
</html>
