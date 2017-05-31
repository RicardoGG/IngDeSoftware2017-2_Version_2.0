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
        <title>Crear Puesto</title>
    </head>
    <body class="bgimage">
        <div class="header">
            <p class="title_page">Crear Puesto</p>
        </div>
        <div style="height: 90%;width: 100%; position: relative">
            <div class="sidemenu">
                    <img class="logo" src="css/logo.png">

                    <form method="submit" action="/PrimerProyecto/CrearPuestoIH">
                        <br>
                        <button>Crear Puesto</button>
                        <br>
                    </form>

                    <form method="submit" action="/PrimerProyecto/LeerPuestoIH">
                        <br>
                        <button>Ver Puestos</button>
                        <br>
                    </form>
                    
                    <form method ="POST" action="/PrimerProyecto/eliminarUsuarioAdministradorIH">
                        <br>
                        <button>Ver Usuarios</button>
                        <br>
                    </form>
                    
                    <form method="submit" action="/PrimerProyecto/verComentariosAdmin">
                        <br>
                        <button>Ver Comentarios</button>
                        <br>
                    </form>

                    <form method ="POST" action="/PrimerProyecto/cerrarSesion">
                        <br>
                        <button> Cerrar Sesión</button>
                        <br>
                    </form>
            </div>     
            <div class="content">
                <form method="POST" action="/PrimerProyecto/formularioPuesto">
                    <form action="." oniput="range_control_value.value = range_control.valueAsNumber"/>
                    <div class="titles">
                        <div id="form_create_1"> Nombre: <br> <input id="name" name="nombre" type="text" placeholder="Nombre"/><br> </div>
                        <div id="form_create_2" > Ubicacion: <br> <input id="ubicacion" name="ubicacion" readonly="readonly" type="text" placeholder="Ubicacion" /><br> </div>
                        <div id="form_create_3" > Abre a las: 
                            <select>
                                    <option value="06:00">06:00</option>
                                    <option value="07:00">07:00</option>
                                    <option value="08:00">08:00</option>
                                    <option value="09:00">09:00</option>
                                    <option value="10:00">10:00</option>
                                    <option value="11:00">11:00</option>
                                    <option value="12:00">12:00</option>
                                    <option value="13:00">13:00</option>
                                    <option value="14:00">14:00</option>
                                    <option value="15:00">15:00</option>
                                    <option value="16:00">16:00</option>
                                    <option value="17:00">17:00</option>
                                    <option value="18:00">18:00</option>
                                    <option value="19:00">19:00</option>
                                    <option value="20:00">20:00</option>
                                    <option value="21:00">21:00</option>
                            </select> 
                        </div>
                        <div id="form_create_3" > Cierra a las: <br> <input id="open_at" type="text" placeholder="Ingrese hora" /><br> </div>
                    </div>
                    <button id="button_create_f" style="color: white">Aceptar</button>
                </form>
                <div id = "map" class="map">
            </div>
        </div>
           <script type="text/javascript">
                var map;
                var markers = [];
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
                    deleteMarkers();
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
                markers.push(marker);
                var lat = marker.getPosition().lat();
                var lng = marker.getPosition().lng();
                var cordinate = lat + "," + lng;
                document.getElementById("ubicacion").value = cordinate;

             }
             
                   // Sets the map on all markers in the array.
            function setMapOnAll(map) {
                for (var i = 0; i < markers.length; i++) {
                    markers[i].setMap(map);
                }
            }

            // Removes the markers from the map, but keeps them in the array.
            function clearMarkers() {
                setMapOnAll(null);
            }
            // Deletes all markers in the array by removing references to them.
            function deleteMarkers() {
              clearMarkers();
              markers = [];
            }
             
            new google.maps.event.addDomListener(window, 'load', initialize);
            </script>

            <script type="text/javascript"
                    src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDvfkQSaSfeL20ShaYbhIFZd9n63xAxqSg&sensor=false&callback=initMap">
            </script>




    </body>
