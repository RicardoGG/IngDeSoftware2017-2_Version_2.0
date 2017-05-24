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
        <link rel="stylesheet" type="text/css" href="css/admin.css">
        <title>Calificar Puesto</title>
    </head>
    <body class="bgimage">
        <div class="header">
            <p class="title_page">Crear Puesto</p>
        </div>
        <div style="height: 90%;width: 100%; position: relative">
            <div class="sidemenu">
                    <img class="logo" src="css/logo.png">

                    <form method="submit" action="/PrimerProyecto/crearPuesto">
                        <button>Crear Puesto</button>
                    </form>

                    <form method="submit" action="/PrimerProyecto/leerPuesto">
                        <button>Ver Puestos</button>
                    </form>

                    <form method="submit" action="/PrimerProyecto/modificarPuesto">
                        <button>Modificar Puesto</button>
                    </form>

                    <form method="POST" action="/PrimerProyecto/elimPuest">
                        <button>Eliminar Puesto</button>
                    </form>

                    <form method ="POST" action="/PrimerProyecto/cerrarSesion">
                        <button> Cerrar Sesión</button>
                    </form>
            </div>     
            <div class="content">
                <form method="POST" action="/PrimerProyecto/formularioPuesto">
                    <form action="." oniput="range_control_value.value = range_control.valueAsNumber"/>
                    <div class="titles">
                        <div id="form_create_1"> Nombre: <br> <input id="name" name="nombre" type="text" placeholder="Nombre"/><br> </div>
                        <div id="form_create_2"> Ubicacion: <br> <input id="ubicacion" name="ubicacion" type="text" placeholder="Ubicacion" /><br> </div>
                    </div>
                    <button id="button_create_f" style="color: white">Aceptar</button>
                </form>
                <div id = "map" class="map">
            </div>
        </div>
           <script type="text/javascript">
            var map;
            function initMap() {
                map = new google.maps.Map(document.getElementById('map'), {
                    center: {lat: 19.323909, lng: -99.179915},
                    zoom: 19,
                    mapTypeId: 'roadmap',
                    heading: 270,
                    tilt: 45,
                    streetViewControl: false,
                    scrollwheel: false,
                    rotateControl: false,
                    labels: true
                });
                google.maps.event.addListener(map, 'click', function(event) {
                    addMarker(event.latLng, map);
                });
                  
            }
            
            function addMarker(location, map) {
                // Add the marker at the clicked location, and add the next-available label
                // from the array of alphabetical characters.
                var marker = new google.maps.Marker({
                  position: location,
                  label: "Ubicación",
                  map: map
                });
             }
            google.maps.event.addDomListener(window, 'load', initialize);
            </script>
            
            <script type="text/javascript"
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDvfkQSaSfeL20ShaYbhIFZd9n63xAxqSg&sensor=false&callback=initMap">
            </script>
            
            
    
    
    </body>
