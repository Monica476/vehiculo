package com.inventario.vehiculo.services.impl;

import com.inventario.vehiculo.entities.Vehiculo;
import com.inventario.vehiculo.repositories.VehiculoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.util.Arrays;
import java.util.Optional;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class VehiculoServiceTest {
    @Mock
    private VehiculoRepository iVehiculoRepository;

    @InjectMocks
    private VehiculoService vehiculoService;

    private Vehiculo vehiculo;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
        vehiculo =new Vehiculo();
        vehiculo.setId(Integer.valueOf(10));
        vehiculo.setColor("Rojo");
        vehiculo.setAnio(Integer.valueOf(2026));
        vehiculo.setMarca("Ford");
        vehiculo.setMatricula("KDJ234");
        vehiculo.setModelo("PRUEBA");
    }

    @Test
    void saveVehiculo() {
        when(iVehiculoRepository.save(any(Vehiculo.class))).thenReturn(vehiculo);
        Vehiculo saveVehiculo= vehiculoService.saveVehiculo(vehiculo);
        assertEquals(Integer.valueOf(10), saveVehiculo.getId());
        assertEquals("Rojo", saveVehiculo.getColor());
        assertEquals(Integer.valueOf(2026), saveVehiculo.getAnio());
        assertEquals("Ford", saveVehiculo.getMarca());
        assertEquals("KDJ234", saveVehiculo.getMatricula());
        assertEquals("PRUEBA", saveVehiculo.getModelo());

    }
    @Test
    void updateVehiculo() {
        when(iVehiculoRepository.existsById(10)).thenReturn(true);
        when(iVehiculoRepository.saveAndFlush(any(Vehiculo.class))).thenReturn(vehiculo);
        Vehiculo vehiculo2 =new Vehiculo();
        vehiculo2.setId(Integer.valueOf(10));
        vehiculo2.setColor("Verde");
        vehiculo2.setAnio(Integer.valueOf(2026));
        vehiculo2.setMarca("BMW");
        vehiculo2.setMatricula("KDJ234");
        vehiculo2.setModelo("PRUEBA1");
        Vehiculo updatedVehiculo = vehiculoService.updateVehiculo(vehiculo2);
        assertEquals(10, updatedVehiculo.getId());

    }

    @Test
    void getAllVehiculo() {
        when(iVehiculoRepository.findAll(any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(vehiculo)));
        assertEquals(1, vehiculoService.getAllVehiculo(0,10,true).getTotalElements());
    }

    @Test
    void getFilterVehiculo() {
        when(iVehiculoRepository.findByMarcaContainingIgnoreCaseOrModeloContainingIgnoreCaseOrMatriculaContainingIgnoreCase(anyString(),anyString(),anyString(),any(PageRequest.class)))
                .thenReturn(new PageImpl<>(Arrays.asList(vehiculo)));
        assertEquals(1,vehiculoService.getFilterVehiculo("Ford","PRUEBA","KDJ234", 0, 10).getTotalElements());
    }

    @Test
    void deleteVehiculo() {
        when(iVehiculoRepository.existsById(10)).thenReturn(true);
        vehiculoService.deleteVehiculo(10);
        verify(iVehiculoRepository,times(1)).deleteById(10);
    }



}