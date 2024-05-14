package com.inventario.vehiculo.services;

import com.inventario.vehiculo.entities.Vehiculo;
import org.springframework.data.domain.Page;

public interface IVehiculoService {

   Vehiculo saveVehiculo (Vehiculo vehiculo);

   Page<Vehiculo> getAllVehiculo (Integer page, Integer size, Boolean enablePagination);
   Page<Vehiculo> getFilterVehiculo ( String marca, String modelo, String matricula,Integer page, Integer size);
   void deleteVehiculo(Integer id);
   Vehiculo updateVehiculo(Vehiculo vehiculo);
    boolean existByid(Integer id);
    Vehiculo getFilterId (Integer id);
}
