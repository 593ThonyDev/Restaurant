package com.restaurant.controller;

import com.restaurant.model.*;
import com.restaurant.dao.*;
import com.restaurant.security.Encriptador;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;

public class Login extends HttpServlet {

    /*Variables de sesion*/
    HttpSession session;
    usuario usu = new usuario();
    UsuarioDao usuDao = new UsuarioDao();
    Encriptador enc = new Encriptador();
    /*Variables de la logica*/
    String usuUsuario = "";
    String usuClave = "";
    String usuClaveBd = "";
    Integer usuIntento = 0;
    Integer idUsuario = 0;

    // Constante para el número máximo de intentos permitidos
    final int MAX_INTENTOS = 3;

    /*Variables de navegacion*/
    String INCIO = "Controlador?p=ventas";
    String LOGIN = "index.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        session = request.getSession();
        usuUsuario = request.getParameter("usuUsuario");
        usuClave = request.getParameter("usuClave");
        idUsuario = usuDao.getIdUsuario(usuUsuario);
        System.out.println("idUsuario: " + idUsuario);
        // Si los parametros es igual a null o el id del idUsuario == 0 caso contrario pasa al else
        if (idUsuario == 0 || idUsuario == null) {
            request.setAttribute("errorSesion", "El usuario ingresado no esta registrado!!");
            request.getRequestDispatcher(LOGIN).forward(request, response);
        } else {
            // Utilizamos la bd para dar logica
            usuClaveBd = enc.desencriptar(usuDao.getPassword(idUsuario));
            System.out.println("usuClaveBd " + usuClaveBd);

            // SI LA CLAVE DESENCRIPTADA ES IGUAL A LA INGRESADA EN LA INTERFAZ SE DA EL ACCESO
            usuIntento = usuDao.getAttempts(idUsuario);

            // Validamos la clave
            // si la usuClaveBd(BASE DE DATOS DESENCRIPTADA) es igual a la clave del input igresado por el usuario
            if (usuClaveBd.equals(usuClave)) {
                // GENERAMOS EL CONTROL DE INTENTOS
                if (usuIntento <= MAX_INTENTOS) {
                    // ... Código de inicio de sesión exitoso ...
                    // Resetear intentos fallidos al inicio de sesión exitoso
                    usu = new usuario(idUsuario, usuUsuario, usuClave, 0);
                    usuDao.updateById(usu);
                    // Realizamos la redireccion a la interfaz y agregamos los datos del usuario
                    session.setAttribute("idUsuario", idUsuario);
                    session.setAttribute("usuUsuario", usuUsuario);
                    session.setAttribute("usuario", usu);
                    response.sendRedirect(INCIO);
                } else {
                    request.setAttribute("errorSesion", "LIMITES DE INTENTOS EXCEDIDOS!!");
                    request.getRequestDispatcher(LOGIN).forward(request, response);
                }
            } else {
                // Incrementar el contador de intentos fallidos
                usu = new usuario(idUsuario, usuUsuario, usuClaveBd, usuIntento + 1);

                int intentosRestantes = MAX_INTENTOS - usuIntento - 1; // Cálculo de intentos restantes

                if (usuIntento >= MAX_INTENTOS) {
                    request.setAttribute("errorSesion", "LÍMITE DE INTENTOS EXCEDIDO," + "\n" + "USUARIO BLOQUEADO!!");
                } else {
                    if (intentosRestantes == 0) {
                        request.setAttribute("errorSesion", "CLAVE INCORRECTA!!" + "\n" + "USUARIO BLOQUEADO!!");
                        usu = new usuario(idUsuario, usuUsuario, usuClaveBd, 0);

                    } else {
                        request.setAttribute("errorSesion", "CLAVE INCORRECTA!!" + "\n" + "Intentos restantes: " + intentosRestantes);
                    }
                }
                usuDao.updateById(usu);
                request.getRequestDispatcher(LOGIN).forward(request, response);
            }
        }
    }
}
