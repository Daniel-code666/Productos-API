/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.products.products.service;

import java.util.List;
import java.util.Optional;
import com.products.products.models.Imagen;
import com.products.products.models.Producto;

/**
 *
 * @author Daniel
 */
public interface ImagenService {
    public List<?> imgByPublc(Producto p);
    public List<Imagen> imgAll();
    public Optional<?> oneImage(int id);
    public Imagen save(Imagen image);
    public void deleteById(int id);
    public void deleteByProductId(int id);
}
