<%-- 
    Document   : inicio
    Created on : 5/04/2017, 02:12:14 PM
    Author     : Manuel & Servando
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Pagina de Inicio</title>
        <link rel="stylesheet" type="text/css" href="css/Inicio.css">
    </head>
    <div class="blur">
    <body class="bgcolor">
        <div class="box">
        <form method="POST" action="/PrimerProyecto/login">
            <h1>Iniciar Sesion</h1>
            <input id="nombre2" name ="correo" type="text" placeholder="Correo"><br>
            <input id="nombre2" name ="password" type="text" placeholder="Contrasena"><br>
            <button>Aceptar</button>
        </form>
        
        <br>
        <h1>Registro</h1>
        <form method="submit" action="/PrimerProyecto/registrarse">
            <button>Registrarse</button>
        </form>
        
        
        <form method="submit" action="/PrimerProyecto/verInformacion">
            <h1>Iniciar como Visitante</h1>
            <button>Aceptar</button>
        </form>    
        </div>
        
    </body>
    </div>
</html>
