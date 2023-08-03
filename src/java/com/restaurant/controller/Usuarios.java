package com.restaurant.controller;

import com.restaurant.dao.UsuarioDao;
import com.restaurant.model.usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Usuarios extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "views/UsuariosCrear.jsp";
    String EDITAR = "views/UsuariosActualizar.jsp";
    String REGISTROS = "views/Usuarios.jsp";

    //Objetos
    usuario usu = new usuario();
    UsuarioDao dao = new UsuarioDao();
    //
    Integer idUsuario = 0;
    Integer usuIntento = 0;
    String usuUsuario;
    String usuClave;

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        usuario usu = (usuario) session.getAttribute("usuario");
        if (usu == null) {
            request.getRequestDispatcher(LOGIN).forward(request, response);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        usuario usu = (usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "guardar":
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    this.usu = new usuario(usuUsuario, usuClave, usuIntento);
                    dao.add(this.usu);
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idUsuario = Integer.parseInt(request.getParameter("idUsuario"));
                    usuUsuario = request.getParameter("usuUsuario");
                    usuClave = request.getParameter("usuClave");
                    this.usu = new usuario(idUsuario,usuUsuario, usuClave,0);
                    dao.updateById(this.usu);
                    System.out.println(dao.updateById(this.usu));
                    acceso = REGISTROS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("accion");
        String acceso = "";
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        usuario usu = (usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (action) {
                case "nuevo":
                    acceso = CREAR;
                    break;
                case "editar":
                    request.setAttribute("idUsuario", request.getParameter("idUsuario"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idUsuario = Integer.parseInt((String) request.getParameter("idUsuario"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    dao.deleteById(idUsuario);
                    acceso = REGISTROS;
                    break;

                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

}
