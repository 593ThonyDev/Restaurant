package com.restaurant.dao;

import java.sql.*;
import java.util.List;
import com.restaurant.database.Conexion;
import com.restaurant.interfaces.crudUsuario;
import com.restaurant.model.usuario;
import com.restaurant.security.Encriptador;
import java.util.ArrayList;

public class UsuarioDao implements crudUsuario {

    //Estancias necesarias para la logica
    Encriptador encriptador = new Encriptador();
    usuario usuario = new usuario();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "CALL spLeerUsuarios()";
    String getById = "CALL spObtenerUsuarioPorID(?)";
    String add = "CALL spCrearUsuario(?,?,?)";
    String update = "CALL updateUsuario(?,?,?,?)";
    String delete = "CALL spEliminarUsuario(?)";

    String CONSULTAR_USUARIO = "CALL getIdUsuario(?)";
    String CONSULTAR_CLAVE = "CALL getPassword(?)";
    String CONSULTAR_INTENTOS = "CALL getAttempts(?)";

    //
    Integer idUsuario;
    Integer usuIntento;
    String usuClave;

    /* =================== LOGIN =================== */
    @Override
    public Integer getIdUsuario(String USU_USUARIO) {
        try {
            idUsuario = 0;
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_USUARIO);
            cs.setString(1, USU_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                idUsuario = Integer.parseInt(rs.getString(1));
            }
        } catch (SQLException ex) {
            System.out.println("ID_USUARIO no encontrado: " + ex);
            return 0;
        }
        return idUsuario;
    }

    @Override
    public String getPassword(int ID_USUARIO) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_CLAVE);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                usuClave = rs.getString(1);
            }
        } catch (Exception e) {
            System.out.println("USU_CLAVE no encontrada: " + e);
        }
        return usuClave;
    }

    @Override
    public Integer getAttempts(int ID_USUARIO) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(CONSULTAR_INTENTOS);
            cs.setInt(1, ID_USUARIO);
            rs = cs.executeQuery();
            while (rs.next()) {
                usuIntento = rs.getInt(1);
            }
        } catch (Exception e) {
            System.out.println("USU_ESTADO no encontrado: " + e);
        }
        return usuIntento;
    }

    //Crud
    @Override
    public List getAll() {
        ArrayList<usuario> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                usuario usu = new usuario();
                usu.setIdUsuario(rs.getInt(1));
                usu.setUsuUsuario(rs.getString(2));
                usu.setUsuClave(rs.getString(3));
                usu.setUsuIntento(rs.getInt(4));
                lista.add(usu);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public usuario getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                usuario.setIdUsuario(rs.getInt(1));
                usuario.setUsuUsuario(rs.getString(2));
                usuario.setUsuClave(encriptador.desencriptar(rs.getString(3)));
                usuario.setUsuIntento(rs.getInt(4));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return usuario;
    }

    @Override
    public String add(usuario usu) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setString(1, usu.getUsuUsuario());
            cs.setString(2, encriptador.encriptar(usu.getUsuClave()));
            cs.setInt(3, usu.getUsuIntento());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String updateById(usuario usu) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(update);
            cs.setInt(1, usu.getIdUsuario());
            cs.setString(2, usu.getUsuUsuario());
            cs.setString(3, encriptador.encriptar(usu.getUsuClave()));
            cs.setInt(4, usu.getUsuIntento());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex);
            return "no actualizado";
        }
        return "actualizado";
    }

    @Override
    public String deleteById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(delete);
            cs.setInt(1, id);
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al eliminar: " + ex.getMessage());
            return "no eliminado";
        }
        return "eliminado";
    }

}
