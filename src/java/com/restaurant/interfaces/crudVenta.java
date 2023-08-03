package com.restaurant.interfaces;

import com.restaurant.model.venta;
import java.util.List;

public interface crudVenta {

    List getAll();

    venta getById(Integer id);


}
