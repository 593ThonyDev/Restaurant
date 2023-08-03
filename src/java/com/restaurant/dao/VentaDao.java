package com.restaurant.dao;

import com.restaurant.database.Conexion;
import com.restaurant.interfaces.crudVenta;
import com.restaurant.model.detalle;
import com.restaurant.model.venta;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VentaDao implements crudVenta {

    //Estancias necesarias para la logica
    venta venta = new venta();
    Conexion cn = new Conexion();
    CallableStatement cs;
    Connection con;
    ResultSet rs;

    //variables para los procedimientos almacenados
    String getAll = "";
    String getById = "";
    String add = "";

    @Override
    public List getAll() {
        ArrayList<venta> lista = new ArrayList<>();
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getAll);
            rs = cs.executeQuery();
            while (rs.next()) {
                venta pro = new venta();
                pro.setIdVenta(rs.getInt(1));
                pro.setFkCliente(rs.getInt(2));
                pro.setCliNombre(rs.getString(3));
                pro.setCliApellido(rs.getString(4));
                pro.setVenCodigo(rs.getString(5));
                pro.setVenFecha(rs.getString(6));
                pro.setDetTotal(rs.getDouble(7));
                lista.add(pro);
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return lista;
    }

    @Override
    public venta getById(Integer id) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall(getById);
            cs.setInt(1, id);
            rs = cs.executeQuery();
            while (rs.next()) {
                venta.setIdVenta(rs.getInt(1));
                venta.setFkCliente(rs.getInt(2));
                venta.setCliNombre(rs.getString(3));
                venta.setCliApellido(rs.getString(4));
                venta.setVenCodigo(rs.getString(5));
                venta.setVenFecha(rs.getString(6));
                venta.setDetTotal(rs.getDouble(7));
            }
        } catch (SQLException ex) {
            System.out.println("Error al listar: " + ex.getMessage());
        }
        return venta;
    }

    public Integer getLastId() {
        int numero = 0;
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("CALL obtenerUltimoID()");
            rs = cs.executeQuery();
            while (rs.next()) {
                numero = rs.getInt(1);
            }
        } catch (SQLException ex) {
            System.out.println("No hay id: " + ex.getMessage());
            return 0;
        }
        return numero;
    }

    public String saveSale(venta ven, detalle detalle) {
        try {
            con = (Connection) cn.getConexion();
            cs = con.prepareCall("call saveSale(?,?,?,?,?,?,?,?)");
            cs.setInt(1, ven.getFkCliente());
            cs.setString(2, ven.getVenCodigo());
            cs.setString(3, ven.getVenFecha());
            cs.setDouble(4, ven.getDetTotal());
            cs.setInt(5, detalle.getFkVenta());
            cs.setInt(6, detalle.getFkProducto());
            cs.setInt(7, detalle.getDetCantidad());
            cs.setDouble(8, detalle.getDetTotal());
            cs.executeQuery();
        } catch (SQLException ex) {
            System.out.println("Error al crear la venta: " + ex.getMessage());
            return "no creado";
        }
        return "creado";
    }

}
