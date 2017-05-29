package Controlador;

import Mapeo.Calificar;
import Mapeo.Persona;
import Mapeo.Puesto;
import Mapeo.Usuario;
import Mapeo.Vender;
import Modelo.CalificarDAO;
import Modelo.PersonaDAO;
import Modelo.PuestoDAO;
import Modelo.UsuarioDAO;
import Modelo.VenderDAO;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
/**
 *
 * @author diego
 * @version 2.0
 */
@Controller
public class Controlador {

    @Autowired
    UsuarioDAO usuario;

    @Autowired
    PersonaDAO persona;

    @Autowired
    PuestoDAO puesto;
    
    @Autowired
    VenderDAO vender;
    
    @Autowired
    CalificarDAO calificar;

    String user;

    String edit_puesto;

    // Expresion regular que verifica el correo.
    private final String PATTERN_EMAIL = "^[\\w-]+(\\.[\\w-]+)*@ciencias.unam.mx$";

    /** Carga la pagina de inicio
     * 
     * @return el nombre del archivo de la pantalla de inicio 
     */
    @RequestMapping(value = "/")
    public String PantallaDeInicioIH() {
        return "PantallaDeInicioIH";
    }

    /**
     * 
     * @param model - el modelo a usar
     * @param request -la solicitud
     * @return la vista con los puestos cargados en la base de datos
     */
    @RequestMapping(value = "/verInformacion", method = RequestMethod.GET)
    public ModelAndView verInformacionPuesto(ModelMap model, HttpServletRequest request) {
        String wrong;
        List<Puesto> puestos_registrados = puesto.list_puestos();

        if (puestos_registrados == null) {
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }

        model.addAttribute("puestos", puestos_registrados);

        return new ModelAndView("VerInformacionPuestoIH", model);
    }

    /**
     * Redireccion para el formulario de registro
     * @param model
     * @param request
     * @return la pagina del formulario de registro
     */
    @RequestMapping(value = "/RegistrarseIH", method = RequestMethod.GET)
    public ModelAndView registrarse(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("RegistrarseIH", model);
    }

    /**
     * Redireccion al formulario de creacion de puestos
     * @param model
     * @param request
     * @return la pagina del formulario de creacion de puestos
     */
    @RequestMapping(value = "/CrearPuestoIH", method = RequestMethod.GET)
    public ModelAndView creaPuest(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("CrearPuestoIH", model);
    }

    /**
     * Muestra un listado de los puestos en la base de datos
     * @param model
     * @param request
     * @return la pagina con la lista de puestos en la base de datos
     */
    @RequestMapping(value = "/LeerPuestoIH", method = RequestMethod.GET)
    public ModelAndView LeerPuestoIH(ModelMap model, HttpServletRequest request) {
        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();

        if (puestos_registrados == null) {
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }

        model.addAttribute("puestos", puestos_registrados);

        return new ModelAndView("LeerPuestoIH", model);
    }

    /**
     * Redireccion al formulario de eliminacion de puestos
     * @param model - el modelo
     * @param request -la solicitud
     * @return la pagina del formulario de eliminacion de puestos 
     */
    @RequestMapping(value = "/EliminarPuestoIH", method = RequestMethod.POST)
    public ModelAndView elimPuest(ModelMap model, HttpServletRequest request) {
        List<Puesto> puest = puesto.list_puestos();
        String wrong = "";
        if (puest == null) {
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }
        model.addAttribute("puestos", puest);
        return new ModelAndView("EliminarPuestoIH", model);
    }

    /**
     * Metodo para iniciar sesion
     * @param model
     * @param request
     * @return el perfil del usuario
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(ModelMap model, HttpServletRequest request) {
        String email = request.getParameter("correo");
        String pas = request.getParameter("password");
        String wrong = "";
        Usuario u = usuario.getUser(email, pas);
        if (u == null) {
            wrong = "usuario no valido";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }
        Persona p = persona.getPersona(u.getCorreo_us(), pas);
        if (p == null) {
            wrong = "correo invalido, favor de verificarlo";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else if (pas.equals(p.getContrasenia())) {

            if (usuario.es_Admin(email).equals("1")) {
                List<Puesto> puestos_registrados = puesto.list_puestos();

                if (puestos_registrados == null) {
                    wrong = "Error al cargar la información.";
                    model.addAttribute("mensaje", wrong);
                    return new ModelAndView("ErrorIH", model);
                }

                model.addAttribute("puestos", puestos_registrados);
                return new ModelAndView("AdministradorIH", model);
            }

            user = email;
            
            String nombre = p.getNombre();
            String apellidoPat = p.getApPaterno();
            String apellidoMat = p.getApMaterno();
            String correo = p.getCorreo();

            model.addAttribute("nombre", nombre);
            model.addAttribute("apellidoPat", apellidoPat);
            model.addAttribute("apellidoMat", apellidoMat);
            model.addAttribute("correo", correo);
        } else {
            wrong = "La contraseña es incorrecta, favor de verificarla";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }
        return new ModelAndView("PerfilIH", model);
    }

    /**
     * Metodo para cerrar sesión
     *
     * @param model-el modelo
     * @param request-la solicitud
     * @param response-la respuesta
     * @return ModelAndView-la página de inicio
     * @throws javax.servlet.ServletException
     * @throws java.io.IOException
     */
    @RequestMapping(value = "/cerrarSesion", method = RequestMethod.POST)
    public ModelAndView cerrarSesion(ModelMap model, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession sesion = request.getSession(true);
        //Invalidamos la sesion y desvinculamos los objetos asociados a ella
        sesion.invalidate();
        return new ModelAndView("PantallaDeInicioIH", model);
    }

    /**
     * metodo para validar el dominio de una direccion email
     * @param correo -el correo a validar
     * @return true si la validacion coincide
     */
    private boolean valida_email(String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }
    
    
    /**
     * Formulario de registro
     * @param model
     * @param request
     * @return la pantalla de inicio
     */
    @RequestMapping(value = "/formulario", method = RequestMethod.POST)
    public ModelAndView registro(ModelMap model, HttpServletRequest request,MailSender mm) {
        Persona p;
        Usuario u;
        String wrong;

        String nombre = request.getParameter("nombre");
        String apPaterno = request.getParameter("paterno");
        String apMaterno = request.getParameter("materno");
        String correo = request.getParameter("email");
        String pass = request.getParameter("password");

        if (valida_email(correo) == false) {
            wrong = "Correo no valido. Debes usar un correo ciencias.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else {
            p = persona.usuario_registrado(correo);
            if (p != null) {
                wrong = "Usuario ya registrado.";
                model.addAttribute("mensaje", wrong);
                return new ModelAndView("ErrorIH", model);
            } else {
                p = new Persona(nombre, apPaterno, apMaterno, correo, pass);
                u = new Usuario(correo, "0");
                persona.insert(p);
                usuario.insert(u);
            }
        }
        return new ModelAndView("PantallaDeInicioIH", model);
    }

    /**
     * Metodo para crear puestos
     * @param model
     * @param request
     * @return la pagina de home del administrador
     */
    @RequestMapping(value = "/formularioPuesto", method = RequestMethod.POST)
    public ModelAndView creaPuesto(ModelMap model, HttpServletRequest request) {
        String nombre = request.getParameter("nombre");
        String ubicacion = request.getParameter("ubicacion");

        Puesto puest = null;
        String wrong = "";

        if (nombre.equals("")) {
            wrong = "El nombre del puesto no puede estar vacio favor de poner un nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else if (ubicacion.equals("")) {
            wrong = "La ubicacion no puede estar vacia, favor de poner un nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else {
            puest = puesto.verificaPuesto(nombre);
            if (puest != null) {
                wrong = "El puesto ya existe";
                model.addAttribute("mensaje", wrong);
                return new ModelAndView("ErrorIH", model);
            } else {
                puest = new Puesto(nombre, ubicacion, 0);
                puesto.insert(puest);
            }
        }

        return new ModelAndView("AdministradorIH", model);
    }

   /**
    * Metodo para eliminar puestos
    * @param model
    * @param request
    * @return el home del administrador
    */
    @RequestMapping(value = "/eliminarPuesto", method = RequestMethod.POST)
    public ModelAndView eliminarPuesto(ModelMap model, HttpServletRequest request) {
        String nombre = request.getParameter("puesto");

        Puesto puest = puesto.verificaPuesto(nombre);
        List<Vender> v = vender.buscar(nombre);
        String wrong = "";

        if (puest == null) {
            wrong = "El puesto no esta en la base de datos, favor de verificar el nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else {
            if(v.isEmpty()){
                puesto.delete(puest);
            }else{
                for(Vender ven: v)
                    vender.delete(ven);
                puesto.delete(puest);
            }
        }
        return new ModelAndView("AdministradorIH", model);

    }

    /**
     * Regresa a la vista del administrador.
     *
     * @param model
     * @param request
     * @return la vista del administrador.
     */
    @RequestMapping(value = "/cancelar", method = RequestMethod.POST)
    public ModelAndView cancelar(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("AdministradorIH", model);
    }

    /**
     * Regresa a la vista del administrador.
     *
     * @param model
     * @param request
     * @return la vista del puesto a modificar.
     */
    @RequestMapping(value = "/ModificarPuestoIH", method = RequestMethod.GET)
    public ModelAndView modificarPuesto(ModelMap model, HttpServletRequest request) {
        List<Puesto> puest = puesto.list_puestos();
        model.addAttribute("puestos", puest);
        return new ModelAndView("ModificarPuestoIH", model);
    }
    
    /**
     * Redireccion del boton a la pantalla para calificar los puestos
     * @param model
     * @param request
     * @return la pantalla para calificar un puesto
     */
    @RequestMapping(value="/calificacionPuesto", method = RequestMethod.POST)
    public ModelAndView calificarPuestoP(ModelMap model,HttpServletRequest request){
        return new ModelAndView("CalificarPuestoIH",model);
    }

    /**
     * Va a la vista con los datos del puesto.
     *
     * @param model
     * @param request
     * @return la vista del puesto a modificar.
     */
    @RequestMapping(value = "/editPuesto", method = RequestMethod.POST)
    public ModelAndView editPuesto(ModelMap model, HttpServletRequest request) {
        String nombre_puesto = request.getParameter("puesto");
        Puesto p = puesto.verificaPuesto(nombre_puesto);

        if (p == null) {
            String wrong = "Puesto no encontrado";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }

        String nombre = p.getIdNombre();
        String ubicacion = p.getUbicacion();
        int calificacion = p.getCalificacion();

        model.addAttribute("nombre", nombre);
        model.addAttribute("ubicacion", ubicacion);
        model.addAttribute("calificacion", calificacion);

        return new ModelAndView("actualizarPuestoIH", model);
    }
    
    
    /**
     * Metodo para calificar el puesto
     * @param model
     * @param request
     * @return la pantalla de inicio
     */
     @RequestMapping(value="/calificarPuesto2", method = RequestMethod.POST)
    public ModelAndView calificarPuesto(ModelMap model,HttpServletRequest request){
        String nombre = request.getParameter("nombre");
        String calificacion = request.getParameter("calificacion");
        String comentario = request.getParameter("comentario");
        
        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();
        
        if(puestos_registrados == null){
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }

        model.addAttribute("puestos", puestos_registrados);

        Puesto puest;
        Calificar cali;
        
        if(puesto.verificaPuesto(nombre) == null){
            wrong = "El del puesto no existe favor de poner un nombre valido";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }
        else if(nombre.equals("")){
            wrong = "El nombre del puesto no puede estar vacio favor de poner un nombre";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        } else{
            if(calificacion.equals("")){
                wrong = "Debes agregar una calificación.";
                model.addAttribute("mensaje",wrong);
                return new ModelAndView("ErrorIH",model);
            }
            else{
                if(isNumeric(calificacion) == true && Integer.parseInt(calificacion)>-1 && Integer.parseInt(calificacion)<11){
                int c = Integer.parseInt(calificacion);
                puest = puesto.verificaPuesto(nombre);
                int n = puest.getCalificacion();
                int califFinal = (c+n)/2;
                puest.setCalificacion(califFinal);
                
                Persona p = persona.getPersona_correo(user);
                cali = new Calificar(p, puest, comentario);
                calificar.insert(cali);

                puesto.update(puest);
                }else{
                    wrong = "Ocurrio un error al registrar tu calificacion, presiona la flecha de retorno en tu navegador para volver a intentarlo";
                    model.addAttribute("mensaje",wrong);
                    return new ModelAndView("ErrorIH",model);
                }
            }
        }

        return new ModelAndView("PerfilIH",model);
    }
    
    /**
     * Metodo auxiliar para verificar si una cadena representa un valor numerico
     * @param cadena -la cadena a verificar
     * @return true si representa un valor numerico, false en otro caso
     * 
     */
    private static boolean isNumeric(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     * Metodo para que el administrador elimine comentarios
     * @param model
     * @param request
     * @return la pantalla de comentarios correspondiente al puesto asociado
     */
    @RequestMapping(value="/eliminarComentario", method = RequestMethod.POST)
    public ModelAndView eliminarComentario(ModelMap model,HttpServletRequest request){
        String quien_comento = request.getParameter("persona");
        String donde_comento = request.getParameter("puesto");
        String que_comento = request.getParameter("comentario");

        Persona quien = persona.getPersona_correo(quien_comento);
        Puesto lugar = puesto.verificaPuesto(donde_comento);
        
        Calificar comentario = new Calificar(quien, lugar, que_comento);
        
        calificar.delete(comentario);
        
        List<Calificar> comentarios = calificar.list_comentarios(donde_comento);
        
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nombre", donde_comento);
        
        return new ModelAndView("VerComentariosAdminIH", model);
    }
    
    /**
     * Redireccion para la pagina de comentarios de un puesto determinado
     * @param model
     * @param request
     * @return la pagina de comentarios de un puesto determinado
     */
      @RequestMapping(value="/verComentariosAdmin", method = RequestMethod.POST)
    public ModelAndView verComentarioPuestoAdmin(ModelMap model,HttpServletRequest request){
        String nombre = request.getParameter("comentariosDe");
        String wrong = "";
        List<Calificar> comentarios = calificar.list_comentarios(nombre);

        if(comentarios == null){
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }

        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nombre", nombre);
        return new ModelAndView("VerComentariosAdminIH",model);
    }
    



     /**
     * Funcion que regresa la informacion de los puestos con un usuario administrador
     * @param model
     * @return la pagina con la informacion de los puestos -implica las funcionalidades de un usuario estadar
     */
    @RequestMapping(value="/verInfoAdmin", method = RequestMethod.POST)
    public ModelAndView verInformacionPuestoAdmin(ModelMap model,HttpServletRequest request){
        
        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();
        
        if(puestos_registrados == null){
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }

        model.addAttribute("puestos", puestos_registrados);
        
        return new ModelAndView("VerInformacionPuestoAdminIH",model);
    }
    
    /**
     * Funcion que regresa la informacion de los puestos con un usuario registrado
     * @param model
     * @return la pagina con la informacion de los puestos para un usuario -no admin-
     */
    @RequestMapping(value="/verInfoRegistrado", method = RequestMethod.POST)
    public ModelAndView verInformacionPuestoUsReg(ModelMap model,HttpServletRequest request){
        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();
        if(puestos_registrados == null){
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }
        model.addAttribute("puestos", puestos_registrados);
        return new ModelAndView("VerInformacionPuestoRegistradosIH",model);
    }

    /**
     * Va a la vista con los datos del puesto.
     *
     * @param model
     * @param request
     * @return la vista del puesto a modificar.
     */
    @RequestMapping(value = "/actualizar", method = RequestMethod.POST)
    public ModelAndView actualizar(ModelMap model, HttpServletRequest request) {
        String nombreViejo = request.getParameter("nombreViejo");
        String nombre = request.getParameter("nombre");
        String ubicacion = request.getParameter("ubicacion");

        Puesto p = puesto.verificaPuesto(nombreViejo);
        Calificar c = calificar.buscar(nombre);
        List<Vender> v = vender.buscar(nombreViejo);
        String wrong = "";
        if(p == null){
            wrong = "El puesto no se encuentra en la base de datos, favor de verificar el nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH",model);
        }else{
            if(nombre.equals("") != true){
                puesto.update(p);
            }
            if(ubicacion.equals("") != true)
                p.setUbicacion(ubicacion);
            puesto.update(p);
        }
        
        
        return new ModelAndView("AdministradorIH", model);
    }

    /**
     * Metodo que muestra los comentarios a un usuario -no admin-
     * @param model
     * @param request
     * @return 
     */
    @RequestMapping(value = "/verComentarios", method = RequestMethod.POST)
    public ModelAndView verComentarios(ModelMap model, HttpServletRequest request){
        String nombre = request.getParameter("comentariosDe");
        String wrong = "";
        List<Calificar> comentarios = calificar.list_comentarios(nombre);

        if(comentarios == null){
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }

        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nombre", nombre);

        return new ModelAndView("VerComentariosIH",model);
    }

    /**
     * Metodo para eliminar un usuario del sistema.
     * @param model
     * @param request
     * @return la pagina home del administrador
     */
    @RequestMapping(value = "/eliminarUsuarioAdministrador1IH", method = RequestMethod.POST)
    public ModelAndView eliminarUsuario(ModelMap model, HttpServletRequest request) {
        String correo = request.getParameter("usuario");

        Usuario us = usuario.verificaUsuario(correo);
        Persona p = persona.usuario_registrado(correo);

        String wrong = "";

        if (us == null) {
            wrong = "El usuario no esta en la base de datos, favor de verificar el nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        } else {
            if(p == null){
                usuario.delete(us);
            }else{
                persona.delete(p);
                usuario.delete(us);
            }
        }
        return new ModelAndView("AdministradorIH", model);

    }

    /**
     * Redireccion para el formulario de eliminacion de usuarios
     * @param model
     * @param request
     * @return la pagina del formulario para eliminar usuarios
     */
     @RequestMapping(value = "/eliminarUsuarioAdministradorIH", method = RequestMethod.POST)
    public ModelAndView Usuarios(ModelMap model, HttpServletRequest request) {
         return new ModelAndView("eliminarUsuarioAdministradorIH", model);
    }
    
    /**
     * Metodo para eliminar comentarios -usuario no admin-
     * Difiere con el metodo del administrador en verificar que el usuario no pueda eliminar comentarios de otros usuarios
     * @param model
     * @param request
     * @return La pagina de comentarios asociada a un puesto determinado
     */
    @RequestMapping(value = "/eliminarComentarioUser", method = RequestMethod.POST)
    public ModelAndView eliminarComentarioUser(ModelMap model, HttpServletRequest request) {
        String quien_comento = request.getParameter("persona");
        String donde_comento = request.getParameter("puesto");
        String que_comento = request.getParameter("comentario");
        String wrong;
        
        if(!quien_comento.equals(user)){
            wrong = "Tu no puedes eliminar este comentario.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }
        
        Persona quien = persona.getPersona_correo(quien_comento);
        Puesto lugar = puesto.verificaPuesto(donde_comento);
        Calificar comentario = new Calificar(quien, lugar, que_comento);
        calificar.delete(comentario);
        List<Calificar> comentarios = calificar.list_comentarios(donde_comento);
        
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nombre", donde_comento);
        
        return new ModelAndView("VerComentariosIH", model);
    }
    
    /**
     * Redireccion para la pagina de editar comentarios -exclusivo usuario noAdmin-
     * @param model
     * @param request
     * @return la pagina del formulario para editar un comentario
     */
    @RequestMapping(value = "/editarComentario", method = RequestMethod.POST)
    public ModelAndView editarComentario(ModelMap model, HttpServletRequest request) {
        String quien_comento = request.getParameter("persona");
        String donde_comento = request.getParameter("puesto");
        String que_comento = request.getParameter("comentario");
        String wrong = "";
        
        if(!quien_comento.equals(user)){
            wrong = "Tu no puedes editar este comentario.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }
        
        model.addAttribute("comentario", que_comento);
        model.addAttribute("puesto", donde_comento);
        model.addAttribute("email", quien_comento);
        
        return new ModelAndView("editarComentarioIH", model);
    }
    
    /**
     * Metodo para guardar el comentario editado
     * @param model
     * @param request
     * @return la pagina de comentarios asociada a un puesto determinado
     */
    @RequestMapping(value = "/guardarComentario", method = RequestMethod.POST)
    public ModelAndView guardarComentario(ModelMap model, HttpServletRequest request) {
        String quien_comento = request.getParameter("persona");
        String donde_comento = request.getParameter("puesto");
        String nuevo_comentario = request.getParameter("comentario_nuevo");
        Calificar comentario = calificar.buscar_comentario(quien_comento);
        comentario.setComentario(nuevo_comentario);
        calificar.update(comentario);
        List<Calificar> comentarios = calificar.list_comentarios(donde_comento);
        model.addAttribute("comentarios", comentarios);
        model.addAttribute("nombre", donde_comento);
        return new ModelAndView("VerComentariosIH", model);
    }
}
