package com.restaurant.dao;

import com.restaurant.database.Conexion;
import com.restaurant.interfaces.crudCliente;
import com.restaurant.model.cliente;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDao implements crudCliente {

    //Estancias necesarias para la logica
    cliente cliente = new cliente();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "CALL spLeerClientes()";
    String getById = "CALL spObtenerClientePorID(?)";
    String add = "CALL spCrearCliente(?,?,?,?,?)";
    String update = "CALL spActualizarCliente(?,?,?,?,?,?)";
    String delete = "CALL spEliminarCliente(?)";

    @Override
    public List getAll() {
        ArrayList<cliente> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                cliente cli = new cliente();
                cli.setIdCliente(rs.getInt(1));
                cli.setCliNombre(rs.getString(2));
                cli.setCliApellido(rs.getString(3));
                cli.setCliCedula(rs.getString(4));
                cli.setCliEmail(rs.getString(5));
                cli.setCliTelefono(rs.getString(6));
                lista.add(cli);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public cliente getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                cliente.setIdCliente(rs.getInt(1));
                cliente.setCliNombre(rs.getString(2));
                cliente.setCliApellido(rs.getString(3));
                cliente.setCliCedula(rs.getString(4));
                cliente.setCliEmail(rs.getString(5));
                cliente.setCliTelefono(rs.getString(6));
                }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return  cliente;
    }

    @Override
    public String add(cliente cli) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(add);
            cs.setString(1, cli.getCliNombre());
            cs.setString(2,cli.getCliApellido());
            cs.setString(3, cli.getCliCedula());
            cs.setString(4, cli.getCliEmail());
            cs.setString(5, cli.getCliTelefono());
            cs.execute();
        } catch (SQLException ex) {
            System.out.println("Error al crear: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

    @Override
    public String updateById(cliente cli) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(update);
            cs.setInt(1, cli.getIdCliente());
            cs.setString(2, cli.getCliNombre());
            cs.setString(3,cli.getCliApellido());
            cs.setString(4, cli.getCliCedula());
            cs.setString(5, cli.getCliEmail());
            cs.setString(6, cli.getCliTelefono());
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
