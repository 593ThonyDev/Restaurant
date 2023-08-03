package com.restaurant.interfaces;

import com.restaurant.model.menu;
import java.util.List;

public interface crudProducto {

    List getAll();

    menu getById(Integer id);

    String add(menu producto);

    String updateById(menu producto);

    String deleteById(Integer id);
}
