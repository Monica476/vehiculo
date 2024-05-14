package com.inventario.vehiculo.controllers;

import com.inventario.vehiculo.services.IVehiculoService;
import com.inventario.vehiculo.entities.Vehiculo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping("/api/vehiculo")
public class VehiculoController {
    @Autowired
    private IVehiculoService vehiculoService;


    @PostMapping
    public ResponseEntity<Vehiculo> saveVehicle (@RequestBody Vehiculo vehiculo){
        try {
            return ResponseEntity.ok(vehiculoService.saveVehiculo(vehiculo));
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }


    }
    @GetMapping
    public ResponseEntity<Page<Vehiculo>> getAllVehicle (
            @RequestParam(required = false, defaultValue = "0") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size,
            @RequestParam(required = false, defaultValue = "false") Boolean enablePagination){
        return ResponseEntity.ok(vehiculoService.getAllVehiculo(page, size, enablePagination));
    }
    @GetMapping(value = "/filter")
    public ResponseEntity<Page<Vehiculo>> getVehicle (
            @RequestParam(required = false, defaultValue = "")String marca,
            @RequestParam(required = false, defaultValue = "")String modelo,
            @RequestParam(required = false, defaultValue = "")String matricula,
            @RequestParam(required = false, defaultValue = "") Integer page,
            @RequestParam(required = false, defaultValue = "10") Integer size){
        return ResponseEntity.ok(vehiculoService.getFilterVehiculo(marca, modelo,matricula,page, size));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> deleteVehicle(@PathVariable Integer id){
        try {
        vehiculoService.deleteVehiculo(id);
        return ResponseEntity.ok(!vehiculoService.existByid(id));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    @PutMapping
    public ResponseEntity<Vehiculo> editVehiculo(@RequestBody Vehiculo vehiculo){
        try{
            return ResponseEntity.ok(vehiculoService.updateVehiculo(vehiculo));
        }catch (Exception e){
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
    @GetMapping(value = "/{id}")
    public ResponseEntity<Vehiculo> getIdVehicle (
            @PathVariable Integer id){
        return ResponseEntity.ok(vehiculoService.getFilterId(id));
    }

}
