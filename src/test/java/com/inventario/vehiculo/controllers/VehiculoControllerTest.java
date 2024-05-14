package com.inventario.vehiculo.controllers;

import com.inventario.vehiculo.entities.Vehiculo;
import com.inventario.vehiculo.services.IVehiculoService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class VehiculoControllerTest {

    @InjectMocks
    private VehiculoController vehiculoController;

    @Mock
    private IVehiculoService vehiculoService;

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
    void saveVehicle() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setColor("Rojo");
        vehiculo.setAnio(2022);
        vehiculo.setMarca("Ford");
        vehiculo.setMatricula("ABC123");
        vehiculo.setModelo("Focus");

        when(vehiculoService.saveVehiculo(any(Vehiculo.class))).thenReturn(vehiculo);

        ResponseEntity<Vehiculo> responseEntity = vehiculoController.saveVehicle(vehiculo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehiculo, responseEntity.getBody());
    }

    @Test
    void getAllVehicle() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo());
        Page<Vehiculo> vehiculoPage = new PageImpl<>(vehiculos);

        when(vehiculoService.getAllVehiculo(0, 10, false)).thenReturn(vehiculoPage);

        ResponseEntity<Page<Vehiculo>> responseEntity = vehiculoController.getAllVehicle(0, 10, false);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehiculoPage, responseEntity.getBody());
    }

    @Test
    void getVehicle() {
        List<Vehiculo> vehiculos = new ArrayList<>();
        vehiculos.add(new Vehiculo());
        Page<Vehiculo> vehiculoPage = new PageImpl<>(vehiculos);

        when(vehiculoService.getFilterVehiculo("Ford", "Focus", "ABC123", 0, 10)).thenReturn(vehiculoPage);

        ResponseEntity<Page<Vehiculo>> responseEntity = vehiculoController.getVehicle("Ford", "Focus", "ABC123", 0, 10);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehiculoPage, responseEntity.getBody());
    }

    @Test
    void deleteVehicle() {
        Integer id = 10;
        when(vehiculoService.existByid(id)).thenReturn(true);

        ResponseEntity<Boolean> responseEntity = vehiculoController.deleteVehicle(id);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertFalse(responseEntity.getBody());
    }

    @Test
    void editVehiculo() {
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(1);
        vehiculo.setColor("Rojo");
        vehiculo.setAnio(2022);
        vehiculo.setMarca("Ford");
        vehiculo.setMatricula("ABC123");
        vehiculo.setModelo("Focus");

        when(vehiculoService.updateVehiculo(any(Vehiculo.class))).thenReturn(vehiculo);

        ResponseEntity<Vehiculo> responseEntity = vehiculoController.editVehiculo(vehiculo);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehiculo, responseEntity.getBody());
    }

    @Test
    void getIdVehicle() {
        Integer id = 1;
        Vehiculo vehiculo = new Vehiculo();
        vehiculo.setId(id);
        when(vehiculoService.getFilterId(id)).thenReturn(vehiculo);

        ResponseEntity<Vehiculo> responseEntity = vehiculoController.getIdVehicle(id);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(vehiculo, responseEntity.getBody());
    }
}
