package com.inventario.vehiculo.services.impl;

import com.inventario.vehiculo.entities.Vehiculo;
import com.inventario.vehiculo.repositories.VehiculoRepository;
import com.inventario.vehiculo.services.IVehiculoService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
@Slf4j
@Service
public class VehiculoService implements IVehiculoService {
    @Autowired
    private VehiculoRepository iVehiculoRepository;

    /**
     * servicio que permite guardar el vehiculo
     * @param vehiculo
     * @return
     */
    @Override
    public Vehiculo saveVehiculo (Vehiculo vehiculo){
        return  iVehiculoRepository.save(vehiculo);
    }

    /**
     * Servicio que permite obtener todos los datos paginado
     * @param page
     * @param size
     * @param enablePagination
     * @return
     */
    @Override
    public Page<Vehiculo> getAllVehiculo ( Integer page, Integer size, Boolean enablePagination){
        return iVehiculoRepository.findAll(enablePagination? PageRequest.of(page,size): Pageable.unpaged());
    }

    /**
     * Servicio que permite filtrar por los siguiente paramatros u obtener toda la informacion:
     * @param marca
     * @param modelo
     * @param matricula
     * @param page
     * @param size
     * @return
     */
    @Override
    public Page<Vehiculo> getFilterVehiculo ( String marca, String modelo, String matricula,Integer page, Integer size){
        try{
            return iVehiculoRepository.findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCaseOrMatriculaContainingIgnoreCase( marca,  modelo,matricula, PageRequest.of(page,size));
        }catch (InternalError e){
            throw new IllegalArgumentException("Vehiculo no encontrado");
        }

    }

    /**
     * Servicio que permite eliminar el vehiculo
     * @param id
     */
    @Override
     public void deleteVehiculo(Integer id){
         if (id != null && iVehiculoRepository.existsById(id)){
            iVehiculoRepository.deleteById(id);
         }
         else
             throw new IllegalArgumentException("El vehículo no existe o no tiene un ID válido");
     }

    /**
     * Servicio que permite actualizar los datos
     * @param vehiculo
     * @return
     */
    @Override
    public Vehiculo updateVehiculo(Vehiculo vehiculo){
        if (vehiculo.getId() != null && iVehiculoRepository.existsById(vehiculo.getId())){
            return iVehiculoRepository.saveAndFlush(vehiculo);
        }
        else
            throw new IllegalArgumentException("El vehículo no existe o no tiene un ID válido");
    }

    /**
     * Permite validad si exixte el vehiculo
     * @param id
     * @return
     */
    public boolean existByid(Integer id) {
        return iVehiculoRepository.existsById(id);
    }

    public Vehiculo getFilterId (Integer id){
        return iVehiculoRepository.findById(id).get();
    }
}
