package com.restaurant.controller;

import com.restaurant.dao.MenuDao;
import com.restaurant.model.menu;
import com.restaurant.model.usuario;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class Productos extends HttpServlet {

    String LOGIN = "index.jsp";
    String CREAR = "views/ProductosCrear.jsp";
    String EDITAR = "views/ProductosActualizar.jsp";
    String REGISTROS = "views/Productos.jsp";

    //Objetos
    menu men = new menu();
    MenuDao dao = new MenuDao();
    //
    Integer idMenu = 0;
    String menNombre;
    String menCodigo;
    Integer menStock;
    Double menPrecio;

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
                    menNombre = request.getParameter("proNombre");
                    menCodigo = request.getParameter("proCodigo");
                    menStock = Integer.parseInt(request.getParameter("proStock"));
                    menPrecio = Double.parseDouble(request.getParameter("proPrecio"));
                    men = new menu(menNombre, menCodigo, menStock, menPrecio);
                    dao.add(men);
                    acceso = REGISTROS;
                    break;
                case "actualizar":
                    idMenu = Integer.parseInt(request.getParameter("idProducto"));
                    menNombre = request.getParameter("proNombre");
                    menCodigo = request.getParameter("proCodigo");
                    menStock = Integer.parseInt(request.getParameter("proStock"));
                    menPrecio = Double.parseDouble(request.getParameter("proPrecio"));
                    men = new menu(idMenu, menNombre, menCodigo, menStock, menPrecio);
                    dao.updateById(men);
                    System.out.println(dao.updateById(men));
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
                    request.setAttribute("idProducto", request.getParameter("idProducto"));
                    acceso = EDITAR;
                    break;
                case "eliminar":
                    idMenu = Integer.parseInt((String) request.getParameter("idProducto"));
                    /* ========== ENVIO EL OBJETO A LA DB=========*/
                    dao.deleteById(idMenu);
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
