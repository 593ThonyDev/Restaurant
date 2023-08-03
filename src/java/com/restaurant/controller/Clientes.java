package com.restaurant.controller;

import com.restaurant.dao.ClienteDao;
import com.restaurant.model.cliente;
import com.restaurant.model.usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Clientes extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "views/ClientesCrear.jsp";
    String EDITAR = "views/ClientesActualizar.jsp";
    String REGISTROS = "views/Clientes.jsp";

    //Objetos
    cliente cli = new cliente();
    ClienteDao dao = new ClienteDao();
    //
    Integer idCliente = 0;
    String cliNombre;
    String cliApellido;
    String cliCedula;
    String cliEmail;
    String cliTelefono;

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
                    cliNombre = request.getParameter("cliNombre");
                    cliApellido = request.getParameter("cliApellido");
                    cliCedula = request.getParameter("cliCedula");
                    cliEmail = request.getParameter("cliEmail");
                    cliTelefono = request.getParameter("cliTelefono");
                    cli = new cliente(cliNombre, cliApellido, cliCedula, cliEmail, cliTelefono);
                    dao.add(cli);
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idCliente = Integer.parseInt(request.getParameter("idCliente"));
                    cliNombre = request.getParameter("cliNombre");
                    cliApellido = request.getParameter("cliApellido");
                    cliCedula = request.getParameter("cliCedula");
                    cliEmail = request.getParameter("cliEmail");
                    cliTelefono = request.getParameter("cliTelefono");
                    cli = new cliente(idCliente,cliNombre, cliApellido, cliCedula, cliEmail, cliTelefono);
                    dao.updateById(cli);
                    System.out.println(dao.updateById(cli));
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
                    request.setAttribute("idCliente", request.getParameter("idCliente"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idCliente = Integer.parseInt((String) request.getParameter("idCliente"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    dao.deleteById(idCliente);
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
