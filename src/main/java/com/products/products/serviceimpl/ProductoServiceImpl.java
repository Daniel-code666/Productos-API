/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.serviceimpl;

import com.products.products.repository.InterfaceProducto;
import com.products.products.service.ProductoService;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.products.products.models.Producto;
import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Sort;

/**
 *
 * @author Daniel
 */
@Service
public class ProductoServiceImpl implements ProductoService {
    @Autowired
    private InterfaceProducto interfaceProducto;

    @Override
    public Iterable<Producto> findAll() {

        return interfaceProducto.findAll();
    }

    @Override
    public Page<Producto> findAll(Pageable pageable) {

        return (Page<Producto>) interfaceProducto.findAll((Sort) pageable);
    }

    @Override
    public Optional<Producto> findById(int id) {

        return interfaceProducto.findById(id);
    }

    @Override
    @Transactional
    public Producto save(Producto prod) {

        return interfaceProducto.save(prod);
    }

    @Override
    public Producto update(Producto prod) {
        return interfaceProducto.save(prod);
    }
    
    @Override
    public void deleteById(int id) {
        interfaceProducto.deleteById(id);

    }

    @Override
    public Iterable<Producto> findByName(String name) {

        //return (Iterable<Producto>) interfaceProducto.listByName(name);
        return null;
    }
}
