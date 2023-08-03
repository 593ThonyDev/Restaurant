package com.restaurant.interfaces;

import com.restaurant.model.cliente;
import java.util.List;

public interface crudCliente {

    List getAll();

    cliente getById(Integer id);

    String add(cliente cliente);

    String updateById(cliente cliente);

    String deleteById(Integer id);
}
