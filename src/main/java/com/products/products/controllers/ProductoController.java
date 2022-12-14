/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.controllers;

import com.products.products.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.products.products.models.Producto;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PatchMapping;

/**
 *
 * @author Daniel
 */
@RestController
@RequestMapping(value = "/product")
public class ProductoController {

    @Autowired
    private ProductoService productoService;

    
    /*
     * CREATE
     **/
    
    //crear un nuevo producto
    @PostMapping(value = "/create")
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(producto));
    }
    
    /*
     * UPDATE
     * */
    //Actualizar un producto
    @PutMapping(value = "update/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable(value = "id") int productId) {
        Optional<Producto> oProduct = productoService.findById(productId);
        if (!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {

            /*
            * esta anera copia todas las caracteristicas de un objeto a otro
            * 
            * BeanUtils.copyProperties(producto, oProduct.get());
            * */
            if(producto.getNombre() != null){
                oProduct.get().setNombre(producto.getNombre());
            }
            
            if(producto.getDescripcion() != null){
                oProduct.get().setDescripcion(producto.getDescripcion());
            }
            
            if(producto.getPrecioUnidad() != 0){
                oProduct.get().setPrecioUnidad(producto.getPrecioUnidad());
            }
            
            if(producto.getStock() != 0){
                oProduct.get().setStock(producto.getStock());
            }
            
            return ResponseEntity.status(HttpStatus.CREATED).body(productoService.save(oProduct.get()));
        }

    }
    
    @PutMapping(value = "updateResta/{id}/{cant}")
    public boolean uptadeCantidad(@PathVariable(value = "id")String productId,@PathVariable(value = "cant")String cantidad ) {
    	
        int idProduct = Integer.parseInt(productId);
        float qtyInvoice = Float.parseFloat(cantidad);
        int qtyInvoice2 = (int)qtyInvoice;
        Optional<Producto> oProduct = productoService.findById(idProduct);
        if (!oProduct.isPresent()) {
            return false;
        }else {
        	
            try {
                if(qtyInvoice2 != 0){
                    int cant=oProduct.get().getStock()-qtyInvoice2;
                    oProduct.get().setStock(cant);
                }
                productoService.save(oProduct.get());
                return true;
			} catch (Exception e) {
				return false;
			}

        }
    	
    }
    
    /*
     * READ
     * */
    //leer un solo registro

    @GetMapping(value = "/findById/{id}")
    public ResponseEntity<?> findById(@PathVariable(name = "id") int productId) {
        Optional<Producto> oProduct = productoService.findById(productId);
        if (!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            return ResponseEntity.ok(oProduct);
        }
    }
    //leer todas las publicaciones
    @GetMapping(value = "/findAll")
    public List<Producto> readAll() {
        List<Producto> product = StreamSupport
                .stream(productoService.findAll().spliterator(), false)
                .collect(Collectors.toList());
        return product;
    }

    //buscar por nombre
    @GetMapping(value = "/findByName/{name}")
    public List<Producto> ReadByName(@PathVariable(value = "name") String productName) {
        List<Producto> product = StreamSupport
                .stream(productoService.findByName(productName).spliterator(), false)
                .collect(Collectors.toList());
        return product;
    }
    
    /*
     * DELETE
     * */
    
    //eliminar una publicacion
    @DeleteMapping(value = "/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable(value = "id") int productId) {
        Optional<Producto> oProduct = productoService.findById(productId);
        if (!oProduct.isPresent()) {
            return ResponseEntity.notFound().build();
        } else {
            productoService.deleteById(productId);
            return ResponseEntity.ok().build();
        }
    }

    
}
