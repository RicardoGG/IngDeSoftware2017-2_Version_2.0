<%-- 
    Document   : VerComentariosVisIH
    Created on : 30/05/2017, 06:58:28 PM
    Author     : diego
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="css/general_style.css">
        <link rel="stylesheet" type="text/css" href="css/verPuestoDetalle.css">
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Comentarios</title>
    </head>
    
    <body style="height: 100%;" >
        <div class="left">
        </div>
        <div class="middle bgimage1">
            <div class="element" style="background-color: rgba(49,54,76,0.95)">
                <div class="first_section_2">
                    <h1 class="center" style="color:white">Comentarios para ${nombre}</h1>
                </div>
                <div class="second_section_2">
                    <img style="height: 100%; width: 100%;object-fit: contain" src="${mapa}">
                </div>
            </div>
            <c:forEach var="comentario" items="${comentarios}">
                <div class="element">
                    <div class="first_section_3" style="background-color:whitesmoke">
                        <pre><font size="4" style="font-family: verdana;"><B>${comentario.persona.nombre} ${comentario.persona.apPaterno} ${comentario.persona.apMaterno}</B>  dijo:</font></pre>
                        <pre><font size="4" style="font-family: courier;">${comentario.comentario}</font></pre>
                    </div >
                    <div class="second_section_3">
                        <img src="css/BlackCat.png" style="height: 100%; width: 100%;object-fit: contain"/>
                    </div>
                    <br>    
                </div>
                
            </c:forEach>            
        </div>
        <div class="right">
        </div>
        </div>
    </body>
</html>
