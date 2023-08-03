package com.restaurant.interfaces;

import com.restaurant.model.detalle;
import java.util.List;

public interface crudDetalle {

    List getAllByCode(Integer fkVenta);

    String add(detalle detalle);

    String deleteById(Integer id);
}
