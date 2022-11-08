/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.products.products.service;

import com.products.products.models.Producto;
import java.awt.print.Pageable;
import java.util.Optional;
import org.springframework.data.domain.Page;
/**
 *
 * @author Daniel
 */
public interface ProductoService {
    public Iterable<Producto> findAll();

    public Page<Producto> findAll(Pageable pageable);

    public Optional<Producto> findById(int id);

    public Producto save(Producto prod);

    public Producto update(Producto prod);
    
    public void deleteById(int id);

    public Iterable<Producto> findByName(String name);
}
