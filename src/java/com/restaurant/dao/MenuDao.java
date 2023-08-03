package com.restaurant.dao;

import com.restaurant.database.Conexion;
import com.restaurant.interfaces.crudProducto;
import com.restaurant.model.menu;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MenuDao implements crudProducto {

    //Estancias necesarias para la logica
    menu menu = new menu();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "CALL spLeerMenus()";
    String getById = "CALL spObtenerMenuPorID(?)";
    String add = "CALL spCrearMenu(?,?,?,?)";
    String update = "CALL spActualizarMenu(?,?,?,?,?)";
    String delete = "CALL spEliminarMenu(?)";

    @Override
    public List getAll() {
        ArrayList<menu> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                menu pro = new menu();
                pro.setIdMenu(rs.getInt(1));
                pro.setMenNombre(rs.getString(2));
                pro.setMenCodigo(rs.getString(3));
                pro.setMenStock(rs.getInt(4));
                pro.setMenPrecio(rs.getDouble(5));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public menu getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                menu.setIdMenu(rs.getInt(1));
                menu.setMenNombre(rs.getString(2));
                menu.setMenCodigo(rs.getString(3));
                menu.setMenStock(rs.getInt(4));
                menu.setMenPrecio(rs.getDouble(5));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return menu;
    }

    @Override
    public String add(menu men) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setString(1, men.getMenNombre());
            cs.setString(2, men.getMenCodigo());
            cs.setInt(3, men.getMenStock());
            cs.setDouble(4, men.getMenPrecio());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String updateById(menu men) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(update);
            cs.setInt(1, men.getIdMenu());
            cs.setString(2, men.getMenNombre());
            cs.setString(3, men.getMenCodigo());
            cs.setInt(4, men.getMenStock());
            cs.setDouble(5, men.getMenPrecio());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al actualizar: " + ex.getMessage());
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
