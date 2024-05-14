package com.inventario.vehiculo.repositories;

import com.inventario.vehiculo.entities.Vehiculo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface VehiculoRepository extends JpaRepository<Vehiculo, Integer> {

    Page<Vehiculo> findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCaseOrMatriculaContainingIgnoreCase(
            String marca, String modelo, String matricula, Pageable pageable);

}
