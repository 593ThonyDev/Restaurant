package com.restaurant.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import java.io.IOException;

public class CerrarSesion extends HttpServlet {

    String LOGIN = "index.jsp";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        HttpSession session = request.getSession();
        session.removeAttribute("usuario");
        session.removeAttribute("idUsuario");
        session.removeAttribute("usuUsuario");
        request.setAttribute("errorSesion", "Cerraste sesion!!");
        request.getRequestDispatcher(LOGIN).forward(request, response);
    }

}
