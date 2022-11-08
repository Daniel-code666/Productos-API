/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.products.products.service;

import java.util.List;
import java.util.Optional;
import com.products.products.models.Imagen;

/**
 *
 * @author Daniel
 */
public interface ImagenService {
    public List<?> imgByPublc(int id);
    public List<Imagen> imgAll();
    public Optional<?> oneImage(int id);
    public List<?> save(Imagen img);
    public void deleteById(int id);
}
