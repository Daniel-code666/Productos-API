/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.products.products.repository;

import com.products.products.models.Imagen;
import com.products.products.models.Producto;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Daniel
 */
@Repository
public interface InterfaceImagen extends JpaRepository<Imagen, Integer>{
    public List<?> findByProducto(Producto p);
    //public void deleteByIdProduct (int id);
}
