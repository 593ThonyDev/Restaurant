package com.restaurant.controller;

import com.restaurant.dao.VentaDao;
import com.restaurant.model.usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.util.UUID;

public class Controlador extends HttpServlet {

    String LOGIN = "index.jsp";
    String VENTA = "views/Ventas.jsp";
    String CLIENTES = "views/Clientes.jsp";
    String PRODUCTOS = "views/Productos.jsp";
    String USUARIOS = "views/Usuarios.jsp";
    String acceso = "";
    VentaDao dao = new VentaDao();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getParameter("p");
        //========================================================================================== GLOBALES
        HttpSession session = request.getSession();
        usuario usu = (usuario) session.getAttribute("usuario");
        if (usu == null) {
            // si no existe un usuario activo en el servidor se redirige al login
            request.setAttribute("errorSesion", "Debes iniciar sesion, para acceder al contenido!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            switch (page) {
                case "ventas":
                    UUID uuid = UUID.randomUUID();
                    request.setAttribute("codVenta",uuid.toString());
                    request.setAttribute("idVenta",dao.getLastId()+1);
                    acceso = VENTA;
                    break;
                case "clientes":
                    acceso = CLIENTES;
                    break;
                case "productos":
                    acceso = PRODUCTOS;
                    break;
                case "usuarios":
                    acceso = USUARIOS;
                    break;
                default:
                    acceso = LOGIN;
            }
            RequestDispatcher view = request.getRequestDispatcher(acceso);
            view.forward(request, response);
        }
    }

}
