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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.ServletException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    /*
     * Carga la pagina de inicio.
     */
    @RequestMapping(value = "/")
    public String PantallaDeInicioIH() {
        return "PantallaDeInicioIH";
    }

    /*
     * Carga los puestos de la base.
     */
    @RequestMapping(value = "/verInformacion", method = RequestMethod.GET)
    public ModelAndView verInformacionPuesto(ModelMap model, HttpServletRequest request) {

        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();

        if (puestos_registrados == null) {
            wrong = "Error al cargar la información.";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }

        model.addAttribute("puestos", puestos_registrados);

        return new ModelAndView("VerInformacionPuestoIH", model);
    }

    /*
     * Cambia de la vista de inicio a el formulario.
     */
    @RequestMapping(value = "/RegistrarseIH", method = RequestMethod.GET)
    public ModelAndView registrarse(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("RegistrarseIH", model);
    }

    /**
     * Cambia de la vista al formulario de cracion de puestos.
     */
    @RequestMapping(value = "/CrearPuestoIH", method = RequestMethod.GET)
    public ModelAndView creaPuest(ModelMap model, HttpServletRequest request) {
        return new ModelAndView("CrearPuestoIH", model);
    }

    /**
     * Cambia de la vista a la lista de puestos en la base de datos.
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
     * Cambia de la vista al formulario de eliminacion de puestos.
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

    /*
     * Iniciar Sesion
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

            //////
            user = email;
            /////

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

    /*
     * Metodo encargado de validar correo.
     */
    private boolean valida_email(String correo) {
        Pattern pattern = Pattern.compile(PATTERN_EMAIL);

        Matcher matcher = pattern.matcher(correo);
        return matcher.matches();
    }

    /*
     * Recibe los valores ingresados en el formulario.
     * Si son correctos se guardara al usuario nuevo.
     */
    @RequestMapping(value = "/formulario", method = RequestMethod.POST)
    public ModelAndView registro(ModelMap model, HttpServletRequest request) {
        Persona p = null;
        Usuario u = null;
        String wrong = "";

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
     * Metodo para crear nuevos puestos
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
     * Metodo para eliminar un puesto de la base de datos (para el
     * Administrador)
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
     * redirige a la calificacion de puesto
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
    
    
    /*
    Calificacion del puesto
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
    
    private static boolean isNumeric(String cadena){
        try{
            Integer.parseInt(cadena);
            return true;
        }catch(NumberFormatException nfe){
            return false;
        }
    }

    /**
     *Funcion para eliminar comentarios
     */
    @RequestMapping(value="/eliminarComentario", method = RequestMethod.POST)
    public ModelAndView eliminarComentario(ModelMap model,HttpServletRequest request){
        String mail = request.getParameter("usuario");
        String comenta = request.getParameter("comentario");
        String pu = request.getParameter("puesto");
        Puesto puest = puesto.verificaPuesto(pu);
        Persona p = persona.usuario_registrado(mail);
        Calificar c =  new Calificar(p,puest,comenta);

        String wrong ;
        if(puest == null){
        wrong = "El puesto no esta en la base de datos, favor de verificar el nombre";
            model.addAttribute("mensaje", wrong);
            return new ModelAndView("ErrorIH", model);
        }else if(c!=null){
            calificar.delete(c);
        }else{
            wrong = "Error al cargar";
            model.addAttribute("mensaje",wrong);
            return new ModelAndView("ErrorIH",model);
        }

        return new ModelAndView("AdministradorIH",model);
    }

    /**Redireccion para eliminar comentarios**/
    @RequestMapping(value="/eliminarComentarioAdmin", method = RequestMethod.POST)
    public ModelAndView eliminarComentarioPant(ModelMap model,HttpServletRequest request){
        return new ModelAndView("eliminarComentariosAdmin",model);
    }
    /**
     * Funcion que regresa la informacion de los puestos con un usuario registrado
     * @param model
     * @return 
     */
    @RequestMapping(value="/verInfoRegistrado", method = RequestMethod.POST)
    public ModelAndView verInformacionPuestoUsReg(ModelMap model,HttpServletRequest request){
        
        String wrong = "";
        List<Puesto> puestos_registrados = puesto.list_puestos();
        //List<List <String>> comentarios = new ArrayList<>();

        //for(Puesto p: puestos_registrados){
        //    comentarios.add(calificar.list_comentarios(p.getIdNombre()));
        //}
        
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


     @RequestMapping(value = "/eliminarUsuarioAdministradorIH", method = RequestMethod.POST)
    public ModelAndView Usuarios(ModelMap model, HttpServletRequest request) {
         return new ModelAndView("eliminarUsuarioAdministradorIH", model);
    }
}
